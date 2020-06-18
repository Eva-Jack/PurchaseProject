package com.example.rest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.rest.service.ItemCatService;
import com.silence.base.mapper.ItemCatMapper;
import com.silence.base.pojo.ItemCat;
import com.silence.base.pojo.Menu;
import com.silence.base.pojo.MenuNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired(required = false)
    ItemCatMapper itemCatMapper;
    @Override
    public Menu initMenu() {
        //第一步：创建返回对象
        Menu menu=new Menu();
        //第二步：获得的数据,data需要使用递归遍历商品类目表构建，传入的条件是，parent_id

        List<Object> data=this.findAllByParentId(0L);
        //第三步：封装数据
        menu.setData(data);
        //第四步：返回数据
        return menu;
    }

    @Override
    public List<Object> findAllByParentId(long parentId) {
        //第一步：创建返回对象
        List<Object> nodes=new ArrayList<>();
        //第二步：获得数据
        QueryWrapper<ItemCat> wrapper=new QueryWrapper<>();
        wrapper.eq("parent_id",  parentId);
        List<ItemCat> itemCats = itemCatMapper.selectList(wrapper);
        //第三：封装数据
        if (!CollectionUtils.isEmpty(itemCats)) {
            for (ItemCat itemCat : itemCats) {
                //判断，该节点是否有下一级节点。有就继续递归。没有就输出字符串
                if (itemCat.getIsParent()==1) {
                    MenuNode node = new MenuNode();
                    node.setU("/products/" + itemCat.getId() + ".html");
                    node.setN("<a href='/products/" + itemCat.getId() + ".html'>" + itemCat.getName() + "</a>");
                    //将当前节点的ID作为父节点继续递归
                    node.setI(this.findAllByParentId(itemCat.getId()));
                    nodes.add(node);
                }else{
                    nodes.add("/products/"+itemCat.getId()+".html|"+itemCat.getName());
                }
            }

            return nodes;
        }

        return Collections.EMPTY_LIST;
    }
}
