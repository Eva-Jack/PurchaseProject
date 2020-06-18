package com.example.ego.manager.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.ego.manager.service.ItemService;
import com.silence.base.mapper.ItemDescMapper;
import com.silence.base.mapper.ItemMapper;
import com.silence.base.mapper.ItemParamItemMapper;
import com.silence.base.pojo.Item;
import com.silence.base.pojo.ItemDesc;
import com.silence.base.pojo.ItemParamItem;
import com.silence.base.utils.IDUtils;
import com.silence.base.vo.EUDataGridResult;
import com.silence.base.vo.EgoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired(required = false)
    private ItemMapper itemMapper;

    @Autowired(required = false)
    private ItemDescMapper itemDescMapper;
    @Autowired(required = false)
    private ItemParamItemMapper itemParamItemMapper;

    @Override
    public EUDataGridResult findByPage(int index, int rows) {
        //第一步：创建返回对象
        EUDataGridResult result = new EUDataGridResult();
        //第二步：获得数据,分页查询
        Page<Item> page = itemMapper.selectPage(new Page<Item>(index, rows), null);
        //第三步：封装数据
        result.setTotal(page.getTotal());
        result.setRows(page.getRecords());
        //第四步：返回数据
        return result;
    }

    @Override
    public Item getById(Long itemId) {
        return itemMapper.selectById(itemId);
    }

    @Override
    public EgoResult save(Item item, String desc, String paramData) {
        //第一步：封装数据
        long itemId = IDUtils.genItemId();
        //第二步：封装数据
        //商品数据
        item.setId(itemId);
        item.setCreated(new Date());
        item.setUpdated(item.getCreated());
        item.setStatus(1);
        //详情数据
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(item.getCreated());
        itemDesc.setUpdated(item.getCreated());

        //规格值数据
        ItemParamItem itemParamItem = new ItemParamItem();
        itemParamItem.setItemId(itemId);
        itemParamItem.setParamData(paramData);
        itemParamItem.setCreated(item.getCreated());
        itemParamItem.setUpdated(item.getCreated());


        //第二步：插入数据
        this.itemMapper.insert(item);
        this.itemDescMapper.insert(itemDesc);
        this.itemParamItemMapper.insert(itemParamItem);


        //第三步：返回结果

        return EgoResult.ok();
    }


}
