package com.boot.bookstore.mapper;

import com.boot.bookstore.entity.Notices;
import com.boot.bookstore.entity.User;
import org.mapstruct.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

@Component
public interface NoticesMapper extends JpaRepository<Notices, Integer>, JpaSpecificationExecutor {
}
