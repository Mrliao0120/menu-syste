package com.menu.controller;

import com.aliyun.oss.common.utils.DateUtil;
import com.menu.util.ResultData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.Instant;

/**
 * @ProjectName menu-system
 * @Author LHB
 * @Data 2019/10/28 13:31
 * @Version 1.0
 * @Description
 */
@RestController
@RequestMapping(value = "/util")
public class UtilController {

    @Value("${upload.file.path}")
    private String  upload;
    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping(value = "/uploadFile")
    public ResultData uploadFile(@RequestParam("file") CommonsMultipartFile file){
        ResultData resultData=new ResultData();
        if (file.isEmpty()) {
            resultData.setMsg("上传失败");
            resultData.setCode(202);
            return resultData;
        }
        long fileTime = System.currentTimeMillis();
        try {
            //所在文件夹
            String path=upload+"/";
            //文件全路径
            String filte=path+fileTime+file.getOriginalFilename();
            File newFile=new File(filte);
            if(!newFile.exists()){
                newFile.mkdirs();
            }
            //拷贝   spring封装方法  相当于上传
            file.transferTo(newFile);
            resultData.setData(filte);
            return resultData;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }


    /**
     * 获取本地图片
     * @param response
     * @param pathName
     */
    @RequestMapping(value = "/queryLocalHostImage")
    public void  queryLocalHostImage(HttpServletResponse response,@RequestParam(value = "pathName",required=true) String pathName){
        if(pathName==null||pathName==""){
            return;
        }
        File imgFile = new File(pathName);
        FileInputStream fin = null;
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            fin = new FileInputStream(imgFile);
            byte[] arr = new byte[1024 * 10];
            int n;
            while ((n = fin.read(arr)) != -1) {
                output.write(arr, 0, n);
            }
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return;
    }


}
