package com.example.walkthroughjava;

import io.vavr.API;
import org.junit.jupiter.api.Test;

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
}
