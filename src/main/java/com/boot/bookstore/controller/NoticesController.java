package com.boot.bookstore.controller;

import com.boot.bookstore.entity.Indent;
import com.boot.bookstore.entity.Notices;
import com.boot.bookstore.entity.User;
import com.boot.bookstore.mapper.NoticesMapper;
import com.boot.bookstore.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notices")
public class NoticesController {

    @Autowired
    NoticesMapper noticesMapper;

    @RequestMapping("/findAll")
    public Result findAll(){
        List<Notices> list = noticesMapper.findAll();
        return Result.ok().data("items", list);
    }

    @RequestMapping("/update")
    public Result update(@RequestBody Notices notices){
        noticesMapper.save(notices);
        return Result.ok().message("修改成功");
    }


}
