package com.recommender.model;

import javax.persistence.*;

/*
    @Item entity
    @param: item id , item title
    @type param: int, String
    @methods : getters,setters
 */
@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;
    private String itemTitle;
    private String name;
    //private String itemDescription;

 /*   public Item(int itemId, String itemTitle) {
        this.itemId = itemId;
        this.itemTitle = itemTitle;
    }*/
    public Item() {}

    public Item(String name, String itemTitle) {
        this.name = name;
        //this.itemId = itemId;
        this.itemTitle = itemTitle;
    }

    public int getItemId() {
        return itemId;
    }
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
    public String getItemTitle() {
        return itemTitle;
    }
    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }
}
