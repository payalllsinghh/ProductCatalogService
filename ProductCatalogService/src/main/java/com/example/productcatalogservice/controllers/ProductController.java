package com.example.productcatalogservice.controllers;

import com.example.productcatalogservice.dtos.CategoryDto;
import com.example.productcatalogservice.dtos.ProductDto;
import com.example.productcatalogservice.models.Category;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.models.State;
import com.example.productcatalogservice.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController
{
    @Autowired
    @Qualifier("fkps")
    private IProductService productService1;

    @Autowired
    @Qualifier("sps")
    private IProductService productService2;

    @GetMapping
    public List<ProductDto> getAllProducts()
    {
        List<ProductDto> productDtos = new ArrayList<>();
        List<Product> products = productService2.getAllProducts();
        for(Product product : products)
        {
            productDtos.add(from(product));
        }
        return productDtos;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findProductById(@PathVariable Long id)
    {
        try {
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            if (id <= 0) {
                headers.add("called by", "bhudwak");
                throw new IllegalArgumentException("Please try with product Id > 0");
                //return new ResponseEntity<>(null, headers, HttpStatus.NOT_FOUND);
            }
            Product product = productService1.getProductById(id);
            headers.add("called by", "intelligent");
            if (product == null)
                return new ResponseEntity<>(null, headers, HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(from(product), headers, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
            Product input = productService2.save(from(productDto));
            return from(input);

    }

    private ProductDto from(Product product)
    {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImageUrl(product.getImageUrl());
        if(product.getCategory() != null)
        {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(product.getCategory().getId());
            categoryDto.setName(product.getCategory().getName());
            categoryDto.setDescription(product.getCategory().getDescription());
            productDto.setCategory(categoryDto);
        }
        return productDto;
    }

    @PutMapping("/{id}")
    public ProductDto replaceProduct(@PathVariable Long id, @RequestBody ProductDto request)
    {
        Product productRequest = from(request);
        Product product  = productService1.replaceProduct(id,productRequest);
        return  from(product);
    }

    private Product from(ProductDto productDto) {
        Product product = new Product();
        product.setCreatedAt(new Date());
        product.setLastUpdatedAt(new Date());
        product.setState(State.ACTIVE);
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        product.setDescription(productDto.getDescription());
        if(productDto.getCategory() != null) {
            Category category = new Category();
            category.setId(productDto.getCategory().getId());
            category.setName(productDto.getCategory().getName());
            product.setCategory(category);
        }
        return product;
    }
}
