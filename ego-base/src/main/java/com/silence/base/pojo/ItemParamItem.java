package com.silence.base.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "tb_item_param_item")
public class ItemParamItem {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long itemId;
    private String paramData;
    private Date created;
    private Date updated;
}
