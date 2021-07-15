package com.boot.bookstore.controller;


import com.boot.bookstore.constant.ResultCode;
import com.boot.bookstore.entity.Book;
import com.boot.bookstore.mapper.BookMapper;
import com.boot.bookstore.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Order;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookMapper bookMapper;

//    @GetMapping("/findAll")
//    public List<Book> findAll(){
//        return bookMapper.findAll();
//    }


    @GetMapping("/findAll")
    public Result findAll(){
        List<Book> list = bookMapper.findAll();
        return Result.ok().data("items", list);
    }

//    @GetMapping("/findById/{id}")
//    public Result findById(@PathVariable("id") Integer id){
//        Book book = bookMapper.findById(id).get(); // findById(id)返回为Optional<T>，为防止T实体有空值 需再使用get()获取T实体类
//        return Result.ok().data("items", book);
//    }

    @GetMapping("/findById")
    public Result findById(@RequestParam("id") Integer id){
        Book book = bookMapper.findById(id).get(); // findById(id)返回为Optional<T>，为防止T实体有空值 需再使用get()获取T实体类
        return Result.ok().data("items", book);
    }

//    @GetMapping("/save/{name}/{author}")
//    public Result save(@PathVariable("name") String name, @PathVariable("author") String author){
//        Book book = new Book();
//        book.setName(name);
//        book.setAuthor(author);
//        Book saveResult = bookMapper.save(book);
//        if(saveResult!=null){
//            return Result.ok().message("添加成功");
//        } else{
//            return Result.error().message("添加失败");
//        }
//    }

//    @PostMapping("/save")
//    public Result save(@RequestParam String name, @RequestParam String author){
//        Book book = new Book();
//        book.setName(name);
//        book.setAuthor(author);
//        bookMapper.save(book);
//        return Result.ok().message("添加成功");
//    }

    @PostMapping("/insert")
    public Result insert(@RequestBody Book book){
        bookMapper.save(book);
        return Result.ok().message("添加成功");
    }


    @PutMapping("/update")
    public Result update(@RequestBody Book book){
        bookMapper.save(book);
        return Result.ok().message("修改成功");
    }

//    @GetMapping("/delete/{id}")
//    public Result delete(@PathVariable("id") Integer id){
//        try{
//            bookMapper.deleteById(id);
//            return Result.ok().message("删除成功");
//        }   catch (Exception e){
//            return Result.error().message("删除失败");
//        }
//    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam("id") Integer id){
        bookMapper.deleteById(id);
        return Result.ok().message("删除成功");
    }


}
