package com.eorion.bo.enhancement.processdraft.domain.commom;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageInfo<T> {
    private Long total;
    private List<T> data;
}
