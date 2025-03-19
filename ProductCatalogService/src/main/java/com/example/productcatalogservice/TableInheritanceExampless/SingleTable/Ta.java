package com.example.productcatalogservice.TableInheritanceExampless.SingleTable;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name="st_ta")
@DiscriminatorValue(value="70")
public class Ta extends User
{
    int helpReq;
}
