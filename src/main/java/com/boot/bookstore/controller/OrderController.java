package com.boot.bookstore.controller;


import com.boot.bookstore.entity.Order;
import com.boot.bookstore.mapper.OrderMapper;
import com.boot.bookstore.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderMapper orderMapper;

    @GetMapping("/findAll")
    public Result findAll(){
        List<Order> list = orderMapper.findAll();
        return Result.ok().data("items", list);
    }

    @GetMapping("/findById")
    public Result findById(@RequestParam Integer id){
        Order order = orderMapper.findById(id).get(); // findById(id)返回为Optional<T>，为防止T实体有空值 需再使用get()获取T实体类
        return Result.ok().data("items", order);
    }

    @PostMapping("/insert")
    public Result insert(@RequestBody Order order){
        orderMapper.save(order);
        return Result.ok().message("添加成功");
    }

    @PutMapping("/update")
    public Result update(@RequestBody Order order){
        orderMapper.save(order);
        return Result.ok().message("修改成功");
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam Integer id){
        orderMapper.deleteById(id);
        return Result.ok().message("删除成功");
    }

}
