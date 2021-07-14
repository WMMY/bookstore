package com.boot.bookstore.mapper;

import com.boot.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

// 继承JpaRepository<实体类类型，主键类型>
@Component
public interface BookMapper extends JpaRepository<Book, Integer> {

}
