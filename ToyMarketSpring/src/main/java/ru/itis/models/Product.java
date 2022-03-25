package ru.itis.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Product extends ArrayList<Product> {
    private Integer id;
    private String name;
    private String picture;
    private Integer cost;
}
