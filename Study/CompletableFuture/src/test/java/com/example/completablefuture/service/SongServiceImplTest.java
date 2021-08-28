package com.example.completablefuture.service;

import com.example.completablefuture.config.TaskConfig;
import com.example.completablefuture.repository.SongRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class SongServiceImplTest
{
    private static SongService songService;

    @BeforeAll
    static void beforeAll()
    {
        SongRepository songRepository = new SongRepository();
        songService = new SongServiceImpl(songRepository);
    }

    @Test
    @DisplayName("동기 블록킹 방식으로 가수 조회")
    void syncBlocking()
    {
        // given
        String expected = "IU";

        // when
        String singer = songService.getSinger("Good Day");
        log.info("가수 조회 완료");

        // then
        assertThat(singer).isEqualTo(expected);
    }

    @Test
    @DisplayName("비동기 블록킹 방식으로 가수 조회")
    void asyncBlocking()
    {
        // given
        String expected = "UI";

        // when
        CompletableFuture<String> future = songService.getSingerAsync("Bad Day");
        log.info("가수 조회 완료되지 않았지만 다른 작업 수행 가능");
        String singer = future.join(); // 블록킹 과정
        log.info("가수 조회 완료");

        // then
        assertThat(singer).isEqualTo(expected);
    }

    @Test
    @DisplayName("비동기 논블록킹 방식으로 가수 조회")
    void asyncNonBlocking()
    {
        // given
        String expected = "UX";

        // when
        CompletableFuture<Void> future = songService
            .getSingerAsync("Cold Day")
            .thenAccept(singer ->
            {
                log.info("콜백 메소드, 검색한 이름은 " + singer);
                assertThat(singer).isEqualTo(expected);
            });


        // then
        // main Thread 종료를 막기 위해서 추가. 없으면 콜백 메소드 결과 확인 불가
        assertNull(future.join());
    }

    @Test
    @DisplayName("비동기 논블록킹 방식으로 가수 조회, 콜백 연결")
    void asyncNonBlockingChainingSameThread()
    {
        // given
        String expected = "UX와 철수";

        // when
        CompletableFuture<Void> future = songService
            .getSingerAsync("Cold Day")
            .thenApply(singer ->
            {
                log.info("콜백 메소드, 검색한 이름은 " + singer);
                return singer + "와 철수";
            })
            .thenAccept(singer ->
            {
                log.info("콜백 메소드, 검색한 이름은 " + singer);
                assertThat(singer).isEqualTo(expected);
            });


        // then
        // main Thread 종료를 막기 위해서 추가. 없으면 콜백 메소드 결과 확인 불가
        assertNull(future.join());
    }

    @Test
    @DisplayName("비동기 논블록킹 방식으로 가수 조회, 콜백 연결하는데 다른 쓰레드로 실행")
    void asyncNonBlockingChainingDiffThread()
    {
        Executor executor = TaskConfig.executor;
        // given
        String expected = "UX와 철수";

        // when
        CompletableFuture<Void> future = songService
            .getSingerAsync("Cold Day")
            .thenApplyAsync(singer ->
            {
                log.info("콜백 메소드, 검색한 이름은 " + singer);
                return singer + "와 철수";
            }, executor)
            .thenAcceptAsync(singer ->
            {
                log.info("콜백 메소드, 검색한 이름은 " + singer);
                assertThat(singer).isEqualTo(expected);
            }, executor);

        // then
        // main Thread 종료를 막기 위해서 추가. 없으면 콜백 메소드 결과 확인 불가
        assertNull(future.join());
    }

    @Test
    @DisplayName("비동기 논블록킹 방식으로 병렬 처리, 약 1초")
    void asyncNonBlockingCombine()
    {
        // given
        String expected = "IU와 UI";

        // when
        CompletableFuture<String> future1 = songService.getSingerAsync("Good Day");
        CompletableFuture<String> future2 = songService.getSingerAsync("Bad Day");

        // then
        String combinedSinger = future1.thenCombine(future2, (a, b) -> a + "와 " + b).join();
        assertThat(expected).isEqualTo(combinedSinger);
    }
}