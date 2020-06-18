package com.example.rest.controller;

import com.example.rest.service.ItemCatService;
import com.silence.base.pojo.Menu;
import com.silence.base.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/item/cat/")
@Slf4j
public class ItemCatController {

    @Autowired(required = false)
    private ItemCatService itemCatService;

    @GetMapping(value = "/menu",produces= MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    public String getMenu(String callback){
        Menu menu = itemCatService.initMenu();
        String json = JsonUtils.objectToJson(menu);
        log.debug("菜单JSON字符串:"+json);
        String jsonpData=callback+"("+json+")";
        return  jsonpData;
    }
}
