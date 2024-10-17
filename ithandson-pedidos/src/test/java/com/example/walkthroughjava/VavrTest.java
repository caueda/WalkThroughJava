package com.example.walkthroughjava;

import io.vavr.API;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VavrTest {
    @Test
    public void testSnickThrows() {
        Exception exception = assertThrows(Exception.class, () ->
            API.unchecked(() -> {
                return 1/0;
            }).get()
        );
        assertTrue(exception instanceof ArithmeticException);
    }

    @Test
    public void stream() {
        List<List<?>> b = List.of(List.of(1,2,3), List.of(4,5,6));
        var result = b.stream()
                .flatMap(r -> r.stream())
                .map(num -> (Integer)num).collect(Collectors.toList());
        System.out.println(result);
    }

    @Test
    public void testBooleanCheck() {
        List<Boolean> booleans = List.of(Boolean.TRUE, Boolean.TRUE, Boolean.TRUE);
        var isValid = booleans.stream().collect(Collectors.reducing(Boolean.TRUE, Boolean::logicalAnd));
        assertTrue(isValid);
    }
}
