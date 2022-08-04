package com.products.service;

import com.products.shared.dto.ProductsDto;

import java.util.List;

public interface ProductsService {
    List<ProductsDto> getProducts(int page, int limit);
    ProductsDto createProduct(ProductsDto productsDto);
    ProductsDto findByProductId(String productId);
    ProductsDto updateUser(String productId,ProductsDto productsDto);
}
