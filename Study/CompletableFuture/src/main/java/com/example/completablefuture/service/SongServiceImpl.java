package com.example.completablefuture.service;

import com.example.completablefuture.config.TaskConfig;
import com.example.completablefuture.repository.SongRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
@Service
public class SongServiceImpl implements SongService
{
    private final SongRepository songRepository;
    private final Executor executor;

    @Autowired
    public SongServiceImpl(SongRepository songRepository)
    {
        this.songRepository = songRepository;
        this.executor = Executors.newFixedThreadPool(2);
    }

    @Override
    public String getSinger(String title)
    {
        log.info("동기 방식으로 가수 조회");
        return songRepository.getSingerByTitle(title);
    }

    @Override
    public CompletableFuture<String> getSingerAsync(String title)
    {
        log.info("비동기 방식으로 가수 조회");

        return CompletableFuture.supplyAsync(() ->
        {
            log.info("비동기 실행");
            return songRepository.getSingerByTitle(title);
        }, executor);
    }

    @Override
    public Future<String> getSingerDetailAsync(String title)
    {
        return null;
    }
}
