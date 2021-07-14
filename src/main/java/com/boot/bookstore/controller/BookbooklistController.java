package com.boot.bookstore.controller;

import com.boot.bookstore.entity.Bookbooklist;
import com.boot.bookstore.mapper.BookbooklistMapper;
import com.boot.bookstore.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookbooklist")
public class BookbooklistController {
    @Autowired
    private BookbooklistMapper bookbooklistMapper;

    @GetMapping("/findAll")
    public Result findAll(){
        List<Bookbooklist> list = bookbooklistMapper.findAll();
        return Result.ok().data("items", list);
    }

    @GetMapping("/findById")
    public Result findById(@RequestParam Integer id){
        Bookbooklist bookbooklist = bookbooklistMapper.findById(id).get(); // findById(id)返回为Optional<T>，为防止T实体有空值 需再使用get()获取T实体类
        return Result.ok().data("items", bookbooklist);
    }

    @PostMapping("/insert")
    public Result insert(@RequestBody Bookbooklist bookbooklist){
        bookbooklistMapper.save(bookbooklist);
        return Result.ok().message("添加成功");
    }

    @PutMapping("/update")
    public Result update(@RequestBody Bookbooklist bookbooklist){
        bookbooklistMapper.save(bookbooklist);
        return Result.ok().message("修改成功");
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam Integer id){
        bookbooklistMapper.deleteById(id);
        return Result.ok().message("删除成功");
    }
}
