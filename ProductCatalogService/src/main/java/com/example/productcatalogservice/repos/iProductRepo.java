package com.example.productcatalogservice.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.productcatalogservice.models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface iProductRepo extends JpaRepository<Product, Long>
{
    Optional<Product> findProductById(Long id);
    Product save(Product request);
    List<Product> findAll();

    List<Product> findProductByOrderByPrice();

    @Query("Select p.name from Product p where p.id=?1")
    String findProductTitleById(Long pid);

    @Query("SELECT c.name from Category c join Product p on p.category.id=c.id where p.id=:pid")
    String findCategoryNameFromProductId(Long pid);

}
