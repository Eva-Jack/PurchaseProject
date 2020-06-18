package com.example.ego.manager;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.silence.base.mapper.ItemMapper;
import com.silence.base.pojo.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EgoManagerApplicationTests {
    @Autowired(required = false)
    private ItemMapper itemMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void findByPage() {
        Page<Item> page = itemMapper.selectPage(new Page(1, 5), null);
        if (null != page) {
            System.out.println("中也属：" + page.getTotal());
        }
    }
}
