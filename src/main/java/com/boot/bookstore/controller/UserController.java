package com.boot.bookstore.controller;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.boot.bookstore.entity.Bookcategory;
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

    // 登录
    @PostMapping("/login")
    public Result managerLogin(@RequestBody User paramuser){
        User user = userMapper.findByPhone(paramuser.getPhone());
        if (user == null || user.getManager() != paramuser.getManager()){
            return Result.notExist().message("账号不存在");
        }
        if (user.getPwd().equals(paramuser.getPwd())){
            String token= TokenUse.sign(user.getPhone(), user.getId());
            return Result.ok().message("登录成功").data("token", token).data("name", user.getName()).data("email", user.getEmail());
        }   else {
            return Result.error().message("登录失败");
        }
    }

    // 获取用户钱包余额
    @RequestMapping("/getWallet")
    public Result getWallet(@RequestParam("token") String token){
        // 验证token
        if(TokenUse.tokenVerify(token)){
            // findById(id)返回为Optional<T>，为防止T实体有空值 需再使用get()获取T实体类
            User user = userMapper.findById(TokenUse.getUserID(token)).get();
            return Result.ok().data("wallet", user.getWallet());
        }   else {
            return Result.error().message("查询失败");
        }
    }

    // 通过电话号码查找用户
    @RequestMapping("/findByPhone")
    public Result findByPhone(@RequestParam("phone") String phone){
        User user = userMapper.findByPhone(phone);
        return Result.ok().data("items", user);
    }

    @RequestMapping("/setWallet")
    public Result setWallet(@RequestParam("token") String token, @RequestParam("wallet") double wallet){
        if(TokenUse.tokenVerify(token)){
            User user = userMapper.findById(TokenUse.getUserID(token)).get(); // findById(id)返回为Optional<T>，为防止T实体有空值 需再使用get()获取T实体类
            user.setWallet(user.getWallet() - wallet);
            userMapper.save(user);
            return Result.ok().message("修改成功");
        }   else {
            return Result.error().message("修改失败");
        }
    }

    @RequestMapping("/findAll")
    public Result findAll(){
        List<User> list = userMapper.findAll();
        return Result.ok().data("items", list);
    }

    @RequestMapping("/findById")
    public Result findById(@RequestParam("id") Integer id){
        User user = userMapper.findById(id).get(); // findById(id)返回为Optional<T>，为防止T实体有空值 需再使用get()获取T实体类
        return Result.ok().data("items", user);
    }

    @RequestMapping("/setName")
    public Result setName(@RequestParam("token") String token, @RequestParam("name") String name){
        if(TokenUse.tokenVerify(token)){
            User user = userMapper.findById(TokenUse.getUserID(token)).get(); // findById(id)返回为Optional<T>，为防止T实体有空值 需再使用get()获取T实体类
            user.setName(name);
            userMapper.save(user);
            return Result.ok().message("修改成功");
        }   else {
            return Result.error().message("修改失败");
        }
    }

    @RequestMapping("/insert")
    public Result insert(@RequestBody User user){
        userMapper.save(user);
        String token= TokenUse.sign(user.getPhone(), user.getId());
        return Result.ok().message("注册成功").data("token", token);
    }

//    @PutMapping("/update")
//    public Result update(@RequestBody User user){
//        userMapper.save(user);
//        return Result.ok().message("修改成功");
//    }

    @RequestMapping("/modify")
    public Result modify(@RequestBody User paramuser){
        User user = userMapper.findByPhone(paramuser.getPhone());
        if (user == null || !user.getEmail().equals(paramuser.getEmail())){
            return Result.error().message("修改失败");
        }   else{
            user.setPwd(paramuser.getPwd());
            userMapper.save(user);
            return Result.ok().message("修改成功");
        }
    }

    @RequestMapping("/delete")
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
//            return Result.ok().message("登录成功");
//        }   else {
//            return Result.error().message("登录失败");
//        }
//    }
}
