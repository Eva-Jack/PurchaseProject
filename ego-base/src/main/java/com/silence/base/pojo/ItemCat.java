package com.silence.base.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "tb_item_cat")
public class ItemCat {
    @TableId(type = IdType.AUTO)
    private long id;
    private long parentId;
    private String name;
    private Integer status;
    private Integer sortOrder;
    private Integer isParent;
    private Date created;
    private Date updated;
}
