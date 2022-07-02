package com.example.javaspring.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;

    @Length(message = "En az 3 en fazla 40 karakter girebilirsiniz", min = 3, max = 40)
    @Column(length = 40)
    @NotBlank
    private String title;

    @Column(length = 500)
    private String detail;

    @PositiveOrZero(message = "Eksi(-) veya 10Tl nin altında değer girilemez")
    @Min(message = "Tutar 10 Tl den küçük olamaz", value = 10)
    private Double price;

}
