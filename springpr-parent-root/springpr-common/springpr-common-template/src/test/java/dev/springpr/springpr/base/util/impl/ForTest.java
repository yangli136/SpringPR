/* (C)2023 */
package dev.springpr.springpr.base.util.impl;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class ForTest {
    @Test
    void test() {
        Optional<Object> o =
                Optional.of(2).flatMap(f -> Optional.of(3).flatMap(s -> Optional.of(f + s)));
        if (o.isPresent()) {
            System.out.println(o.get());
        }
    }
}
