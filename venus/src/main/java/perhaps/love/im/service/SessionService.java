/**
 * Copyright 2016 yezi.gl. All Rights Reserved.
 */
package perhaps.love.im.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import perhaps.love.im.entity.Session;

/**
 * description here
 *
 * @author yezi
 * @since 2016年12月29日
 */
@Service
public class SessionService {

    private static final String SESSION_PREFIX = "session-";

    private static final String SESSION_LIST_PREFIX = "session-list-";

    @Resource(name = "stringRedisTemplate")
    StringRedisTemplate redisTemplate;

    public Session get(int uid, int toId) {
        String value = (String) redisTemplate.opsForHash().get(SESSION_PREFIX + uid, String.valueOf(toId));
        return JSON.parseObject(value, Session.class);
    }

    public List<Session> listSession(int uid, int offset, int limit) {
        List<String> ids = redisTemplate.opsForList().range(SESSION_LIST_PREFIX + uid, offset, offset + limit);
        List<Object> values = redisTemplate.opsForHash().multiGet(SESSION_PREFIX + uid,
                ids.stream().map(e -> (Object) e).collect(Collectors.toList()));
        return values.stream().map(e -> JSON.parseObject((String) e, Session.class)).filter(Objects::nonNull)
                .sorted((a, b) -> (int) (a.getTime() - b.getTime())).collect(Collectors.toList());
    }

    public void put(int uid, Session session) {
        delete(uid, session.getId());
        redisTemplate.opsForList().leftPush(SESSION_LIST_PREFIX + uid, String.valueOf(session.getId()));
        redisTemplate.opsForHash().put(SESSION_PREFIX + uid, String.valueOf(session.getId()), JSON.toJSONString(session));
    }

    public void delete(int uid, int id) {
        redisTemplate.opsForList().remove(SESSION_LIST_PREFIX + uid, 0, String.valueOf(id));
        redisTemplate.opsForHash().delete(SESSION_PREFIX + uid, String.valueOf(id));
    }
}
