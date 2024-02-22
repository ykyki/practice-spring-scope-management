package com.example.practice.spring.scope.management.domain.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class UserIdTest {
    @ParameterizedTest
    @MethodSource("provide_getIdAsString_should_return_expected_value")
    void getIdAsString_should_return_expected_value(String caseName, long number, String expected) {
        // given
        var userId = new UserId(number);

        // when
        var actual = userId.getIdAsString();

        // then
        Assertions.assertEquals(expected, actual);
    }

    private static Stream<Arguments> provide_getIdAsString_should_return_expected_value() {
        return Stream.of(
                Arguments.of("ゼロ", 0L, "USR00000000"),
                Arguments.of("最小値", 1L, "USR00000001"),
                Arguments.of("8桁", 12345678L, "USR12345678"),
                Arguments.of("最大値", 99999999L, "USR99999999")
        );
    }
}
