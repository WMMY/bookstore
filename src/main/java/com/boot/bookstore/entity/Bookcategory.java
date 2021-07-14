package com.boot.bookstore.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Bookcategory {
    @Id
    private Integer id;
    private Integer bookid;
    private Integer categoryid;
    private String note;
}