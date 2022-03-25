package ru.itis.services.products;

import ru.itis.dto.ProductsForm;
import ru.itis.dto.ProfileProductForm;

import java.util.List;

public interface WishListService {
    List<ProductsForm> getAllProducts(String email);

    ProductsForm getProduct(Integer id);

    void dropProduct(ProfileProductForm form);

    void addProductToCart(ProfileProductForm form);

    boolean isNewProductInCart(ProfileProductForm form);
}
