package com.heima.pojo;

import lombok.Data;

@Data
public class ClazzQuery {
    private String name;
    private String begin;
    private String end;
    private Integer page;
    private Integer pageSize;
}
