package com.products.repository;

import com.products.io.entity.ProductsEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends PagingAndSortingRepository<ProductsEntity, Long> {

    ProductsEntity findByProductName(String productName);
    ProductsEntity findByProductId(String productId);
}
