package com.products.service.serviceImpl;

import com.products.io.entity.ProductsEntity;
import com.products.repository.ProductsRepository;
import com.products.service.ProductsService;
import com.products.shared.Utils;
import com.products.shared.dto.ProductsDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    Utils utils;

    @Override
    public ProductsDto createProduct(ProductsDto productsDto) {

        ModelMapper modelMapper = new ModelMapper();

        if(productsRepository.findByProductName(productsDto.getProductName()) != null)
            throw new RuntimeException("Record exists");
        String productId = utils.generateProductId(20);
        productsDto.setProductId(productId);
        ProductsEntity productsEntity = modelMapper.map(productsDto, ProductsEntity.class);
        ProductsEntity createdProduct = productsRepository.save(productsEntity);
        ProductsDto returnValue = modelMapper.map(createdProduct, ProductsDto.class);

        return returnValue;
    }
}
