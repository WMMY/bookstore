package com.boot.bookstore.mapper;

import com.boot.bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper extends JpaRepository<User, Integer>, JpaSpecificationExecutor {

    public User findByPhone(String phone);

}

