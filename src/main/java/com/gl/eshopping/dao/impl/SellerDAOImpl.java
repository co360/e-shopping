package com.gl.eshopping.dao.impl;

import com.gl.eshopping.exception.ShopNotFoundException;
import com.gl.eshopping.exception.SellerNotFoundException;
import com.gl.eshopping.model.Seller;
import com.gl.eshopping.model.Shop;
import com.gl.eshopping.repository.SellerRepository;
import com.gl.eshopping.repository.ShopRepository;
import com.gl.eshopping.dao.SellerDAO;
import com.gl.eshopping.vo.GeneralDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SellerDAOImpl implements SellerDAO {
    private SellerRepository sellerRepository;
    private ShopRepository shopRepository;

    @Contract(pure = true)
    @Autowired
    public SellerDAOImpl(SellerRepository sellerRepository, ShopRepository shopRepository) {
        this.sellerRepository = sellerRepository;
        this.shopRepository = shopRepository;
    }

    @Override
    public List<Seller> findAll() {
        log.debug("Getting Sellers from DAO.");
        return sellerRepository.findAll();
    }

    @Override
    public Seller findById(Long sellerId) {
        log.debug("Getting Seller by Id from DAO.");
        return sellerRepository.findById(sellerId).orElseThrow(() -> new SellerNotFoundException(sellerId));
    }

    @Override
    public List<Shop> findAllShops(Long sellerId) {
        Seller seller = findById(sellerId);
        return seller.getShops().stream().collect(Collectors.toList());
    }

    @Override
    public Seller save(Seller newSeller) {
        log.debug("Saving Seller from DAO.");
        return sellerRepository.save(newSeller);
    }

    @Override
    public Seller update(GeneralDetailVO generalDetailVO, Long sellerId) {
        log.debug("Updating Seller from DAO.");
        Seller seller = findById(sellerId);
        seller.setName(generalDetailVO.getName());
        seller.setEmail(generalDetailVO.getEmail());
        seller.setPhoneNo(generalDetailVO.getPhoneNo());
        return sellerRepository.saveAndFlush(seller);
    }

    @Override
    public Shop updateShopDetails(GeneralDetailVO generalDetailVO, Long sellerId, Long shopId) {
        Shop shop = shopRepository.findById(shopId).orElseThrow(() -> new ShopNotFoundException(shopId));
        Seller seller = findById(sellerId);
        if (!seller.getShops().contains(shop)) {
            throw new IllegalArgumentException("You don't own shop: " + shop.getName());
        }
        shop.setName(generalDetailVO.getName());
        shop.setEmail(generalDetailVO.getEmail());
        shop.setPhoneNo(generalDetailVO.getPhoneNo());
        return shopRepository.saveAndFlush(shop);
    }


    @Override
    public void delete(Seller seller) {
        log.debug("Deleting Seller from DAO.");
        sellerRepository.delete(seller);
    }

    @Override
    public void deleteById(Long sellerId) {
        log.debug("Deleting Seller by Id from DAO.");
        sellerRepository.deleteById(sellerId);
    }

    @Override
    public Seller saveShops(Long sellerId, @NotNull List<Shop> shops) {
        log.debug("Adding Seller to Shops and Vice Versa from DAO.");
        Seller seller = findById(sellerId);
        shops.stream().forEach(shop -> {
            shop.setSeller(seller);
            shopRepository.save(shop);
        });
        return sellerRepository.save(seller);
    }

    @Override
    public Seller deleteShops(Long sellerId, List<Long> shopIds) {
        log.debug("Removing Shops from Seller and Vice Versa from DAO.");
        Seller seller = findById(sellerId);
        List<Shop> shops = shopRepository.findAllById(shopIds);
        shopRepository.deleteAll(shops);
//        shops.stream().forEach(shopRepository::delete);
        return seller;
    }
}
