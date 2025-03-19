package com.example.productcatalogservice.TableInheritanceExampless.TablePerClass;

import jakarta.persistence.Entity;

@Entity(name="tpc_mentor")
public class Mentor extends User{
    double ratings;
}
