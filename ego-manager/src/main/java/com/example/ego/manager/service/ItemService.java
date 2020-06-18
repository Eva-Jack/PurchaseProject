package com.example.ego.manager.service;

import com.silence.base.pojo.Item;

import com.silence.base.vo.EUDataGridResult;
import com.silence.base.vo.EgoResult;

public interface ItemService {
    /**
     * 通过当前索引，从1开始的，已经每页记录，分页查询
     *
     * @param index
     * @param rows
     * @return
     */

    EUDataGridResult findByPage(int index, int rows);

    /**
     * 获取产品
     *
     * @param itemId
     * @return
     */
    Item getById(Long itemId);

    EgoResult save(Item item, String desc, String itemParams);
}
