package com.example.productcatalogservice.TableInheritanceExampless.JoinedTable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name="jt_ta")
@PrimaryKeyJoinColumn(name="user_id")
public class Ta extends User
{
    int helpReq;
}
