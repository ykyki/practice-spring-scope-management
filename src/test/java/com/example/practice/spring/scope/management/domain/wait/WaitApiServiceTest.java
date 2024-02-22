package com.example.practice.spring.scope.management.domain.wait;

import com.example.practice.spring.scope.management.domain.common.random.RandomUtilService;
import com.example.practice.spring.scope.management.domain.common.sleep.SleepUtilService;
import io.vavr.control.Try;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class WaitApiServiceTest {

    @Autowired
    private WaitApiService waitApiService;

    @MockBean
    private RandomUtilService randomUtilServiceMock;

    @MockBean
    private SleepUtilService sleepUtilServiceMock;

    private final static int RANDOM_TIME = 987;
    private final static int HIT_PERCENTAGE = 3;

    @BeforeEach
    void beforeEach() {
        doNothing().when(sleepUtilServiceMock).sleep(anyInt());
    }

    @Test
    void waitRandomTime_should_throw_exception_when_hit() {
        // given
        when(randomUtilServiceMock.naturalNumber(RANDOM_TIME)).thenReturn(13);
        when(randomUtilServiceMock.hit(HIT_PERCENTAGE)).thenReturn(true);

        // when
        var actual = assertThrows(
                RuntimeException.class,
                () -> waitApiService.waitRandomTime()
        );

        // then
        assertEquals("Unlucky random error", actual.getMessage());
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void waitRandomTime_should_sleep(boolean hit) {
        // given
        when(randomUtilServiceMock.naturalNumber(RANDOM_TIME)).thenReturn(999);
        when(randomUtilServiceMock.hit(HIT_PERCENTAGE)).thenReturn(hit);

        // when
        Try.of(() -> waitApiService.waitRandomTime());

        // then
        verify(sleepUtilServiceMock, times(1)).sleep(999);
    }

    @ParameterizedTest
    @MethodSource("provide_waitParallel_should_return_expected_value")
    void waitRandomTime_should_return_expected_value(int randomTime, String expected) {
        // given
        when(randomUtilServiceMock.naturalNumber(RANDOM_TIME)).thenReturn(randomTime);
        when(randomUtilServiceMock.hit(HIT_PERCENTAGE)).thenReturn(false);

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
        when(randomUtilServiceMock.naturalNumber(RANDOM_TIME)).thenReturn(13);
        when(randomUtilServiceMock.hit(HIT_PERCENTAGE)).thenReturn(false);

        // when
        var actual = waitApiService.waitParallel();

        // then
        assertEquals(10, actual.size());
    }

    @Test
    void waitParallel_should_throw_exception_when_hit() {
        // given
        when(randomUtilServiceMock.naturalNumber(RANDOM_TIME)).thenReturn(13);
        when(randomUtilServiceMock.hit(HIT_PERCENTAGE)).thenReturn(true);

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
