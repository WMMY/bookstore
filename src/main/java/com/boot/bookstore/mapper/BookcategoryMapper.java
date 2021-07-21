package com.boot.bookstore.mapper;

import com.boot.bookstore.entity.Bookcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookcategoryMapper extends JpaRepository<Bookcategory, Integer>, JpaSpecificationExecutor {

    public Bookcategory findByBookidAndCategoryid(Integer bookid, Integer categoryid);

    public List<Bookcategory> findByCategoryid(Integer categoryid);


}
