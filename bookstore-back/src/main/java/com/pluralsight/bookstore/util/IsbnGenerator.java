package com.pluralsight.bookstore.util;

public class IsbnGenerator implements NumberGenerator {
    @Override
    public String generateNumber() {
        return "13-5677-" + (1000000 + (int) (Math.random() * 899999));
    }
}
