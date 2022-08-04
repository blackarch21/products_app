package com.products.ui.controller;

import com.products.service.ProductsService;
import com.products.shared.dto.ProductsDto;
import com.products.ui.request.ProductsDetailsRequestModel;
import com.products.ui.response.ProductsRest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("products")
public class ProductsController {

    @Autowired
    ProductsService productsService;

    @GetMapping
    public List<ProductsRest> getProducts(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "limit", defaultValue = "20") int limit) {

        ModelMapper modelMapper = new ModelMapper();
        List<ProductsRest> returnValue = new ArrayList<>();
        List<ProductsDto> productsDtos = productsService.getProducts(page, limit);
        for (ProductsDto temp : productsDtos) {
            ProductsRest productsRest = modelMapper.map(temp, ProductsRest.class);
            returnValue.add(productsRest);
        }

        return returnValue;
    }

    @GetMapping(path = "/{productId}")
    public ProductsRest findByProductId(@PathVariable String productId){
        ModelMapper modelMapper = new ModelMapper();
        ProductsDto productsDto = productsService.findByProductId(productId);
        ProductsRest returnValue = modelMapper.map(productsDto, ProductsRest.class);

        return returnValue;
    }

    @PostMapping
    public ProductsRest createProducts(@RequestBody ProductsDetailsRequestModel productsDetails) {

        ModelMapper modelMapper = new ModelMapper();
        ProductsDto productsDto = modelMapper.map(productsDetails, ProductsDto.class);
        ProductsDto createdProduct = productsService.createProduct(productsDto);
        ProductsRest returnValue = modelMapper.map(createdProduct, ProductsRest.class);

        return returnValue;

    }

    @PutMapping(path = "/{productId}")
    public ProductsRest updateProduct(@PathVariable String productId,@RequestBody ProductsDetailsRequestModel productsDetails){

        ModelMapper modelMapper= new ModelMapper();
        ProductsDto productsDto = modelMapper.map(productsDetails, ProductsDto.class);

        ProductsDto createdProduct = productsService.updateUser(productId,productsDto);
        ProductsRest returnValue = modelMapper.map(createdProduct, ProductsRest.class);
        return returnValue;
    }
}
