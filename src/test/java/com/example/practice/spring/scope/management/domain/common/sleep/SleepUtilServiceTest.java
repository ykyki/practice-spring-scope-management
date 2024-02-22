package com.example.practice.spring.scope.management.domain.common.sleep;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

@SpringBootTest
class SleepUtilServiceTest {

    @Autowired
    private SleepUtilService sleepUtilService;

    @Test
    void sleep() {
        // when
        var startTime = System.currentTimeMillis();
        sleepUtilService.sleep(1000);
        var endTime = System.currentTimeMillis();

        // then
        assertThat(endTime - startTime, greaterThanOrEqualTo(1000L));
    }
}
