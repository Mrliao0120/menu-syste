package com.menu.controller;

import com.aliyun.oss.common.utils.DateUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
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


    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping(value = "/uploadFile")
    public String uploadFile(@RequestParam("file") CommonsMultipartFile file){
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        long fileTime = System.currentTimeMillis();
        try {
            //所在文件夹
            String path="/upload/"+fileTime+"/";
            //文件全路径
            String filte=path+fileTime+file.getOriginalFilename();
            File newFile=new File(filte);
            if(!newFile.exists()){
                newFile.mkdirs();
            }
            //拷贝   spring封装方法  相当于上传
            file.transferTo(newFile);
            return filte;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
