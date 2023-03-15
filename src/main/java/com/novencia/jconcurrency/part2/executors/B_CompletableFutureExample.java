package com.novencia.jconcurrency.part2.executors;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class B_CompletableFutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Integer> r = CompletableFuture.supplyAsync(() -> {
                    System.out.println("fetching primes...");
                    return List.of(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37);
                })
                .thenApplyAsync(primes -> {
                    System.out.println("applying calculations...");
                    return primes.stream().map(s -> s * 2).collect(Collectors.toList());
                })
                .get();
        System.out.println(r);

        // http client example
        HttpResponse<String> streamHttpResponse = HttpClient.newHttpClient()
                .sendAsync(
                        HttpRequest.newBuilder().uri(URI.create("https://postman-echo.com/get")).build(),
                        HttpResponse.BodyHandlers.ofString()
                )
                .get();
        System.out.println(streamHttpResponse.body());
    }
}
