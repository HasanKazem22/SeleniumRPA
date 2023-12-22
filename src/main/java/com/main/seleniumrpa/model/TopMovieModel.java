package com.main.seleniumrpa.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "IMDB_Top_Movie_List")
public class TopMovieModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String year;
    private String duration;
    private String rated;
    private String rating;
    private String voteCount;
    @Column(length = 3000)
    private String description;

    public TopMovieModel(String name, String year, String duration, String rated, String rating, String voteCount, String description) {
        this.name = name;
        this.year = year;
        this.duration = duration;
        this.rated = rated;
        this.rating = rating;
        this.voteCount = voteCount;
        this.description = description;
    }
}
