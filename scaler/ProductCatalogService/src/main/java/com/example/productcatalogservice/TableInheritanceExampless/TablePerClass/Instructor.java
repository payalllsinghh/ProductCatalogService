package com.example.productcatalogservice.TableInheritanceExampless.TablePerClass;

import jakarta.persistence.Entity;

@Entity(name="tpc_instructor")
public class Instructor extends User
{
    String company;
}
