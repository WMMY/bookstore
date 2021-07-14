package com.boot.bookstore.controller;


import com.boot.bookstore.entity.Discussion;
import com.boot.bookstore.entity.User;
import com.boot.bookstore.mapper.DiscussionMapper;
import com.boot.bookstore.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discussion")
public class DiscussionController {
    @Autowired
    private DiscussionMapper discussionMapper;

    @GetMapping("/findAll")
    public Result findAll(){
        List<Discussion> list = discussionMapper.findAll();
        return Result.ok().data("items", list);
    }

    @GetMapping("/findById")
    public Result findById(@RequestParam Integer id){
        Discussion discussion = discussionMapper.findById(id).get(); // findById(id)返回为Optional<T>，为防止T实体有空值 需再使用get()获取T实体类
        return Result.ok().data("items", discussion);
    }

    @PostMapping("/insert")
    public Result insert(@RequestBody Discussion discussion){
        discussionMapper.save(discussion);
        return Result.ok().message("添加成功");
    }

    @PutMapping("/update")
    public Result update(@RequestBody Discussion discussion){
        discussionMapper.save(discussion);
        return Result.ok().message("修改成功");
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam Integer id){
        discussionMapper.deleteById(id);
        return Result.ok().message("删除成功");
    }
}
