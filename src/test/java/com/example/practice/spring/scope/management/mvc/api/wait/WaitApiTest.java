package com.example.practice.spring.scope.management.mvc.api.wait;

import com.example.practice.spring.scope.management.util.random.RandomUtil;
import com.example.practice.spring.scope.management.util.sleep.SleepUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WaitApiTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private RandomUtil randomUtilMock;

    @MockBean
    private SleepUtil sleepUtilMock;

    private final static String PATH_PARALLEL = "/api/wait/parallel";

    @BeforeEach
    void beforeEach() {
        doNothing().when(sleepUtilMock).sleep(anyInt());
    }

    @RepeatedTest(3)
    void invoke_should_return_status_ok_when_no_hit() throws Exception {
        // given
        when(randomUtilMock.naturalNumber(anyInt())).thenReturn(1);
        when(randomUtilMock.hit(anyInt())).thenReturn(false);

        // when
        var result = mockMvc
                .perform(get(PATH_PARALLEL))
                .andDo(print());

        // then
        result.andExpect(status().isOk());
    }

    @Test
    void invoke_should_return_status_internal_server_error_when_hit() throws Exception {
        // given
        when(randomUtilMock.naturalNumber(anyInt())).thenReturn(1);
        when(randomUtilMock.hit(anyInt())).thenReturn(true);

        // when
        var result = mockMvc
                .perform(get(PATH_PARALLEL))
                .andDo(print());

        // then
        result.andExpect(status().isInternalServerError());
    }
}
