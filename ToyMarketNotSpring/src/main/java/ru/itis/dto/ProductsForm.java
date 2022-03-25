package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.Product;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductsForm {
    private Integer id;
    private String name;
    private String picture;
    private Integer cost;

    public static ProductsForm from(Product product) {
        return ProductsForm.builder()
                .id(product.getId())
                .name(product.getName())
                .picture(product.getPicture())
                .cost(product.getCost())
                .build();
    }

    public static List<ProductsForm> from(List<Product> products) {
        return products.stream().map(ProductsForm::from).collect(Collectors.toList());
    }
}

