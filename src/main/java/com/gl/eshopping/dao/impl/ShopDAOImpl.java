package com.gl.eshopping.dao.impl;

import com.gl.eshopping.exception.ShopNotFoundException;
import com.gl.eshopping.model.Product;
import com.gl.eshopping.model.Order;
import com.gl.eshopping.model.Shop;
import com.gl.eshopping.repository.ProductRepository;
import com.gl.eshopping.repository.ShopRepository;
import com.gl.eshopping.dao.ShopDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ShopDAOImpl implements ShopDAO {
    private ShopRepository shopRepository;
    private ProductRepository productRepository;

    @Autowired
    public ShopDAOImpl(ShopRepository shopRepository, ProductRepository productRepository) {
        this.shopRepository = shopRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Shop> findAll() {
        log.debug("Getting Shops from DAO.");
        return shopRepository.findAll();
    }

    @Override
    public Shop findById(Long shopId) {
        log.debug("Getting Shop By Id from DAO.");
        return shopRepository.findById(shopId).orElseThrow(() -> new ShopNotFoundException(shopId));
    }

    @Override
    public Shop save(Shop newShop) {
        log.debug("Saving Shop from DAO.");
        return shopRepository.save(newShop);
    }

    public Shop update(Shop shop, Long shopId) {
        log.debug("Updating Shop from DAO.");
        return null;
    }

    @Override
    public void delete(Shop shop) {
        log.debug("Deleting Shop from DAO.");
        shopRepository.delete(shop);
    }

    @Override
    public void deleteById(Long shopId) {
        log.debug("Deleting Shop By Id from DAO.");
        shopRepository.deleteById(shopId);
    }

    @Override
    public Shop addProducts(Long shopId, @org.jetbrains.annotations.NotNull List<Product> products) {
        Shop shop = findById(shopId);
        log.debug("Adding Product to Shop "+ shop.getName() + " from DAO. ");
        products.stream().forEach(product -> {
            product.setShop(shop);
            productRepository.save(product);
        });
        return shop;
    }

    @Override
    public Shop removeProducts(Long shopId, List<Long> productIds) {
        Shop shop = findById(shopId);
        log.debug("Removing Products from Shop" + shop.getName() + " from DAO. ");
        List<Product> products = productRepository.findAllById(productIds);
        productRepository.deleteAll(products);
        return shop;
    }

    @Override
    public List<Order> getShopOrders(Long shopId) {
        Shop shop = shopRepository.findById(shopId).orElseThrow(() -> new ShopNotFoundException(shopId));
        return shop.getOrders().stream().collect(Collectors.toList());
    }
}
