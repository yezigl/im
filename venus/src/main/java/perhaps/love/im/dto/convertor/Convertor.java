/**
 * Copyright 2016 focus.cn. All Rights Reserved.
 */
package perhaps.love.im.dto.convertor;

import java.util.List;

/**
 * description here
 *
 * @author lidehua
 * @since 2016年11月17日
 */
public interface Convertor<S, T> {
    
    T convert(S source);
    
    List<T> convert(List<S> sources);
    
    /**
     * 需要缓存的子类自己实现
     * <br>
     * 在调用convert方法前先调用 {@link #caches(List)} 方法
     * @param coll
     */
    default Convertor<S, T> caches(List<S> coll) {
        return this;
    }
}
