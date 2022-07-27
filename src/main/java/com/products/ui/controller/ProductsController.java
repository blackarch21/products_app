package com.products.ui.controller;

import com.products.service.ProductsService;
import com.products.shared.dto.ProductsDto;
import com.products.ui.request.ProductsDetailsRequestModel;
import com.products.ui.response.ProductsRest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("products")
public class ProductsController {

    @Autowired
    ProductsService productsService;

    @PostMapping
    public ProductsRest createProducts(@RequestBody ProductsDetailsRequestModel productsDetails){

        ModelMapper modelMapper = new ModelMapper();
        ProductsDto productsDto = modelMapper.map(productsDetails , ProductsDto.class);
        ProductsDto createdProduct = productsService.createProduct(productsDto);
        ProductsRest returnValue = modelMapper.map(createdProduct, ProductsRest.class);

        return returnValue;

    }
}
