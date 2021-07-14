package com.boot.bookstore.mapper;

import com.boot.bookstore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


@Component
public interface OrderMapper extends JpaRepository<Order, Integer> {
}
