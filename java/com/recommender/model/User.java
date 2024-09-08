package com.recommender.model;

import javax.persistence.*;

/*
    @User entity
    @param: user id , username
    @type param: int, String
    @methods : getters,setters
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Userid;
    private String Username;
    private String Useremail;
    //private String email;

    /*  public User(int Userid, String Username) {
        this.Userid = Userid;
        this.Username = Username;
    }*/
    public User() {}

    public User(String username,  String userEmail) {
        this.Username = username;
        this.Useremail = userEmail;
    }

    public int getUserid() {
        return Userid;
    }

    public String getUsername(){
        return Username;
    }

    public void setUserid(int Userid) {
        this.Userid = Userid;
    }

    public void setUsername(String Username){
        this.Username = Username;
    }

    public String getUseremail(){
        return Useremail;
    }
    public void setUseremail(String Useremail){
        this.Useremail = Useremail;
    }

}
