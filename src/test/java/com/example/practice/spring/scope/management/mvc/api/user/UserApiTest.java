package com.example.practice.spring.scope.management.mvc.api.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserApiTest {

    @Autowired
    MockMvc mockMvc;

    private final static String PATH = "/api/user";

    @Nested
    @DisplayName("/")
    class PathDefault {
        @Test
        void invoke_GET_should_return_status_ok() throws Exception {
            // when
            var result = mockMvc
                    .perform(get(PATH))
                    .andDo(print());

            // then
            result.andExpect(status().isOk());
        }

        @RepeatedTest(3)
        void invoke_POST_should_return_status_ok() throws Exception {
            // when
            var result = mockMvc
                    .perform(post(PATH)
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                            .param("userNameForm", "Alice")
                    )
                    .andDo(print());

            // then
            result.andExpect(status().isOk());
            result.andExpect(jsonPath("$.userId").value(startsWith("USR")));
            result.andExpect(jsonPath("$.userName").doesNotExist());
        }

        // TODO add validation test
    }

    @Nested
    @DisplayName("/greet")
    class PathGreet {
        @Test
        void invoke_GET_should_return_status_ok() throws Exception {
            // when
            var result = mockMvc
                    .perform(get(PATH + "/greet"))
                    .andDo(print());

            // then
            result.andExpect(status().isMethodNotAllowed());
        }
    }
}
