package com.boot.bookstore.mapper;


import com.boot.bookstore.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface CategoryMapper extends JpaRepository<Category, Integer> {

}
