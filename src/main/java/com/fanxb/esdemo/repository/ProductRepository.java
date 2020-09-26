package com.fanxb.esdemo.repository;

import com.fanxb.esdemo.entity.Product;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductRepository extends Repository<Product,String> {
    List<Product> findByCid(Integer cid);
}
