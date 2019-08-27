package com.gl.eshopping.dao.impl;

import com.gl.eshopping.exception.OrderProductFoundException;
import com.gl.eshopping.model.OrderProduct;
import com.gl.eshopping.repository.OrderProductRepository;
import com.gl.eshopping.dao.OrderProductDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
;

import java.util.List;

@Slf4j
@Service
public class OrderProductDAOImpl implements OrderProductDAO {
    private OrderProductRepository orderProductRepository;

    @Autowired
    public OrderProductDAOImpl(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    @Override
    public List<OrderProduct> findAll() {
        log.debug("Getting Order Products from DAO");
        return orderProductRepository.findAll();
    }

    @Override
    public OrderProduct findById(Long orderProductId) {
        log.debug("Getting Order Product By Id from DAO");
        return orderProductRepository.findById(orderProductId).orElseThrow(() -> new OrderProductFoundException(orderProductId));
    }

    @Override
    public OrderProduct save(OrderProduct newOrderProduct) {
        log.debug("Saving Order Products from DAO");
        return orderProductRepository.save(newOrderProduct);
    }

    public OrderProduct update(OrderProduct orderProduct, Long orderProductId) {
        log.debug("Updating Order Product from DAO");
        return null;
    }

    @Override
    public void delete(OrderProduct orderProduct) {
        log.debug("Deleting Order Product from DAO");
        orderProductRepository.delete(orderProduct);
    }

    @Override
    public void deleteById(Long orderProductId) {
        log.debug("Deleting Order Product By Id from DAO");
        orderProductRepository.deleteById(orderProductId);
    }
}
