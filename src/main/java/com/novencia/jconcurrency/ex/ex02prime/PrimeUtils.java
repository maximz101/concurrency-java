package com.novencia.jconcurrency.ex.ex02prime;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public final class PrimeUtils {
    private PrimeUtils() {
    }

    public static boolean isPrime(long num) {
        if (num <= 1) {
            return false;
        }
        return LongStream
                .iterate(2, i -> i <= Math.sqrt(num), i -> i + 1)
                .noneMatch(i -> num % i == 0);
    }

    public static List<Long> getPrimeList() {
        try {
            try (var lines = Files.lines(Path.of("src/main/java/com/novencia/jconcurrency/ex/ex02prime/input.txt"))) {
                return lines.map(Long::parseLong)
                        .toList();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void generateLargeNumber() {
        // generate an input.txt file containing 1000000 random numbers, one per line, with min=1_000_000_000
        var random = new Random();
        var nums = LongStream.range(0, 1_000L)
                .mapToObj(unused -> Long.toString(random.nextLong(1_000_000_000, Long.MAX_VALUE)))
                .collect(Collectors.toUnmodifiableSet());
        try {
            Files.write(Path.of("input.txt"), nums, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        generateLargeNumber();
    }
}
