package com.example.speed_typing.model.Database;

import com.example.speed_typing.model.Util.Scores;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ScoresDao {
    @Query("SELECT * FROM scores")
    List<Scores> getAll();

    @Query("SELECT * FROM scores WHERE name LIKE :name")
    List<Scores> getByName(String name);

    @Insert
    void insertAll(Scores scores);
}
