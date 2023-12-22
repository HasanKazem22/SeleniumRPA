package com.main.seleniumrpa.controller;


import com.main.seleniumrpa.service.RokomariService;
import com.main.seleniumrpa.service.StartechService;
import com.main.seleniumrpa.service.TopMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RpaController {
    @Autowired
    RokomariService rokomariService;

    @Autowired
    StartechService startechService;

    @Autowired
    TopMovieService topMovieService;

    @GetMapping("/SeleniumRpa/rokomari")
    public void ScrapRokomari() {
        rokomariService.rokomariScrape();
    }

    @GetMapping("/SeleniumRpa/startech")
    public void ScrapStartech() {
        startechService.startechScrape();
    }

    @GetMapping("/SeleniumRpa/imdb")
    public void ScrapImdb() {
        topMovieService.movieScrape();
    }
}
