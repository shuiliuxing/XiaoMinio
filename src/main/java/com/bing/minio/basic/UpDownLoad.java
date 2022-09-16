package com.bing.minio.basic;

import io.minio.MinioClient;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
/**
 * @author zxh
 * @date 2022/9/16 14:52
 * @description
 */
public class UpDownLoad {
    public static void main(String[] args) throws Exception {
        upload();
    }

    // 上传文件
    public static void upload() throws Exception {
        // minio配置信息
        String url = "http://115.159.6.164:9005";
        String accessKey="admin";
        String secretKey="";
        String bucket="xiao";

        MinioClient client=new MinioClient(url, accessKey, secretKey);
        // 校验存储桶是否存在
        boolean isExist=client.bucketExists(bucket);
        if(isExist){
            System.out.println("存储桶已存在！");
        }else{
            // 创建一个名为xiao的存储桶，用于存储上传的文件
            client.makeBucket(bucket);
        }

        // 要上传的文件
        File file = new File("E:\\data\\test.log");
        InputStream is=new FileInputStream(file);

        // 上传文件到minio
        client.putObject(bucket, "data/test.log", is, "text/plain");         // image/jps、null
    }

    // 下载文件
    public static void download() throws Exception {
        // Minio配置信息
        String url = "http://115.159.6.164:9005";
        String accessKey="admin";
        String secretKey="";
        String bucket="xiao";

        // 要下载的文件
        String fileName="person/person_1.txt";

        MinioClient client=new MinioClient(url, accessKey, secretKey);

        // 判断文件是否存在,不存在则抛异常
        client.statObject(bucket, fileName);

        // 下载文件
        client.getObject(bucket,  fileName, "E:\\data\\new_test.log");
    }
}
