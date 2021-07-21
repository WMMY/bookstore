package com.boot.bookstore.controller;

import com.boot.bookstore.entity.Book;
import com.boot.bookstore.entity.Bookcategory;
import com.boot.bookstore.entity.BookcategoryParam;
import com.boot.bookstore.entity.Category;
import com.boot.bookstore.mapper.BookMapper;
import com.boot.bookstore.mapper.BookcategoryMapper;
import com.boot.bookstore.mapper.CategoryMapper;
import com.boot.bookstore.util.Result;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bookcategory")
public class BookcategoryController {

    @Autowired
    private BookcategoryMapper bookcategoryMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private BookMapper bookMapper;


    @RequestMapping("/findAll")
    public Result findAll(){
        List<Bookcategory> list = bookcategoryMapper.findAll();
        return Result.ok().data("items", list);
    }

    @RequestMapping("/findById")
    public Result findById(@RequestParam("id") Integer id){
        Bookcategory bookcategory = bookcategoryMapper.findById(id).get(); // findById(id)返回为Optional<T>，为防止T实体有空值 需再使用get()获取T实体类
        return Result.ok().data("items", bookcategory);
    }

    @RequestMapping("/findByBookidAndCategoryid")
    public Result findByBookidAndCategoryid(@RequestParam("bookid") Integer bookid, @RequestParam("categoryid") Integer categoryid){
        Bookcategory bookcategory = bookcategoryMapper.findByBookidAndCategoryid(bookid, categoryid); // findById(id)返回为Optional<T>，为防止T实体有空值 需再使用get()获取T实体类
        return Result.ok().data("items", bookcategory);
    }

//    @PostMapping("/insert")
//    public Result insert(@RequestBody Bookcategory bookcategory){
//        bookcategoryMapper.save(bookcategory);
//        return Result.ok().message("添加成功");
//    }

    //添加图书时，添加图书与类别的关系表
    @RequestMapping("/insert")
    public Result insert(@RequestBody BookcategoryParam bookcategoryParam){
        Category category;
        Book book = bookMapper.findByIsbn(bookcategoryParam.getIsbn());
        Bookcategory[] bookcategory = new Bookcategory[bookcategoryParam.getType().length];
        for(int i=0; i< bookcategoryParam.getType().length; i++){
            bookcategory[i] = new Bookcategory();
            category = categoryMapper.findByName(bookcategoryParam.getType()[i]);
            if(i == 0){
                bookcategory[i].setId(0);
            }else {
                bookcategory[i].setId(bookcategoryMapper.findByBookidAndCategoryid(bookcategory[i-1].getBookid(), bookcategory[i-1].getCategoryid()).getId()+1);
            }
            bookcategory[i].setBookid(book.getId());
            bookcategory[i].setCategoryid(category.getId());
            bookcategoryMapper.save(bookcategory[i]);
        }
        return Result.ok().message("添加成功");
    }

//    @RequestMapping("/insert")
//    public Result insert(@RequestParam("isbn") String isbn, @RequestParam("type") String[] type){
//        Category category;
//        Book book = bookMapper.findByIsbn(isbn);
//        Bookcategory[] bookcategory = new Bookcategory[type.length];
//        for(int i=0; i< type.length; i++){
//            bookcategory[i] = new Bookcategory();
//            category = categoryMapper.findByName(type[i]);
//            if(i == 0){
//                bookcategory[i].setId(0);
//            }else {
//                bookcategory[i].setId(bookcategoryMapper.findByBookidAndCategoryid(bookcategory[i-1].getBookid(), bookcategory[i-1].getCategoryid()).getId()+1);
//            }
//            bookcategory[i].setBookid(book.getId());
//            bookcategory[i].setCategoryid(category.getId());
//            bookcategoryMapper.save(bookcategory[i]);
//        }
//        return Result.ok().message("添加成功");
//    }

    @RequestMapping("/update")
    public Result update(@RequestBody Bookcategory bookcategory){
        bookcategoryMapper.save(bookcategory);
        return Result.ok().message("修改成功");
    }

    @RequestMapping("/delete")
    public Result delete(@RequestParam("id") Integer id){
        bookcategoryMapper.deleteById(id);
        return Result.ok().message("删除成功");
    }
}
