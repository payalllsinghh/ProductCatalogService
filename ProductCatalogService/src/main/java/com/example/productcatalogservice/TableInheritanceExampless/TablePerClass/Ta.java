package com.example.productcatalogservice.TableInheritanceExampless.TablePerClass;

import jakarta.persistence.Entity;

@Entity(name="tpc_ta")
public class Ta extends User
{
    int helpReq;
}
