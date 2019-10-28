package com.menu.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Random;
import java.util.UUID;

/**
 * @ProjectName ddcApp-v2
 * @Author
 * @Data 2019/7/3 15:01
 * @Version 1.0
 * @Description
 */
public class AccountTokenUtils {

    /**
     * 生成token
     * @return
     */
    public static   String  getToken(){
        Random random=new Random();
        String tokenAfter = UUID.randomUUID() + String.valueOf(random.nextLong());
        String token = DigestUtils.md5Hex(tokenAfter);
        return  token;
    }






}
