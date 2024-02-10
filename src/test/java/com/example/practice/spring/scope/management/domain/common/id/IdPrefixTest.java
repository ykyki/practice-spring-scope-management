package com.example.practice.spring.scope.management.domain.common.id;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class IdPrefixTest {
    @ParameterizedTest(name = "{0}")
    @MethodSource("provide_formatWithEightDigits_ShouldBe_ExpectedValue")
    void formatWithEightDigits_ShouldBe_ExpectedValue(String caseName, IdPrefix idPrefix, long number, String expected) {
        // when
        var actual = idPrefix.formatWithEightDigits(number);

        // then
        Assertions.assertEquals(expected, actual);
    }

    private static Stream<Arguments> provide_formatWithEightDigits_ShouldBe_ExpectedValue() {
        return Stream.of(
                Arguments.of("ゼロ", IdPrefix.USER, 0L, "USR00000000"),
                Arguments.of("最小値", IdPrefix.USER, 1L, "USR00000001"),
                Arguments.of("8桁", IdPrefix.USER, 12345678L, "USR12345678"),
                Arguments.of("最大値", IdPrefix.USER, 99999999L, "USR99999999")
        );
    }

    @Test
    void formatWithEightDigits_ShouldThrow_Exception_ForNegativeNumber() {
        // given
        var idPrefix = IdPrefix.USER;
        var number = -1L;

        // when
        var exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> idPrefix.formatWithEightDigits(number)
        );

        //then
        Assertions.assertEquals("number must be non-negative", exception.getMessage());
    }
}
