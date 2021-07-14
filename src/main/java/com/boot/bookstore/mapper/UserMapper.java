package com.boot.bookstore.mapper;

import com.boot.bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper extends JpaRepository<User, Integer> {

}
