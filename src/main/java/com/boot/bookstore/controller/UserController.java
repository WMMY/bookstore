package com.boot.bookstore.controller;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.boot.bookstore.entity.User;
import com.boot.bookstore.mapper.UserMapper;
import com.boot.bookstore.util.TokenUse;
import com.boot.bookstore.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
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
    public Result findById(@RequestParam("id") Integer id){
        User user = userMapper.findById(id).get(); // findById(id)返回为Optional<T>，为防止T实体有空值 需再使用get()获取T实体类
        return Result.ok().data("items", user);
    }

    @PostMapping("/insert")
    public Result insert(@RequestBody User user){
        userMapper.save(user);
        return Result.ok().message("注册成功");
    }

    @PutMapping("/update")
    public Result update(@RequestBody User user){
        userMapper.save(user);
        return Result.ok().message("修改成功");
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam("id") Integer id){
        userMapper.deleteById(id);
        return Result.ok().message("删除成功");
    }

//    @PostMapping("/login")
//    public Result managerLogin(@RequestParam("phone") String phone, @RequestParam("pwd") String pwd, @RequestParam("manager") Integer manager){
//        User user = userMapper.findByPhone(phone);
//        if (user == null || user.getManager() != manager){
//            return Result.notExist().message("账号不存在");
//        }
//        if (user.getPwd().equals(pwd)){
//            return Result.ok().message("登陆成功");
//        }   else {
//            return Result.error().message("登陆失败");
//        }
//    }

    @PostMapping("/login")
    public Result managerLogin(@RequestBody User paramuser){
        User user = userMapper.findByPhone(paramuser.getPhone());
        if (user == null || user.getManager() != paramuser.getManager()){
            return Result.notExist().message("账号不存在");
        }
        if (user.getPwd().equals(paramuser.getPwd())){
            String token= TokenUse.sign(user.getPhone(), user.getId());
            return Result.ok().message("登陆成功").data("token", token);
        }   else {
            return Result.error().message("登陆失败");
        }
    }

    public String getToken(User user) {
        String token="";
        try {
            token= JWT.create().withAudience(user.getId().toString())
                    .sign(Algorithm.HMAC256(user.getPwd()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return token;
    }
}
