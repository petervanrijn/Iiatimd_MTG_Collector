package com.example.mtgcollection.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CardDao {
    //Insert query
    @Insert(onConflict = OnConflictStrategy.REPLACE)

    void insert(Card card);

    //Delete query
    @Delete
    void delete(Card card);

    //Delete all query
    @Delete
    void reset(List<Card> card);

    //update query
//    void update(int id, String name, String generic_mana, String type, String type_name, int power, int toughness,String image, String set );


    //delete all entiries
    @Query("DELETE FROM table_card")
    public void nukeTable();

    //get all data
    @Query("SELECT * FROM table_card WHERE user_email LIKE :user_email  ORDER BY id ASC")
    List<Card> getAllCards(String user_email);

    //get all data that is in possession
    @Query("SELECT * FROM table_card")
    List<Card> getAll();

}
