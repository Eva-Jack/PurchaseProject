package com.example.ego.manager.controller;

import com.example.ego.manager.service.ItemService;
import com.silence.base.pojo.Item;
import com.silence.base.vo.EUDataGridResult;
import com.silence.base.vo.EgoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/item")
public class ItemController {

    @Autowired(required = false)
    private ItemService itemService;

    @GetMapping(value = "/{itemId}")
    public Item getItemById(@PathVariable Long itemId) {
        return itemService.getById(itemId);
    }

    @PostMapping(value = "/save")
    public EgoResult save(Item item, String desc, String itemParams) {
        try {
            return itemService.save(item, desc, itemParams);
        } catch (Exception e) {
            e.printStackTrace();
            return EgoResult.build(400, "保存商品失败");
        }
    }

    /**
     * 分页查询
     *
     * @param page
     * @param rows
     * @return
     */
    @GetMapping(value = "/list")
    public EUDataGridResult list(Integer page, Integer rows) {
        return itemService.findByPage(page, rows);
    }


}
