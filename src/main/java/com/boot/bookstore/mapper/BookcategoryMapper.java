package com.boot.bookstore.mapper;

import com.boot.bookstore.entity.Bookcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface BookcategoryMapper extends JpaRepository<Bookcategory, Integer> {
}
