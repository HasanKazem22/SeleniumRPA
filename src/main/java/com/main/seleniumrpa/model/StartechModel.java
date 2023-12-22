package com.main.seleniumrpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class StartechModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String processor;
    String ram;
    String graphics;
    String features;
    String price;

    public StartechModel(String processor, String ram, String graphics, String features, String price) {
        this.processor = processor;
        this.ram = ram;
        this.graphics = graphics;
        this.features = features;
        this.price = price;
    }
}
