package perhaps.love.im.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class PageBuilder {

    private static final int PAGE_SIZE = 10;

    private int offset;
    private int limit = PAGE_SIZE;
    private String propName;
    private Sort.Direction direction = Sort.Direction.DESC;

    private PageBuilder() {
    }

    public static PageBuilder newBuilder() {
        return new PageBuilder();
    }

    public PageBuilder offset(int offset) {
        this.offset = offset;
        return this;
    }

    public PageBuilder limit(int limit) {
        this.limit = limit;
        return this;
    }

    public PageBuilder order(String propName) {
        this.propName = propName;
        return this;
    }

    public PageBuilder desc() {
        this.direction = Sort.Direction.DESC;
        return this;
    }

    public PageBuilder asc() {
        this.direction = Sort.Direction.ASC;
        return this;
    }

    public PageRequest build() {
        int page = offset / PAGE_SIZE;
        if (propName != null) {
            Sort sort = new Sort(new Sort.Order(direction, propName));
            return new PageRequest(page, limit, sort);
        }
        return new PageRequest(page, limit);
    }

}
