package com.main.seleniumrpa.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class RokomariModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int Id;
    String bookname;
    String bookauthor;
    String rating;
    String mainprice;
    String offerprice;

    public RokomariModel(String bookname, String bookauthor, String rating, String mainprice, String offerprice) {
        this.bookname = bookname;
        this.bookauthor = bookauthor;
        this.rating = rating;
        this.mainprice = mainprice;
        this.offerprice = offerprice;
    }
}
