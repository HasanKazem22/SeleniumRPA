package com.main.seleniumrpa.service;

import com.main.seleniumrpa.model.RokomariModel;
import com.main.seleniumrpa.repository.RokomariRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class RokomariService {
    @Autowired
    RokomariRepository rokomariRepository;
    private static final String URL = "https://www.rokomari.com/book/category/86/Quran-and-Tafseer";

    List<RokomariModel> rokomariModels;

    public void rokomariScrape() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        driver.get(URL);

        List<WebElement> product = driver.findElements(By.cssSelector("div:nth-child(1) > a:nth-child(1) > div:nth-child(2)"));
        System.out.println(product.size());

        for(WebElement rp : product) {
            String bookname = rp.findElement(By.cssSelector("h4:nth-child(1)")).getText();
            String bookauthor = rp.findElement(By.cssSelector("p:nth-child(2)")).getText();
            String rating = rp.findElement(By.cssSelector("span:nth-child(6)")).getText();

            String mainprice = null;
            String offerprice = null;
            String priceElement = null;
            try {
                priceElement = rp.findElement(By.cssSelector("p[class=\"book-price\"]")).getText();

                mainprice = priceElement.split(" ")[0] + priceElement.split(" ")[1];
                offerprice = priceElement.split(" ")[2] + priceElement.split(" ")[3];
            } catch (Exception e){
                priceElement = rp.findElement(By.cssSelector("p[class=\"book-price\"]")).getText();

                mainprice = priceElement.split(" ")[0] + priceElement.split(" ")[1];
            }


            System.out.println("Book Name: " + bookname);
            System.out.println("Book Author: " + bookauthor);
            System.out.println("Rating: " + rating);
            System.out.println("Main Price: " + mainprice);
            System.out.println("Offer Price: " + offerprice);

            rokomariModels.add(new RokomariModel(bookname, bookauthor, rating, mainprice, offerprice));
        }
        rokomariRepository.saveAll(rokomariModels);

        driver.quit();
    }
}