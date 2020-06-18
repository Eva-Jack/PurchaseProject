package com.example.ego.manager.service;

import com.silence.base.vo.EUTreeNode;

import java.util.List;

public interface ItemCatService {

    public List<EUTreeNode> findNodeByParentId(Long parentId);

}
