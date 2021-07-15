package com.boot.bookstore.controller;

import com.boot.bookstore.service.OSSService;
import com.boot.bookstore.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.util.List;


@RestController
@RequestMapping("/oss")
public class OSSController {

    @Autowired
    private OSSService ossService;

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    @PostMapping("/uploadFile")
    public Result uploadFile(@RequestParam MultipartFile file) {
        //获取上传文件 MultipartFile
        //返回上传到oss的路径
        String url = ossService.uploadFile(file);

        return Result.ok().data("url", url);
    }

    /**
     * 下载文件
     *
     * @param fileName
     */
    @PostMapping("/exportFile")
    public void exportFile(OutputStream os, @RequestParam("fileName") String fileName) {
        ossService.exportFile(os, fileName);
    }

    /**
     * 删除文件
     *
     * @param fileName
     */
    @PostMapping("/deleteFile")
    public void deleteFile(@RequestParam("fileName") String fileName) {
        ossService.deleteFile(fileName);
    }

    /**
     * 查看文件列表
     *
     * @return
     */
    @PostMapping("/listObjects")
    public List<String> listObjects() {
        return ossService.listObjects();
    }

    /**
     * 判断文件是否存在
     *
     * @param fileName
     */
    @PostMapping("/doesObjectExist")
    public boolean doesObjectExist(@RequestParam("fileName") String fileName) {
        return ossService.doesObjectExist(fileName);
    }

    /**
     * 获取 url
     *
     * @param fileName
     */
    @PostMapping("/getWXUrl")
    public String getWXUrl(@RequestParam String fileName) {
        return ossService.getWXUrl(fileName);
    }

    /**
     * 设置文件访问权限
     *
     * @param fileName
     */
    @PostMapping("/setObjectAcl")
    public void setObjectAcl(@RequestParam("fileName") String fileName) {
        ossService.setObjectAcl(fileName);
    }
}

