package com.example.productcatalogservice.models;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass //This ensures this fields be added to the subsidary class tables
public abstract class BaseModel {
    @Id // id will be a primary key
    private Long id;
    private Date createdAt;
    private Date lastUpdatedAt;
    private State state;
}
