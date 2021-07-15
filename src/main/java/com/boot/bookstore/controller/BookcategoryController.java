package com.boot.bookstore.controller;

import com.boot.bookstore.entity.Bookcategory;
import com.boot.bookstore.entity.User;
import com.boot.bookstore.mapper.BookcategoryMapper;
import com.boot.bookstore.mapper.UserMapper;
import com.boot.bookstore.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Bookcategory")
public class BookcategoryController {
    @Autowired
    private BookcategoryMapper bookcategoryMapper;

    @GetMapping("/findAll")
    public Result findAll(){
        List<Bookcategory> list = bookcategoryMapper.findAll();
        return Result.ok().data("items", list);
    }

    @GetMapping("/findById")
    public Result findById(@RequestParam("id") Integer id){
        Bookcategory bookcategory = bookcategoryMapper.findById(id).get(); // findById(id)返回为Optional<T>，为防止T实体有空值 需再使用get()获取T实体类
        return Result.ok().data("items", bookcategory);
    }

    @PostMapping("/insert")
    public Result insert(@RequestBody Bookcategory bookcategory){
        bookcategoryMapper.save(bookcategory);
        return Result.ok().message("添加成功");
    }

    @PutMapping("/update")
    public Result update(@RequestBody Bookcategory bookcategory){
        bookcategoryMapper.save(bookcategory);
        return Result.ok().message("修改成功");
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam("id") Integer id){
        bookcategoryMapper.deleteById(id);
        return Result.ok().message("删除成功");
    }
}
