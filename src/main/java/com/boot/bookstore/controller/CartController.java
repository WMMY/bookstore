package com.boot.bookstore.controller;

import com.boot.bookstore.entity.Cart;
import com.boot.bookstore.entity.User;
import com.boot.bookstore.mapper.CartMapper;
import com.boot.bookstore.mapper.UserMapper;
import com.boot.bookstore.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartMapper cartMapper;

    @GetMapping("/findAll")
    public Result findAll(){
        List<Cart> list = cartMapper.findAll();
        return Result.ok().data("items", list);
    }

    @GetMapping("/findById")
    public Result findById(@RequestParam("id") Integer id){
        Cart cart = cartMapper.findById(id).get(); // findById(id)返回为Optional<T>，为防止T实体有空值 需再使用get()获取T实体类
        return Result.ok().data("items", cart);
    }

    @PostMapping("/insert")
    public Result insert(@RequestBody Cart cart){
        cartMapper.save(cart);
        return Result.ok().message("添加成功");
    }

    @PutMapping("/update")
    public Result update(@RequestBody Cart cart){
        cartMapper.save(cart);
        return Result.ok().message("修改成功");
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam("id") Integer id){
        cartMapper.deleteById(id);
        return Result.ok().message("删除成功");
    }
}
