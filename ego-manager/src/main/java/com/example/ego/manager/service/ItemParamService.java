package com.example.ego.manager.service;

import com.silence.base.vo.EUDataGridResult;
import com.silence.base.vo.EgoResult;

public interface ItemParamService {

   EUDataGridResult listAndPage(Integer page, Integer rows);

   EgoResult getByItemCatId(Long itemcatid);

   EgoResult save(Long itemcatid, String paramData);
}
