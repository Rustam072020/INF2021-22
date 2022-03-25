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
public class WishListServiceImpl implements WishListService {

    private final ProductsRepository productsRepository;

    @Autowired
    public WishListServiceImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public List<ProductsForm> getAllProducts(String email) {
        return from(productsRepository.findAllInWishList(email));
    }

    @Override
    public ProductsForm getProduct(Integer id) {
        Product product = productsRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return from(product);
    }

    @Override
    public void dropProduct(ProfileProductForm form) {
        productsRepository.dropProductWishList(form);
    }

    @Override
    public void addProductToCart(ProfileProductForm form){
        productsRepository.addToCart(form);
    }

    @Override
    public boolean isNewProductInCart(ProfileProductForm form) {
        return productsRepository.productIsNewInCart(form).isPresent();
    }

}
