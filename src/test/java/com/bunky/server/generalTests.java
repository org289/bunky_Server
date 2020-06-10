package com.bunky.server;

import com.bunky.server.Entity.Apartment;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class generalTests {

    @Test
    void helloTest() {
        List<String> symbols = Stream.of(Apartment.CurrencySymbol.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        System.out.println(symbols);
    }
}
