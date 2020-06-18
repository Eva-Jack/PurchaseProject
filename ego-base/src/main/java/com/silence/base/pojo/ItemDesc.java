package com.silence.base.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "tb_item_desc")
public class ItemDesc {
    @TableId(type = IdType.INPUT)
    private long itemId;
    private String itemDesc;
    private Date created;
    private Date updated;
}
