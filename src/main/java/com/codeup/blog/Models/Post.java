package com.codeup.blog.Models;

import javax.persistence.*;

@Entity
@Table(name="posts")
public class Post {

    @Id @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String body;


   @ManyToOne
   @JoinColumn(name = "user_id")
   private User user;

    public Post(String title, String body, User user) {
        this.title = title;
        this.body = body;
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Post(long id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }



    public Post(){}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
