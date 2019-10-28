package com.menu.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @ProjectName movies_box
 * @Author
 * @Data 2019/5/23 16:41
 * @Version 1.0
 * @Description
 */
public class MD5Utils {


    /**
     * MD5加盐
     * @param salt  盐
     * @param text 文本
     * @return
     */
    public static String md5Encryption(String salt,String text){
        String s = DigestUtils.md5Hex(salt + text);
        return s;
    }

    /**
     * 验证MD5
     * @param key
     * @param text
     * @param md5
     * @return
     */
    public static boolean verification(String key,String text,String md5){
        if (md5!=null){
            String data = DigestUtils.md5Hex(key + text);
            if (md5.equals(data)){
                return true;
            }
        }
        return false;
    }






}
