package com.boot.bookstore.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Bookorder {
    @Id
    private Integer id;
    private Integer bookid;
    private Integer orderid;
    private Integer booknum;
    private String note;
}