package com.silence.base.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EgoResult {
    // 响应业务状态
    private Integer status;
    // 响应消息
    private String msg;
    // 响应中的数据
    private Object data;

    public EgoResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }


    //通过静态方法的方式，可以固定调用的参数组合行为。
    public static EgoResult build(Integer status, String msg, Object data) {
        return new EgoResult(status, msg, data);
    }

    public static EgoResult build(Integer status, String msg) {
        return new EgoResult(status, msg, null);
    }

    public static EgoResult ok(Object data) {
        return new EgoResult(data);
    }

    public static EgoResult ok() {
        return new EgoResult(null);
    }

}
