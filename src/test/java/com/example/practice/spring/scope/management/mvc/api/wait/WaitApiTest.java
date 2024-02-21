package com.example.practice.spring.scope.management.mvc.api.wait;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WaitApiTest {

    @Autowired
    MockMvc mockMvc;

    private final static String PATH_PARALLEL = "/api/wait/parallel";

    // TODO 確率的なテストにならないようにする
    @Disabled
    @RepeatedTest(value = 10, failureThreshold = 5)
    void invoke_should_return_status_ok() throws Exception {
        // when
        var result = mockMvc
                .perform(get(PATH_PARALLEL))
                .andDo(print());

        // then
        result.andExpect(status().isOk());
    }
}
