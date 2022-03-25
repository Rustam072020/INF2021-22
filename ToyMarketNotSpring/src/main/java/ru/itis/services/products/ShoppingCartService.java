package ru.itis.services.products;

import ru.itis.dto.ProductsForm;
import ru.itis.dto.ProfileProductForm;

import java.util.List;

public interface ShoppingCartService {
    List<ProductsForm> getAllProducts(String email);

    ProductsForm getProduct(Integer id);

    void dropProduct(ProfileProductForm form);

    void addProductToList(ProfileProductForm form);

    boolean isNewProductInList(ProfileProductForm form);

    Integer sumAllCostProduct(String email);
}
