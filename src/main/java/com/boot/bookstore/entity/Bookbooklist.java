package com.boot.bookstore.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Data
@Entity
public class Bookbooklist {
    @Id
    private Integer id;
    private Integer bookid;
    private Integer booklistid;
    private String note;


}