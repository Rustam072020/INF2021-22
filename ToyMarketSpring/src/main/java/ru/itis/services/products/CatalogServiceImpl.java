package ru.itis.services.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.dto.ProductsForm;
import ru.itis.dto.ProfileProductForm;
import ru.itis.models.Product;
import ru.itis.repository.ProductsRepository;

import java.util.List;

import static ru.itis.dto.ProductsForm.from;

@Service
public class CatalogServiceImpl implements CatalogService {

    private final ProductsRepository productsRepository;

    @Autowired
    public CatalogServiceImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public List<ProductsForm> getAllProducts() {
        return from(productsRepository.findAllInCatalogDecreasing());
    }

    @Override
    public ProductsForm getProduct(Integer id) {
        Product product = productsRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return from(product);
    }

    @Override
    public void addProductToCart(ProfileProductForm form){
        productsRepository.addToCart(form);
    }

    @Override
    public void addProductToList(ProfileProductForm form){
        productsRepository.addToList(form);
    }

    @Override
    public boolean isNewProductInCart(ProfileProductForm form) {
        return productsRepository.productIsNewInCart(form).isPresent();
    }

    @Override
    public boolean isNewProductInList(ProfileProductForm form) {
        return productsRepository.productIsNewInList(form).isPresent();
    }

    @Override
    public List<ProductsForm> getAllProductsDecreasing() {
        return from(productsRepository.findAllInCatalogDecreasing());
    }

    @Override
    public List<ProductsForm> getAllProductsIncreasing() {
        return from(productsRepository.findAllInCatalogIncreasing());
    }

    @Override
    public List<ProductsForm> getAllProductsNew() {
        return from(productsRepository.findAllInCatalogNew());
    }
}
