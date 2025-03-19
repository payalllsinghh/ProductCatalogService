package com.example.productcatalogservice.TableInheritanceExampless.MappedSuperClass;

import jakarta.persistence.Entity;

@Entity(name="msc_mentor")
public class Mentor extends User {
    double ratings;
}
