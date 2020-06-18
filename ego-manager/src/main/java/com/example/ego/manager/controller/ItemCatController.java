package com.example.ego.manager.controller;

import com.example.ego.manager.service.ItemCatService;
import com.silence.base.vo.EUTreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/item/cat/")
public class ItemCatController {

    @Autowired(required = false)
    ItemCatService itemCatService;

    /**
     * 通过父类目编号查询子子节点类目
     *
     * @param id
     * @return
     */
    @PostMapping(value = "/list")
    public List<EUTreeNode> findByParentId(@RequestParam(defaultValue = "0") Long id) {
        return itemCatService.findNodeByParentId(id);
    }

}
