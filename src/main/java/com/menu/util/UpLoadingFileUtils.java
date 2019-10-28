package com.menu.util;

import com.aliyun.oss.OSSClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ProjectName qqw-product-library-api-swagger
 * @Author
 * @Data 2019/5/17 14:49
 * @Version 1.0
 * @Description
 */
@Slf4j
public class UpLoadingFileUtils {

   private static ThreadLocal<SimpleDateFormat> threadLocal=new ThreadLocal<SimpleDateFormat>(){
       @Override
       protected SimpleDateFormat initialValue() {
           return  new SimpleDateFormat("yyyyMMddHHssmm");
       }
   };


    private static String accessKeyId = "ua7zqDXAHlDeHLHD";
    private static String accessKeySecret = "m1Qe7HZICMlzb1K8V0pBxmHnkp48os";
    private static String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
    private static String bucketName = "qqddc";
    private static String folder="qqddcEnclosure/";

    /**
     * 上传文件
     * @throws IOException
     */
    public static String uploadFile(CommonsMultipartFile commonsMultipartFile) {
        //根据系统时间插入

        SimpleDateFormat simpleDateFormat = threadLocal.get();
        String format = simpleDateFormat.format(new Date());
        threadLocal.remove();
        OSSClient client = new OSSClient(endpoint,accessKeyId, accessKeySecret);
        InputStream inputStream = null;
        try {
            inputStream = commonsMultipartFile.getInputStream();
        } catch (IOException e) {
            log.error("UpLoadingFileUtils.uploadFile InputStream ERROR");
        }
        //文件原名
        String originalFilename = commonsMultipartFile.getOriginalFilename();
        int i = originalFilename.indexOf(".");
        //后缀名
        String substring = originalFilename.substring(i, originalFilename.length());
        String fileName = folder + format+substring;
        client.putObject(bucketName, fileName, inputStream);
        client.shutdown();
        String path=fileName;

        try {
            inputStream.close();
        } catch (IOException e) {
            log.error("UpLoadingFileUtils.uploadFile IO inputStream.close() ERROR");
        }
        return path;

    }

    /**
     * 上传内容  根据Byte字节上传
     * @throws IOException
     */
    public static String uploadFileByte(byte[] contents){
        //根据系统时间插入
        SimpleDateFormat simpleDateFormat = threadLocal.get();
        String format = simpleDateFormat.format(new Date());
        threadLocal.remove();
        OSSClient client = new OSSClient(endpoint,accessKeyId, accessKeySecret);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(contents);
        String fileName = folder + format;
        client.putObject(bucketName, fileName, byteArrayInputStream);
        client.shutdown();
        try {
            byteArrayInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (byteArrayInputStream!=null){
                try {
                    byteArrayInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fileName;
    }







}
