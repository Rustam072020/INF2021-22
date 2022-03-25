package ru.itis.services.products;

import ru.itis.dto.ProductsForm;
import ru.itis.dto.ProfileProductForm;
import ru.itis.models.Product;

import java.util.List;

public interface CatalogService {
    List<ProductsForm> getAllProducts();

    ProductsForm getProduct(Integer id);

    void addProductToCart(ProfileProductForm form);

    void addProductToList(ProfileProductForm form);

    boolean isNewProductInCart(ProfileProductForm form);

    boolean isNewProductInList(ProfileProductForm form);

    List<ProductsForm> getAllProductsDecreasing();

    List<ProductsForm> getAllProductsIncreasing();

    List<ProductsForm> getAllProductsNew();
}
