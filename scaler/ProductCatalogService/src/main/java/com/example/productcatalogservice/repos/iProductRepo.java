package com.example.productcatalogservice.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.productcatalogservice.models.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface iProductRepo extends JpaRepository<Product, Long>
{

}
