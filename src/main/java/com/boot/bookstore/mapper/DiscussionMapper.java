package com.boot.bookstore.mapper;

import com.boot.bookstore.entity.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface DiscussionMapper extends JpaRepository<Discussion, Integer> {
}
