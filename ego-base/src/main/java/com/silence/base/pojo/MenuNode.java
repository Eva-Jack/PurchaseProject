package com.silence.base.pojo;

import lombok.Data;

import java.util.List;

@Data
public class MenuNode {
    private String u; //url
    private String n; //name
    private List<?> i;//子节点
}
