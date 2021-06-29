package com.example.mtgcollection.data;

// add database entities

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Card.class}, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    //create database instance
    private static RoomDB database;
    //define database name
    private static String DATABASE_NAME = "MTG_COLLECTION";

    public synchronized static RoomDB getInstance(Context context){
        //cehck condition
        if(database == null){
            //when database is null
            //initialize database
            database = Room.databaseBuilder(context.getApplicationContext(), RoomDB.class,DATABASE_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return database;
    }

    //create Dao
    public abstract CardDao cardDao();
}