package com.example.rest.service;

import com.silence.base.pojo.Menu;

import java.util.List;

public interface ItemCatService {
    /**
     * 初始化，返回商品类目构建的菜单的数据结构
     * @return
     */
    Menu initMenu();

   List<Object> findAllByParentId(long parentId);
}
