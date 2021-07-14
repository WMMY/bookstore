package com.boot.bookstore.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Cart {
    @Id
    private Integer id;
    private Integer userid;
    private Integer bookid;
    private String note;
}
