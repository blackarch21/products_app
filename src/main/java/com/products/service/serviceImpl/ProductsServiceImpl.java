package com.products.service.serviceImpl;

import com.products.io.entity.ProductsEntity;
import com.products.repository.ProductsRepository;
import com.products.service.ProductsService;
import com.products.shared.Utils;
import com.products.shared.dto.ProductsDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    Utils utils;

    @Override
    public List<ProductsDto> getProducts(int page, int limit) {
        ModelMapper modelMapper = new ModelMapper();
        List<ProductsDto> returnValue = new ArrayList<>();
        org.springframework.data.domain.Pageable pageable = PageRequest.of(page, limit);
        Page<ProductsEntity> productsPage = productsRepository.findAll(pageable);
        List<ProductsEntity> productsEntities = productsPage.getContent();

        for (ProductsEntity temp : productsEntities) {
            ProductsDto tempProduct = modelMapper.map(temp, ProductsDto.class);
            returnValue.add(tempProduct);
        }

        return returnValue;
    }

    @Override
    public ProductsDto createProduct(ProductsDto productsDto) {

        ModelMapper modelMapper = new ModelMapper();

        if (productsRepository.findByProductName(productsDto.getProductName()) != null)
            throw new RuntimeException("Record exists");
        String productId = utils.generateProductId(20);
        productsDto.setProductId(productId);
        ProductsEntity productsEntity = modelMapper.map(productsDto, ProductsEntity.class);
        ProductsEntity createdProduct = productsRepository.save(productsEntity);
        ProductsDto returnValue = modelMapper.map(createdProduct, ProductsDto.class);

        return returnValue;
    }

    @Override
    public ProductsDto findByProductId(String productId) {
        ModelMapper modelMapper = new ModelMapper();
        ProductsEntity productsEntity = productsRepository.findByProductId(productId);
        if(productsEntity == null)throw new RuntimeException("Product not found");
        ProductsDto returnValue = modelMapper.map(productsEntity, ProductsDto.class);

        return returnValue;
    }
}
