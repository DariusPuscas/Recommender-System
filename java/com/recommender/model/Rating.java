package com.recommender.model;

import javax.persistence.*;

/*
    @Rating entity
    @param: user id, item id, rating
    @type param: int
    @methods : getters,setters
 */
@Entity
@Table(name = "ratings")
public class Rating {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private int rating;


    public Rating() {}

    public Rating(User user, Item item, int rating) {
        this.user = user;
        this.item = item;
        this.rating = rating;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;

    }
    public User getUser() {
        return user;

    }
    public void setUser(User user) {
        this.user = user;
    }
    public Item getItem() {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }
    public int getRating() {
        return rating;

    }
    public void setRating(int rating) {
        this.rating = rating;

    }

}
