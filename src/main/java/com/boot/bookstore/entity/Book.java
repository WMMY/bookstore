package com.boot.bookstore.entity;


import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;


@Data // 自动生成get和set方法
@Entity // 与数据表绑定，类名对应表中开头字母改为小写的表名
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 属性与表中字段名一致
    private String name;
    private String author;
    private double price;
    private String description;
    private String isbn;
    private Date time;
    private String press;
    private String note;
}
