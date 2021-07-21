package com.boot.bookstore.controller;


import com.boot.bookstore.entity.Indent;
import com.boot.bookstore.mapper.IndentMapper;
import com.boot.bookstore.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/indent")
public class IndentController {

    @Autowired
    private IndentMapper indentMapper;

    @RequestMapping("/findAll")
    public Result findAll(){
        List<Indent> list = indentMapper.findAll();
        return Result.ok().data("items", list);
    }

    @RequestMapping("/findById")
    public Result findById(@RequestParam("id") Integer id){
        Indent indent = indentMapper.findById(id).get();
        return Result.ok().data("items", indent);
    }

    @RequestMapping("/findByState")
    public Result findByState(){
        List<Indent> list = indentMapper.findByState(true);
        return Result.ok().data("items", list);
    }

    @RequestMapping("/deliver")
    public Result deliver(@RequestParam("id") Integer id){
        Indent indent = indentMapper.findById(id).get();
        indent.setState(true);
        java.util.Date  date = new java.util.Date();
        java.sql.Date  data1 = new java.sql.Date(date.getTime());
        indent.setDelivertime(data1);
        indentMapper.save(indent);
        return Result.ok().data("items", indent);
    }

    @RequestMapping("/insert")
    public Result insert(@RequestBody Indent indent){
        indentMapper.save(indent);
        return Result.ok().message("添加成功");
    }

    @RequestMapping("/update")
    public Result update(@RequestBody Indent indent){
        indentMapper.save(indent);
        return Result.ok().message("修改成功");
    }

    @RequestMapping("/updateCurepos")
    public Result updateCurepos(@RequestParam Integer id, @RequestParam String curpos){
        Indent indent = indentMapper.findById(id).get();
        indent.setCurpos(curpos);
        indentMapper.save(indent);
        return Result.ok().message("修改成功");
    }

    @RequestMapping("/delete")
    public Result delete(@RequestParam("id") Integer id){
        indentMapper.deleteById(id);
        return Result.ok().message("删除成功");
    }

}
