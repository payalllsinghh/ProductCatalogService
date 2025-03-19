package com.example.productcatalogservice.TableInheritanceExampless.MappedSuperClass;

import jakarta.persistence.Entity;

@Entity(name="msc_ta")
public class Ta extends User
{
    int helpReq;
}
