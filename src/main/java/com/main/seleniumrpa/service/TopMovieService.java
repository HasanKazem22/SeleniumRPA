package com.main.seleniumrpa.service;

import com.main.seleniumrpa.model.TopMovieModel;
import com.main.seleniumrpa.repository.TopMovieRepository;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@AllArgsConstructor
public class TopMovieService {
    @Autowired
    private TopMovieRepository topMovieRepository;

    private static final String URL = "https://www.imdb.com/chart/top/?ref_=nv_mv_250";

    public List<TopMovieModel> topMovieModels;

    public List<String> descriptionLinkList;

    public void movieScrape() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        driver.get(URL);

        List<WebElement> movies = driver.findElements(By.cssSelector("li.ipc-metadata-list-summary-item"));
        System.out.println(movies.size());

        int movieCounter = 0;

        for (WebElement movieName : movies) {
            String name = movieName.findElement(By.cssSelector("h3")).getText().replaceFirst("\\d+\\.\\s*", "").trim();
            String year = movieName.findElement(By.cssSelector("div:nth-child(2) > span:nth-child(1)")).getText();
            String duration = movieName.findElement(By.cssSelector("div:nth-child(2) > span:nth-child(2)")).getText();
            String rated;

            try {
                rated = movieName.findElement(By.cssSelector("div:nth-child(2) > div:nth-child(2) > span:nth-child(3)")).getText();
            } catch (Exception e) {
                rated = null;
            }

            // Splitting and getting the first part
            String rating = movieName.findElement(By.cssSelector("span:nth-child(3) > div:nth-child(1) > span:nth-child(1)"))
                    .getText().split("\\s+")[0];

            // Splitting and getting the second part
            String voteCount = movieName.findElement(By.cssSelector("span:nth-child(3) > div:nth-child(1) > span:nth-child(1)"))
                    .getText().split("\\s+")[1].replaceAll("[()]", "");

            String descriptionLink = movieName.findElement(By.cssSelector("div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > a:nth-child(1)"))
                    .getAttribute("href");


            System.out.println("Name: " + name);
            System.out.println("Year: " + year);
            System.out.println("Duration: " + duration);
            System.out.println("Rated: " + rated);
            System.out.println("Rating: " + rating);
            System.out.println("VoteCount: " + voteCount);
            System.out.println("DescriptionLink: " + descriptionLink);

            topMovieModels.add(new TopMovieModel(name, year, duration, rated, rating, voteCount, descriptionLink));

            movieCounter++;

            if (movieCounter >= 10) {
                break; // Exit the loop after printing the first 10 movies
            }
        }

        // Process each description link separately
        for (TopMovieModel movie : topMovieModels) {
            driver.get(movie.getDescription());
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,3500)");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

            String description;
            try {
                description = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".sc-9eebdf80-1 > div:nth-child(1)"))).getText();
            } catch (Exception e) {
                description = "Time out or other exception";
            }

            System.out.println("Description: " + description);

            movie.setDescription(description);
        }

        // Save all TopMovieModel instances to the repository
        topMovieRepository.saveAll(topMovieModels);

        // Quit the WebDriver
        driver.quit();
    }
}