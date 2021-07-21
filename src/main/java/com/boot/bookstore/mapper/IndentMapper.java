package com.boot.bookstore.mapper;

import com.boot.bookstore.entity.Indent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface IndentMapper extends JpaRepository<Indent, Integer>, JpaSpecificationExecutor {
    public List<Indent> findByState(Boolean state);
}
