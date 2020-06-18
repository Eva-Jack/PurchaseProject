package com.example.ego.manager.controller;

import com.example.ego.manager.service.ItemParamService;
import com.silence.base.vo.EUDataGridResult;
import com.silence.base.vo.EgoResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/item/param")
@Slf4j
public class ItemParamController {

    @Autowired(required = false)
    private ItemParamService itemParamService;

    /**
     * 返回查询结构
     * @param page
     * @param rows
     * @return
     */
    @GetMapping(value = "/list")
    public EUDataGridResult list(Integer page, Integer rows){
        log.debug("查询规格参数列表");
        return  itemParamService.listAndPage(page,rows);
    }

    /**
     * 通过类目编号查询规格模板数据
     * @param itemcatid
     * @return
     */
    @GetMapping("/query/itemcatid/{itemcatid}")
    public EgoResult selectByCatId(@PathVariable Long itemcatid){
        try {
            log.debug("通过类型查询规格模板"+itemcatid);
            return  itemParamService.getByItemCatId(itemcatid);
        } catch (Exception e) {
            e.printStackTrace();
            return EgoResult.ok(null);
        }
    }


    /**
     * 增加类目模板
     * @param itemcatid 类目编号
     * @param paramData 模板规格项参数,JSON。由页面js构建好JSON数据传到到后台
     * @return
     */
    @PostMapping(value = "/save/{itemcatid}")
    public EgoResult save(@PathVariable Long itemcatid,String paramData){
        try {
            log.debug("类目编号："+itemcatid+"，保存规格模板："+paramData);
            return itemParamService.save(itemcatid,paramData);
        } catch (Exception e) {
            e.printStackTrace();
            return  EgoResult.build(400, "插入规格模板失败");
        }

    }

}
