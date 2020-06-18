package com.silence.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.silence.base.pojo.ItemParam;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ItemParamMapper extends BaseMapper<ItemParam> {
    @Select("select p.id,c.name as itemCatName, p.param_data as paramData,p.created ,p.updated from tb_item_param p left join tb_item_cat c on p.item_cat_id=c.id limit ${start},${size}")
    List<Map<String,Object>> listItemParamAndItemCat(@Param("start") int start, @Param("size") int size);
}
