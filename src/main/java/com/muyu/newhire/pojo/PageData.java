package com.muyu.newhire.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@AllArgsConstructor
@Data
public class PageData<T> {
    public PageData(Page<T> page) {
        this(page.getContent(), page.getTotalElements());
    }

    private List<T> content;
    private long totalCount;
}
