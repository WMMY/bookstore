package com.boot.bookstore.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Entity
public class Indent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userid;
    private Date time;
    private double price;
    private String note;
    private Date delivertime;
    private Date acquiretime;
    private String deliverpos;
    private String acquirepos;
    private String curpos;
    private String delivermethod;
    private Boolean state;
}
