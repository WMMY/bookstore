package com.boot.bookstore.mapper;

import com.boot.bookstore.entity.Booklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface BooklistMapper extends JpaRepository<Booklist, Integer> {
}
