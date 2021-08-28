package com.example.completablefuture.repository;

import com.example.completablefuture.domain.Song;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class SongRepository
{
    private static final Map<String, Song> db = new ConcurrentHashMap<>();

    public SongRepository()
    {
        db.put("Good Day", Song.builder().title("Good Day").singer("IU").build());
        db.put("Bad Day", Song.builder().title("Bad Day").singer("UI").build());
        db.put("Cold Day", Song.builder().title("Cold Day").singer("UX").build());
    }

    public String getSingerByTitle(String title)
    {
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        return db.get(title).getSinger();
    }
}
