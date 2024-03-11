package com.muyu.newhire.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class PageableUtil {

    public static PageRequest of(int pageNumber, int pageSize) {
        return of(pageNumber, pageSize, Sort.unsorted());
    }

    public static PageRequest of(int pageNumber, int pageSize, Sort sort) {
        if (pageSize <= 0) {
            throw new IllegalArgumentException("pageSize must be a positive integer.");
        }
        if (pageNumber <= 0) {
            throw new IllegalArgumentException("pageNumber must be a positive integer.");
        }
        return PageRequest.of(pageNumber - 1, pageSize, sort);
    }

    public static PageRequest of(int pageNumber, int pageSize, Sort.Direction direction, String... properties) {
        return of(pageNumber, pageSize, Sort.by(direction, properties));
    }

}
