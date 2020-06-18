package com.silence.base.vo;

import lombok.Data;

import java.util.List;

@Data
public class EUDataGridResult {
    private long total;
    private List<?> rows;
}
