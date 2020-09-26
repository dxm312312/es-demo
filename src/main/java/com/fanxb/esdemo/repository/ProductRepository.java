package com.fanxb.esdemo.repository;

import com.fanxb.esdemo.entity.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Component;

import java.util.List;
//默认 Repository 可以根据需要选择不同的  Repository 如CrudRepository
@Component
public interface ProductRepository extends PagingAndSortingRepository<Product,Integer> {
    List<Product> findByCid(Integer cid);

    List<Product> findByCategoryName(String categoryName, Sort sort);

  //  List<Product> findById(Integer id);
}
