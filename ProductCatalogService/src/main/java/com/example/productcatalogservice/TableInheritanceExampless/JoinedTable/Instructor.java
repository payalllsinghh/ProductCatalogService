package com.example.productcatalogservice.TableInheritanceExampless.JoinedTable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name="jt_instructor")
@PrimaryKeyJoinColumn(name="user_id")
public class Instructor extends User
{
    String company;
}
