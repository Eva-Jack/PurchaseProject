package com.silence.base.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "tb_item_param")
public class ItemParam {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    // @TableField(value = "item_cat_id")
    private Long itemCatId;
    // @TableField(value = "param_data")
    private String paramData;
    private Date created;
    private Date updated;
}
