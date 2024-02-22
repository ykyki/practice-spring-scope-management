package com.example.practice.spring.scope.management.domain.wait;

import com.example.practice.spring.scope.management.domain.common.random.RandomUtilService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class WaitApiServiceTest {

    @Autowired
    private WaitApiService waitApiService;

    @MockBean
    private RandomUtilService randomUtilService;

    private final static int RANDOM_TIME = 987;
    private final static int HIT_PERCENTAGE = 3;

    @Test
    void waitRandomTime_should_throw_exception_when_hit() {
        // given
        when(randomUtilService.naturalNumber(RANDOM_TIME)).thenReturn(13);
        when(randomUtilService.hit(HIT_PERCENTAGE)).thenReturn(true);

        // when
        var actual = assertThrows(
                RuntimeException.class,
                () -> waitApiService.waitRandomTime()
        );

        // then
        assertEquals("Unlucky random error", actual.getMessage());
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("provide_waitParallel_should_return_expected_value")
    void waitRandomTime_should_return_expected_value(int randomTime, String expected) {
        // given
        when(randomUtilService.naturalNumber(RANDOM_TIME)).thenReturn(randomTime);
        when(randomUtilService.hit(HIT_PERCENTAGE)).thenReturn(false);

        // when
        var actual = waitApiService.waitRandomTime();

        // then
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> provide_waitParallel_should_return_expected_value() {
        return Stream.of(
                Arguments.of(0, "0000 ms"),
                Arguments.of(1, "0001 ms"),
                Arguments.of(1000, "1000 ms")
        );
    }

    @Test
    void waitParallel_should_success_when_no_hit() {
        // given
        when(randomUtilService.naturalNumber(RANDOM_TIME)).thenReturn(13);
        when(randomUtilService.hit(HIT_PERCENTAGE)).thenReturn(false);

        // when
        var actual = waitApiService.waitParallel();

        // then
        assertEquals(10, actual.size());
    }

    @Test
    void waitParallel_should_throw_exception_when_hit() {
        // given
        when(randomUtilService.naturalNumber(RANDOM_TIME)).thenReturn(13);
        when(randomUtilService.hit(HIT_PERCENTAGE)).thenReturn(true);

        // when
        var actual = assertThrows(
                RuntimeException.class,
                () -> waitApiService.waitParallel()
        );

        // then
        // assertEquals("Unlucky random error", actual.getMessage());
        assertThat(actual.getMessage(), containsString("Unlucky random error"));
    }
}
