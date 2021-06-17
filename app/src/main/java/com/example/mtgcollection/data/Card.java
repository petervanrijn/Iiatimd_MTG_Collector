package com.example.mtgcollection.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Card {

    @ColumnInfo
    private String name;

    @ColumnInfo
    private int generic_mana;

    @ColumnInfo
    private String type;

    @ColumnInfo
    private String type_name;

    @ColumnInfo
    private int power;

    @ColumnInfo
    private int toughness;

    @ColumnInfo
    private String image;

    @ColumnInfo
    private String set;

    @PrimaryKey
    private int id;


    public Card(String name, int generic_mana, String type, String type_name, int power, int toughness, String image, String set, int id){
        this.name = name;
        this.generic_mana = generic_mana;
        this.type = type;
        this.type_name = type_name;
        this.power = power;
        this.toughness = toughness;
        this.image = image;
        this.set = set;
    }

    public String getName() {
        return this.name;
    }
    public int getGeneric_mana() {
        return this.generic_mana;
    }
    public String getType() {
        return this.type;
    }
    public String getType_name() {
        return this.type_name;
    }
    public int getPower() {
        return this.power;
    }
    public int getToughness() {
        return this.toughness;
    }
    public String getImage() {
        return this.image;
    }
    public String getSet() {
        return this.set;
    }
    public int getId() {
        return this.id;
    }

}
