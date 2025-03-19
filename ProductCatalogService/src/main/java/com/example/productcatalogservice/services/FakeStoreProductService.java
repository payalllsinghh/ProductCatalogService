package com.example.productcatalogservice.services;

import com.example.productcatalogservice.clients.FakeStoreApiClient;
import com.example.productcatalogservice.dtos.FakeStoreProductDto;
import com.example.productcatalogservice.models.Category;
import com.example.productcatalogservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fkps")
public class FakeStoreProductService implements IProductService
{
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @Autowired
    private FakeStoreApiClient fakeStoreApiClient;

    @Override
    public Product getProductById(Long productId)
    {
        FakeStoreProductDto fakeStoreProductDto = fakeStoreApiClient.getProductById(productId);
        if( fakeStoreProductDto != null)
        {
            return from(fakeStoreProductDto);
        }
        return null;
    }

    private Product from(FakeStoreProductDto fakeStoreProductDto)
    {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setName(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImageUrl(fakeStoreProductDto.getImage());
        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }

    @Override
    public List<Product> getAllProducts()
    {
        List<Product> products = new ArrayList<>();
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> listResponseEntity =
                restTemplate.getForEntity("http://fakestoreapi.com/products", FakeStoreProductDto[].class);

        for(FakeStoreProductDto fakeStoreProductDto : listResponseEntity.getBody())
        {
            products.add(from(fakeStoreProductDto));
        }
        return products;
    }

    @Override
    public Product replaceProduct(Long productId, Product request)
    {
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto fakeStoreProductDto = from(request);
        FakeStoreProductDto response =
                requestForEntity("http://fakestoreapi.com/products/{productId}",HttpMethod.PUT,
                        fakeStoreProductDto, FakeStoreProductDto.class, productId).getBody();
        return from(response);
    }

    @Override
    public Product save(Product request) {
        return null;
    }

    private <T> ResponseEntity<T> requestForEntity(String url, HttpMethod httpMethod, @Nullable Object request,
                                                   Class<T> responseType, Object... uriVariables)
            throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    private FakeStoreProductDto from(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getName());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());
        if(product.getCategory() != null) {
            fakeStoreProductDto.setCategory(product.getCategory().getName());
        }
        return fakeStoreProductDto;
    }

}
