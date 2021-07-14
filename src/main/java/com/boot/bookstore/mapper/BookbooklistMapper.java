package com.boot.bookstore.mapper;

import com.boot.bookstore.entity.Bookbooklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface BookbooklistMapper extends JpaRepository<Bookbooklist, Integer> {
}
