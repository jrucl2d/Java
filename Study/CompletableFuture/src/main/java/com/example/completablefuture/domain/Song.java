package com.example.completablefuture.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Song
{
    private final String title;
    private final String singer;

    @Builder
    private Song(String title, String singer)
    {
        this.title = title;
        this.singer = singer;
    }
}
