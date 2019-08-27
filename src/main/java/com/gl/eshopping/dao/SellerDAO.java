package com.gl.eshopping.dao;

import com.gl.eshopping.model.Shop;
import com.gl.eshopping.model.Seller;

import java.util.List;

public interface SellerDAO extends CrudDAO<Seller, Long> {
    Seller saveShops(Long sellerId, List<Shop> shops);

    Seller deleteShops(Long sellerId, List<Long> shopIds);
}
