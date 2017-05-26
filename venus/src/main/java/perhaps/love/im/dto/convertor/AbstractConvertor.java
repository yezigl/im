/**
 * Copyright 2016 focus.cn. All Rights Reserved.
 */
package perhaps.love.im.dto.convertor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * description here
 *
 * @author lidehua
 * @since 2016年11月21日
 */
public abstract class AbstractConvertor<S, T> implements Convertor<S, T> {
    
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<T> convert(List<S> sources) {
        return sources.stream().map(this::convert).collect(Collectors.toList());
    }
}
