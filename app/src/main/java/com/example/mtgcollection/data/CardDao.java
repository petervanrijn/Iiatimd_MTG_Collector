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
    @Insert(onConflict = OnConflictStrategy.IGNORE)
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

    //get all card that are in possession
    @Query("SELECT * FROM table_card WHERE user_email LIKE :user_email AND inPossession = '1' ORDER BY id ASC")
    List<Card> getAllInpossessionCards(String user_email);

    //get all data that is in possession
    @Query("SELECT * FROM table_card")
    List<Card> getAll();

    //get all data that is in possession
    @Query("SELECT COUNT(id) FROM table_card WHERE inPossession = '1'")
    int getAllCollected();

    //get all data that is in possession
    @Query("SELECT COUNT(id)  FROM table_card WHERE inPossession = '1' AND CardSet = 'AetherRevolt'")
    public abstract int getAetherRevoltCollected();

    @Query("SELECT COUNT(id)  FROM table_card WHERE inPossession = '1' AND CardSet = 'EldritchMoon'")
    public abstract int getEldritchmoonCollected();

    //update card to in possession
    @Query("UPDATE table_card SET inPossession = '1' WHERE id LIKE :card_id")
    public abstract void setInPossession(int card_id);

    //update card to not in possession
    @Query("UPDATE table_card SET inPossession = '0' WHERE id LIKE :card_id")
    public abstract void setNotInPossession(int card_id);
}
