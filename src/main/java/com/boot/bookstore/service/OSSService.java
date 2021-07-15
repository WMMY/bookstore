package com.boot.bookstore.service;

import com.aliyun.oss.*;
import com.aliyun.oss.model.*;
import com.boot.bookstore.config.OSSConfiguration;
import com.boot.bookstore.util.Result;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author wmy
 * @date 2021/07/15
 */
@Component
public class OSSService {

    public static Logger log = LoggerFactory.getLogger(OSSService.class);

    @Autowired
    private OSSConfiguration ossConfiguration;

    @Autowired
    private OSS ossClient;

    public String ENDPOINT = "http://oss-cn-qingdao.aliyuncs.com";
    public String ACCESSKEYID = "LTAI5t9VyQwa4ZSUAjy54qsc";
    public String ACCESSKEYSECRET = "J0eQrVDnYaNAlJp9w7co2s72Die3JE";
    private String OSS_BOOKSTORE_URL;

//    /**
//     * 上传文件到阿里云 OSS 服务器
//     * 链接：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/upload_object.html?spm=5176.docoss/user_guide/upload_object
//     *
//     * @param file
//     * @return
//     */
//    public String uploadFile(MultipartFile file) {
//        OSS_BOOKSTORE_URL = "https://" + ossConfiguration.getBucketName() + ".oss-cn-qingdao.aliyuncs.com/bookstore";
//        try {
//            String fileName = "";
//            // 创建一个唯一的文件名，类似于id，就是保存在OSS服务器上文件的文件名
//            fileName = UUID.randomUUID().toString();
//            InputStream inputStream = file.getInputStream();
//            // 设置对象
//            ObjectMetadata objectMetadata = new ObjectMetadata();
//            // 设置数据流里有多少个字节可以读取
//            objectMetadata.setContentLength(inputStream.available());
//            objectMetadata.setCacheControl("no-cache");
//            objectMetadata.setHeader("Pragma", "no-cache");
//            objectMetadata.setContentType(file.getContentType());
//            objectMetadata.setContentDisposition("inline;filename=" + fileName);
//            fileName = OSS_BOOKSTORE_URL + "/" + fileName;
//            // 上传文件
//            ossClient.putObject(ossConfiguration.getBucketName(), fileName, inputStream, objectMetadata);
//        } catch (IOException e) {
//            log.error("Error occurred: {}", e.getMessage(), e);
//        }
//        return fileName;
//    }


    /**
     * 下载文件
     * 详细请参看“SDK手册 > Java-SDK > 上传文件”
     * 链接：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/download_object.html?spm=5176.docoss/sdk/java-sdk/manage_object
     *
     * @param os
     * @param objectName
     */
    public void exportFile(OutputStream os, String objectName) {
        // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流
        OSSObject ossObject = ossClient.getObject(ossConfiguration.getBucketName(), objectName);
        // 读取文件内容
        BufferedInputStream in = new BufferedInputStream(ossObject.getObjectContent());
        BufferedOutputStream out = new BufferedOutputStream(os);
        byte[] buffer = new byte[1024];
        int lenght;
        try {
            while ((lenght = in.read(buffer)) != -1) {
                out.write(buffer, 0, lenght);
            }
            out.flush();
        } catch (IOException e) {
            log.error("Error occurred: {}", e.getMessage(), e);
        }
    }

//    /**
//     * 获取 url(私有)
//     *
//     * @param filename
//     * @param expSeconds
//     * @return
//     */
//    public String getUrl(String filename, int expSeconds) {
//        Date expiration = new Date(System.currentTimeMillis() + expSeconds * 1000);
//        filename = "bookstore/wx-images" + filename;
//        URL url = ossClient.generatePresignedUrl(ossConfiguration.getBucketName(), filename, expiration);
//        if (url != null) {
//            try {
//                return URLDecoder.decode(url.toString(), "utf-8");
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }

    public String uploadFile(MultipartFile file) {

        String bucketName = ossConfiguration.getBucketName();
        OSS_BOOKSTORE_URL = "https://" + ossConfiguration.getBucketName() + ".oss-cn-qingdao.aliyuncs.com/bookstore";
        // <yourObjectName>上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        try {
            //创建OSS实例
            OSS ossClient = new OSSClientBuilder().build(ENDPOINT, ACCESSKEYID, ACCESSKEYSECRET);

            //获取上传文件输入流
            InputStream inputStream = file.getInputStream();
            // 上传文件到指定的存储空间（bucketName）并将其保存为指定的文件名称（objectName）。
            String fileName = file.getOriginalFilename();

            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            fileName = uuid + fileName;

            ossClient.putObject(bucketName, fileName, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            String url = OSS_BOOKSTORE_URL + "/" + fileName;
            return url;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        // 创建OSSClient实例。
    }


    /**
     * 获取小程序图片的url
     *
     * @param fileName
     * @return
     */
    public String getWXUrl(String fileName) {
        OSS_BOOKSTORE_URL = "https://" + ossConfiguration.getBucketName() + ".oss-cn-qingdao.aliyuncs.com/bookstore";
        fileName = OSS_BOOKSTORE_URL + "/wx-images" + fileName;
        String url = fileName;
        if (url != null) {
            try {
                byte[] bytes = url.getBytes("UTF-8");
                String url_decode = new String(bytes, "UTF-8");
                return url_decode;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 删除文件
     * 详细请参看“SDK手册 > Java-SDK > 管理文件”
     * 链接：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_object.html?spm=5176.docoss/sdk/java-sdk/manage_bucket
     *
     * @param fileName
     */
    public void deleteFile(String fileName) {
        try {
            ossClient.deleteObject(ossConfiguration.getBucketName(), fileName);
        } catch (Exception e) {
            log.error("Error occurred: {}", e.getMessage(), e);
        }
    }

    /**
     * 判断文件是否存在
     *
     * @param fileName
     * @return
     */
    public boolean doesObjectExist(String fileName) {
        try {
            if (Strings.isEmpty(fileName)) {
                log.error("文件名不能为空");
                return false;
            } else {
                return ossClient.doesObjectExist(ossConfiguration.getBucketName(), fileName);
            }
        } catch (OSSException | ClientException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 查看 Bucket 中的 Object 列表
     * 详细请参看“SDK手册 > Java-SDK > 管理文件”
     * 链接：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_object.html?spm=5176.docoss/sdk/java-sdk/manage_bucket
     *
     * @return
     */
    public List<String> listObjects() {
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest(ossConfiguration.getBucketName()).withMaxKeys(200);
        ObjectListing objectListing = ossClient.listObjects(listObjectsRequest);
        List<OSSObjectSummary> objectSummaries = objectListing.getObjectSummaries();
        return objectSummaries.stream().map(OSSObjectSummary::getKey).collect(Collectors.toList());
    }

    /**
     * 设置文件访问权限
     * 详细请参看“SDK手册 > Java-SDK > 管理文件”
     * 链接：https://help.aliyun.com/document_detail/84838.html
     *
     * @param fileName
     */
    public void setObjectAcl(String fileName) {
        ossClient.setObjectAcl(ossConfiguration.getBucketName(), fileName, CannedAccessControlList.PublicRead);
    }
}
