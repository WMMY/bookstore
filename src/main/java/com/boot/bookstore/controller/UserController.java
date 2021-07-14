package com.boot.bookstore.controller;


import com.boot.bookstore.entity.User;
import com.boot.bookstore.mapper.UserMapper;
import com.boot.bookstore.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/findAll")
    public Result findAll(){
        List<User> list = userMapper.findAll();
        return Result.ok().data("items", list);
    }

    @GetMapping("/findById")
    public Result findById(@RequestParam Integer id){
        User user = userMapper.findById(id).get(); // findById(id)返回为Optional<T>，为防止T实体有空值 需再使用get()获取T实体类
        return Result.ok().data("items", user);
    }

    @PostMapping("/insert")
    public Result insert(@RequestBody User user){
        userMapper.save(user);
        return Result.ok().message("添加成功");
    }

    @PutMapping("/update")
    public Result update(@RequestBody User user){
        userMapper.save(user);
        return Result.ok().message("修改成功");
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam Integer id){
        userMapper.deleteById(id);
        return Result.ok().message("删除成功");
    }

    @GetMapping("/login")
    public Result managerLogin(@RequestParam String phone, @RequestParam String pwd, @RequestParam Integer manager){
        User user = userMapper.findByPhone(phone);
        if (user == null || user.getManager() != manager){
            return Result.notExist().message("账号不存在");
        }
        if (user.getPwd().equals(pwd)){
            return Result.ok().message("登陆成功");
        }   else {
            return Result.error().message("登陆失败");
        }
    }
}
