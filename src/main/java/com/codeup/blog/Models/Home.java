package com.codeup.blog.Models;


import javax.persistence.*;

@Entity
@Table(name = "homes")
public class Home {

    @Id@GeneratedValue
    private long id;

    @Column(nullable = false, name = "number_of_bedrooms")
    private int numberOfBedrooms;

    @Column(nullable = false, name ="asking_price")
    private long askingPrice;

    @Column(nullable = false)
    private int zipcode;

    @Column(nullable = false)
    private String address;
}
