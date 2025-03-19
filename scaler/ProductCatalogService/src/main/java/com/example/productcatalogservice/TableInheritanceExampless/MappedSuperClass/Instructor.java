package com.example.productcatalogservice.TableInheritanceExampless.MappedSuperClass;

import jakarta.persistence.Entity;

@Entity(name="msc_instructor")
public class Instructor extends User
{
    String company;
}
