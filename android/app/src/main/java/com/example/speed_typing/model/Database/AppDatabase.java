package com.example.speed_typing.model.Database;

import com.example.speed_typing.model.Util.Scores;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Scores.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ScoresDao scoresDao();

}
