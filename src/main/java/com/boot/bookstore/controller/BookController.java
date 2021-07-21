package com.boot.bookstore.controller;


import com.boot.bookstore.constant.ResultCode;
import com.boot.bookstore.entity.Book;
import com.boot.bookstore.entity.Bookcategory;
import com.boot.bookstore.entity.Category;
import com.boot.bookstore.mapper.BookMapper;
import com.boot.bookstore.mapper.BookcategoryMapper;
import com.boot.bookstore.mapper.CategoryMapper;
import com.boot.bookstore.util.Result;
import com.boot.bookstore.util.ResultList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Order;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private OSSController ossController;

//    @GetMapping("/findAll")
//    public List<Book> findAll(){
//        return bookMapper.findAll();
//    }


//    @GetMapping("/findAll")
//    public Result findAll(){
//        List<Book> list = bookMapper.findAll();
//        return Result.ok().data("items", list);
//    }

    @RequestMapping("/findAll")
    public Result findAll(@RequestParam("page") Integer page, @RequestParam("size") Integer size){
        PageRequest request = PageRequest.of(page-1, size);
        Page<Book> page1 = bookMapper.findAll(request);
        return Result.ok().data("items", page1);
    }

    @RequestMapping("/findByIsbn")
    public Result findByIsbn(@RequestParam("isbn") String isbn){
        Book book = bookMapper.findByIsbn(isbn);
        String url = ossController.getWXImageUrl(isbn);
        return Result.ok().data("url", url).data("items", book);
    }

    @RequestMapping("/findByIsbnList")
    public ResultList findByIsbnList(@RequestParam("isbn") String isbn){
        Book book = bookMapper.findByIsbn(isbn);
        String url = ossController.getWXImageUrl(isbn);
        return ResultList.ok().data(url).data(book);
    }

//    @GetMapping("/findById/{id}")
//    public Result findById(@PathVariable("id") Integer id){
//        Book book = bookMapper.findById(id).get(); // findById(id)返回为Optional<T>，为防止T实体有空值 需再使用get()获取T实体类
//        return Result.ok().data("items", book);
//    }

    @RequestMapping("/findById")
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

    @RequestMapping("/insert")
    public Result insert(@RequestBody Book book){
        try {
            bookMapper.save(book);
            return Result.ok().message("添加成功");
        } catch (Exception e){
            return Result.error().message("添加失败");
        }
    }


    @RequestMapping("/update")
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

    @RequestMapping("/delete")
    public Result delete(@RequestParam("id") Integer id){
        bookMapper.deleteById(id);
        return Result.ok().message("删除成功");
    }


}
