package ru.itis.repository;

import ru.itis.dto.ProfileProductForm;
import ru.itis.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductsRepository {

    List<Product> findAllInCatalogDecreasing();

    List<Product> findAllInCatalogIncreasing();

    List<Product> findAllInCatalogNew();

    public Optional<Product> findById(Integer id);
    public List<Product> findAllInShoppingCart(String email);
    public List<Product> findAllInWishList(String email);

    void dropProductCart(ProfileProductForm form);

    void dropProductWishList(ProfileProductForm form);

    void addToCart(ProfileProductForm form);

    void addToList(ProfileProductForm form);

    Optional<ProfileProductForm> productIsNewInCart(ProfileProductForm form);

    Optional<ProfileProductForm> productIsNewInList(ProfileProductForm form);

    Optional<Integer> sumCostByEmail(String email);
}
