package com.boot.bookstore.mapper;

import com.boot.bookstore.entity.Bookorder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

@Component
public interface BookorderMapper extends JpaRepository<Bookorder, Integer>, JpaSpecificationExecutor {
}
