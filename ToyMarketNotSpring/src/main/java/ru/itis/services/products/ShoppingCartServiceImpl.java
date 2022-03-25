package ru.itis.services.products;

import ru.itis.dto.ProductsForm;
import ru.itis.dto.ProfileProductForm;
import ru.itis.models.Product;
import ru.itis.repository.ProductsRepository;

import java.util.List;

import static ru.itis.dto.ProductsForm.from;

public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ProductsRepository productsRepository;

    public ShoppingCartServiceImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public List<ProductsForm> getAllProducts(String email) {
        return from(productsRepository.findAllInShoppingCart(email));
    }

    @Override
    public ProductsForm getProduct(Integer id) {
        Product product = productsRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return from(product);
    }

    @Override
    public void dropProduct(ProfileProductForm form) {
        productsRepository.dropProductCart(form);
    }

    @Override
    public void addProductToList(ProfileProductForm form){
        productsRepository.addToList(form);
    }

    @Override
    public boolean isNewProductInList(ProfileProductForm form) {
        return productsRepository.productIsNewInList(form).isPresent();
    }

    @Override
    public Integer sumAllCostProduct(String email) {
        return productsRepository.sumCostByEmail(email).orElseThrow(IllegalArgumentException::new);
    }

}
