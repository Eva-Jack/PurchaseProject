package com.example.ego.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ego.manager.service.ItemParamService;
import com.silence.base.mapper.ItemParamMapper;
import com.silence.base.pojo.ItemParam;
import com.silence.base.vo.EUDataGridResult;
import com.silence.base.vo.EgoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ItemParamServiceImpl extends ServiceImpl<ItemParamMapper, ItemParam> implements ItemParamService {

    @Autowired(required = false)
    private ItemParamMapper itemParamMapper;

    @Override
    public EUDataGridResult listAndPage(Integer index, Integer pageSize) {
        //第一步：创建返回对象
        EUDataGridResult result = new EUDataGridResult();
        //第二步：获得数据
        int start=(index-1)*pageSize;
        List<Map<String, Object>> maps = itemParamMapper.listItemParamAndItemCat(start, pageSize);
        //第三步：封装数据
        result.setRows(maps);
        //获得规格模板参数项的所有记录数
        result.setTotal(this.count());
        //第四步：返回数据
        return result;
    }

    @Override
    public EgoResult getByItemCatId(Long itemcatid) {
        //通过类目：返回第一条规格参数
        QueryWrapper<ItemParam> wrapper = new QueryWrapper<>();
        wrapper.eq("item_cat_id", itemcatid);
        List<ItemParam> itemParams = this.list(wrapper);
        if (itemParams != null && itemParams.size() > 0) {
            return EgoResult.ok(itemParams.get(0));
        } else {
            return  EgoResult.build(400,"找不到模板");
        }
    }

    @Override
    public EgoResult save(Long itemcatid, String paramData) {
        //第一步：构建实体类对象
        ItemParam itemParam=new ItemParam();
        itemParam.setParamData(paramData);
        itemParam.setItemCatId(itemcatid);
        itemParam.setCreated(new Date());
        itemParam.setUpdated(new Date());
        //第二步：插入实体类
        this.save(itemParam);
        //第三步：不报异常，返回成功
        return EgoResult.ok();
    }
}
