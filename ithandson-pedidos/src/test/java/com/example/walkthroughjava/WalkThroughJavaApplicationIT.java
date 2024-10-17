package com.example.walkthroughjava;

import com.example.walkthroughjava.config.Config;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class WalkThroughJavaApplicationIT {
    @Autowired
    Config config;

    @Test
    void contextLoads() {
        System.out.println(config.getConfigMessage());
    }

}
