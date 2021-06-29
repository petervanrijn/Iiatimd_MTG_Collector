package com.example.mtgcollection.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface CardDao {
    //Insert query
    @Insert(onConflict = REPLACE)
    void insert(Card card);

    //Delete query
    @Delete
    void delete(Card card);

    //Delete all query
    @Delete
    void reset(List<Card> card);

    //update query
//    void update(int id, String name, String generic_mana, String type, String type_name, int power, int toughness,String image, String set );

    //get all data
    @Query("SELECT * FROM table_card")
    List<Card> getAll();
}
