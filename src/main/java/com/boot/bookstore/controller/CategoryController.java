package com.boot.bookstore.controller;


import com.boot.bookstore.entity.Book;
import com.boot.bookstore.entity.Bookcategory;
import com.boot.bookstore.entity.Category;
import com.boot.bookstore.entity.CategoryParam;
import com.boot.bookstore.mapper.BookMapper;
import com.boot.bookstore.mapper.BookcategoryMapper;
import com.boot.bookstore.mapper.CategoryMapper;
import com.boot.bookstore.util.Result;
import com.boot.bookstore.util.ResultList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private BookcategoryMapper bookcategoryMapper;
    @Autowired
    private BookMapper bookMapper;

    @RequestMapping("/findAll")
    public Result findAll(){
        List<Category> list = categoryMapper.findAll();
        return Result.ok().data("items", list);
    }

    @RequestMapping("/findAllList")
    public ResultList findAllList(){
        List<Category> list = categoryMapper.findAll();
        return ResultList.ok().data(list);
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

    @RequestMapping("/findCategoryByName")
    public ResultList findCategory(@RequestParam String name){
        List<Book> bookList = new ArrayList<>();
        Integer categoryid = categoryMapper.findByName(name).getId();
        List<Bookcategory> list = bookcategoryMapper.findByCategoryid(categoryid);
        for (Bookcategory bookcategory : list){
            Book book = bookMapper.findById(bookcategory.getBookid()).get();
            bookList.add(book);
        }
        return ResultList.ok().data(bookList);
    }

    @RequestMapping("/findCategory")
    public Result findCategory(@RequestBody CategoryParam categoryParam){
        Map<String, Object> map= new HashMap<>();
        List<Book> bookList;
        for(int i=0; i< categoryParam.getType().length; i++){
            bookList = new ArrayList<>();
            Integer categoryid = categoryMapper.findByName(categoryParam.getType()[i]).getId();
            List<Bookcategory> list = bookcategoryMapper.findByCategoryid(categoryid);
            for (Bookcategory bookcategory : list){
                Book book = bookMapper.findById(bookcategory.getBookid()).get();
                bookList.add(book);
            }
            map.put(categoryParam.getType()[i], bookList);
        }
        return Result.ok().data(map);
    }

    @RequestMapping("/findAllCategory")
    public Result findAllCategory(){
        List<Category> categoryList = categoryMapper.findAll();
        Map<String, Object> map= new HashMap<>();
        List<Book> bookList;
        for(Category category : categoryList){
            bookList = new ArrayList<>();
            Integer categoryid = categoryMapper.findByName(category.getName()).getId();
            List<Bookcategory> list = bookcategoryMapper.findByCategoryid(categoryid);
            for (Bookcategory bookcategory : list){
                Book book = bookMapper.findById(bookcategory.getBookid()).get();
                bookList.add(book);
            }
            map.put(category.getName(), bookList);
        }
        return Result.ok().data(map);
    }

}
