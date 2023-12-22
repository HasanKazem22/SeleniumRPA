package com.main.seleniumrpa.service;

import com.main.seleniumrpa.model.StartechModel;
import com.main.seleniumrpa.repository.StartechRepository;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class StartechService {
    @Autowired
    public StartechRepository startechRepository;
    private static final String URL = "https://www.startech.com.bd/msi-gaming-laptop";

    public List<StartechModel> startechModels = new ArrayList<>();

    public void startechScrape() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        driver.get(URL);

        List<WebElement> product = driver.findElements(By.cssSelector("div.p-item"));
        System.out.println(product.size());

        for (WebElement pn : product) {
            String processor = pn.findElement(By.cssSelector("ul:nth-child(1) > li:nth-child(1)")).getText().split(":")[1].trim();
            String ram = pn.findElement(By.cssSelector("ul:nth-child(1) > li:nth-child(2)")).getText().split(":")[1].trim();
            String graphics = pn.findElement(By.cssSelector("ul:nth-child(1) > li:nth-child(3)")).getText().split(":")[1].trim();
            String features = pn.findElement(By.cssSelector("ul:nth-child(1) > li:nth-child(4)")).getText().split(":")[1].trim();
            String price = pn.findElement(By.cssSelector("div:nth-child(3) > span:nth-child(1)")).getText();

            System.out.println("Processor: " + processor);
            System.out.println("Ram: " + ram);
            System.out.println("Graphics: " + graphics);
            System.out.println("Features: " + features);
            System.out.println("Status: " + price);

            startechModels.add(new StartechModel(processor, ram, graphics,features, price));
        }
        startechRepository.saveAll(startechModels);

        driver.quit();
    }
}