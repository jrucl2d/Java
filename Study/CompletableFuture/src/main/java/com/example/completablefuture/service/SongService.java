package com.example.completablefuture.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public interface SongService
{
    String getSinger(String title);
    CompletableFuture<String> getSingerAsync(String title);
    Future<String> getSingerDetailAsync(String title);
}
