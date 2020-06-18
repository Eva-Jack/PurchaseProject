package com.example.ego.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.ego.manager.service.ItemCatService;
import com.silence.base.mapper.ItemCatMapper;
import com.silence.base.pojo.ItemCat;
import com.silence.base.vo.EUTreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired(required = false)
    ItemCatMapper itemCatMapper;

    @Override
    public List<EUTreeNode> findNodeByParentId(Long parentId) {
        //第一步：创建返回的数据对象
        List<EUTreeNode> nodes = new ArrayList<>();
        //第二步：获得数据库的数据
        QueryWrapper<ItemCat> qw = new QueryWrapper<>();
        qw.eq("parent_id", parentId);
        List<ItemCat> itemCats = itemCatMapper.selectList(qw);
        //第三获取完数据封装
        if (!CollectionUtils.isEmpty(itemCats)) {
            for (Object obj : itemCats) {
                EUTreeNode node = new EUTreeNode();
                ItemCat itemCat = (ItemCat) obj;
                node.setId(itemCat.getId());
                node.setText(itemCat.getName());
                //判断是否是枝节点获得根节点。（父节点）
                if (itemCat.getIsParent() == 1) {
                    //如果判断是一个父节点（文件夹类型），将节点状态设置为折叠状态。因为只有折叠状态，到时才可以点击列出它的子节点
                    node.setState("closed");
                } else {
                    //如果是一个叶节点，直接是展开状态
                    node.setState("open");
                }
                nodes.add(node);
            }
        }

        return nodes;
    }
}
