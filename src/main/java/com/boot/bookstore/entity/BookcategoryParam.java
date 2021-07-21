package com.boot.bookstore.entity;

import javax.persistence.Entity;


public class BookcategoryParam {
    private String isbn;
    private String[] type;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String[] getType() {
        return type;
    }

    public void setType(String[] type) {
        this.type = type;
    }
}
