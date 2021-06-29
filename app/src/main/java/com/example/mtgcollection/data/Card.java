package com.example.mtgcollection.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
    //create table name
    @Entity(tableName = "table_card")
    public class Card implements Serializable {
    //primary key
    @PrimaryKey(autoGenerate = true)
    private int id;

    //create columns
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "generic_mana")
    private String generic_mana;
    @ColumnInfo(name = "type")
    private String type;
    @ColumnInfo(name = "type_name")
    private String type_name;
    @ColumnInfo(name = "power")
    private int power;
    @ColumnInfo(name = "toughness")
    private int toughness;
    @ColumnInfo(name = "image")
    private String image;
    @ColumnInfo(name = "set")
    private String set;


    public Card(int id,String name, String generic_mana, String type, String type_name, int power,int toughness,String image, String set){
        this.id = id;
        this.name = name;
        this.generic_mana = generic_mana;
        this.type = type;
        this.type_name = type_name;
        this.power = power;
        this.toughness = toughness;
        this.image = image;
        this.set = set;
    }
    @Ignore
    public Card() {

    }

    // create the getters
    public void setId(int id) {
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGeneric_mana() {
        return generic_mana;
    }

    public void setGeneric_mana(String generic_mana) {
        this.generic_mana = generic_mana;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getToughness() {
        return toughness;
    }

    public void setToughness(int toughness) {
        this.toughness = toughness;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

}

