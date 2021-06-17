package com.example.mtgcollection.data;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CardDao {
    @Query("Select * FROM card")
    List<Card> getAll();

}
