package com.example.productcatalogservice.services;

import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.repos.iProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("sps")
public class StorageProductService implements IProductService
{
    @Autowired
    private iProductRepo productRepo;

    @Override
    public Product getProductById(Long id) {
        Optional<Product> product = productRepo.findProductById(id);
        if(product.isEmpty())
            return null;
        return product.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product replaceProduct(Long id, Product request) {
        return null;
    }

    @Override
    public Product save(Product request) {
        return productRepo.save(request);
    }
}
