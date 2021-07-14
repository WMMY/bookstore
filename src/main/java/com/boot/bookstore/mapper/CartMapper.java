package com.boot.bookstore.mapper;

import com.boot.bookstore.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface CartMapper extends JpaRepository<Cart, Integer> {
}
