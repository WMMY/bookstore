package com.boot.bookstore.controller;


import com.boot.bookstore.entity.Booklist;
import com.boot.bookstore.mapper.BooklistMapper;
import com.boot.bookstore.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booklist")
public class BooklistController {
    @Autowired
    private BooklistMapper booklistMapper;

    @GetMapping("/findAll")
    public Result findAll(){
        List<Booklist> list = booklistMapper.findAll();
        return Result.ok().data("items", list);
    }

    @GetMapping("/findById")
    public Result findById(@RequestParam("id") Integer id){
        Booklist booklist = booklistMapper.findById(id).get(); // findById(id)返回为Optional<T>，为防止T实体有空值 需再使用get()获取T实体类
        return Result.ok().data("items", booklist);
    }

    @PostMapping("/insert")
    public Result insert(@RequestBody Booklist booklist){
        booklistMapper.save(booklist);
        return Result.ok().message("添加成功");
    }

    @PutMapping("/update")
    public Result update(@RequestBody Booklist booklist){
        booklistMapper.save(booklist);
        return Result.ok().message("修改成功");
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam("id") Integer id){
        booklistMapper.deleteById(id);
        return Result.ok().message("删除成功");
    }
}
