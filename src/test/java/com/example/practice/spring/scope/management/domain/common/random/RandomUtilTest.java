package com.example.practice.spring.scope.management.domain.common.random;

import com.example.practice.spring.scope.management.util.random.RandomUtil;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RandomUtilTest {

    @Autowired
    private RandomUtil randomUtil;

    @RepeatedTest(5)
    void hit_0_should_never_hit() {
        // when
        var actual = randomUtil.hit(0);

        // then
        assertFalse(actual);
    }

    @RepeatedTest(5)
    void hit_100_should_always_hit() {
        // when
        var actual = randomUtil.hit(100);

        // then
        assertTrue(actual);
    }

    @ParameterizedTest
    @ValueSource(ints = {-100, -1, 101, 1000})
    void hit_illegal_arg_should_throw_exception(int percentage) {
        // when
        var actual = assertThrows(
                IllegalArgumentException.class,
                () -> randomUtil.hit(percentage)
        );


        // then
        assertEquals("percentage must be between 0 and 100", actual.getMessage());
    }

    @RepeatedTest(3)
    void randomNaturalNumber_0_should_return_0() {
        // when
        var actual = randomUtil.naturalNumber(0);

        // then
        assertEquals(0, actual);
    }

    @RepeatedTest(5)
    void randomNaturalNumber_1_should_return_0_or_1() {
        // when
        var actual = randomUtil.naturalNumber(1);

        // then
        assertTrue(actual == 0 || actual == 1);
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 101, 12345})
    void randomNaturalNumber_should_return_int_between_0_to_max(int max) {
        // when
        var actual = randomUtil.naturalNumber(max);

        // then
        assertThat(actual, greaterThanOrEqualTo(0));
        assertThat(actual, lessThanOrEqualTo(max));
    }


    @ParameterizedTest
    @ValueSource(ints = {-1, -100})
    void randomNaturalNumber_illegal_arg_should_throw_exception(int max) {
        // when
        var actual = assertThrows(
                IllegalArgumentException.class,
                () -> randomUtil.naturalNumber(max)
        );

        // then
        assertEquals("max must be non-negative", actual.getMessage());
    }
}
