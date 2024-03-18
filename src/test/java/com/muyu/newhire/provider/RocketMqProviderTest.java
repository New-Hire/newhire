package com.muyu.newhire.provider;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 真实测试
 */
@SpringBootTest
class RocketMqProviderTest {

    @Autowired
    private RocketMqProvider rocketMqProvider;

    @Test
    void send() {
        rocketMqProvider.send("test2");
    }
}