package com.boot.bookstore.controller;


import com.boot.bookstore.entity.Category;
import com.boot.bookstore.entity.User;
import com.boot.bookstore.mapper.CategoryMapper;
import com.boot.bookstore.mapper.UserMapper;
import com.boot.bookstore.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryMapper categoryMapper;

    @GetMapping("/findAll")
    public Result findAll(){
        List<Category> list = categoryMapper.findAll();
        return Result.ok().data("items", list);
    }

    @GetMapping("/findById")
    public Result findById(@RequestParam("id") Integer id){
        Category category = categoryMapper.findById(id).get(); // findById(id)返回为Optional<T>，为防止T实体有空值 需再使用get()获取T实体类
        return Result.ok().data("items", category);
    }

    @PostMapping("/insert")
    public Result insert(@RequestBody Category category){
        categoryMapper.save(category);
        return Result.ok().message("添加成功");
    }

    @PutMapping("/update")
    public Result update(@RequestBody Category category){
        categoryMapper.save(category);
        return Result.ok().message("修改成功");
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam("id") Integer id){
        categoryMapper.deleteById(id);
        return Result.ok().message("删除成功");
    }
}
