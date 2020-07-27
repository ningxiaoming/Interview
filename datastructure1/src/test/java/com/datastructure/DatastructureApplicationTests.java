package com.datastructure;

import com.controller.AopController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DatastructureApplicationTests {

    @Autowired
    private AopController aopController;
    @Test
    void contextLoads() {
        aopController.Curry();
    }

}
