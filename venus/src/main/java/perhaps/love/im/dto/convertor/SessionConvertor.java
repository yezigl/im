/**
 * Copyright 2016 focus.cn. All Rights Reserved.
 */
package perhaps.love.im.dto.convertor;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import perhaps.love.im.dto.SessionDto;
import perhaps.love.im.entity.MessageType;
import perhaps.love.im.entity.Session;

/**
 * description here
 *
 * @author lidehua
 * @since 2016年11月18日
 */
@Component
public class SessionConvertor extends AbstractConvertor<Session, SessionDto> {

    private static final long MIN_5 = 5 * 60 * 1000L;
    private SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
    private SimpleDateFormat sdf2 = new SimpleDateFormat("yy/MM/dd");

    @Override
    public SessionDto convert(Session session) {
        SessionDto sessionDto = new SessionDto();
        sessionDto.setId(session.getId());
        sessionDto.setUid(session.getUid());
        sessionDto.setAvatar(session.getAvatar());
        if (session.getLastType() == MessageType.IMAGE) {
            sessionDto.setLastContent("[图片]");
        } else {
            sessionDto.setLastContent(session.getLastContent());
        }
        sessionDto.setLastName(session.getLastName());
        sessionDto.setName(session.getName());
        sessionDto.setTime(formatTime(session.getTime()));
        sessionDto.setType(session.getType());
        sessionDto.setUnread(session.getUnread());
        return sessionDto;
    }

    /**
     * @param time
     * @return
     */
    private String formatTime(long time) {
        if (time <= 0) {
            return null;
        }
        long c = System.currentTimeMillis();
        Date today = Date.from(LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0, 0)).atZone(ZoneId.systemDefault()).toInstant());
        if (c - time < MIN_5) {
            return "刚刚";
        } else if (time > today.getTime()){
            return sdf1.format(new Date(time));
        } else {
            return sdf2.format(new Date(time));
        }
    }

    @Override
    public List<SessionDto> convert(List<Session> sessions) {
        return sessions.stream().map(this::convert).collect(Collectors.toList());
    }

}
