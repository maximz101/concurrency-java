package com.novencia.jconcurrency.part2.executors;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class A_ExetcutorsFutureExample {
    public static void main(String[] args) throws InterruptedException {

        try (var executorService = Executors.newFixedThreadPool(4)) {
            var future = executorService.submit(() -> {
                Thread.sleep(1000);
                return "Hello World!";
            });

            // future.cancel(false);
            var result = future.get(2000L, TimeUnit.MILLISECONDS);

            System.out.println(result);

            executorService.shutdown();
        } catch (ExecutionException | TimeoutException e) {
            throw new RuntimeException(e);
        }

        new Scanner(System.in).nextLine();
    }
}
