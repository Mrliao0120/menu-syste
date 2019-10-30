package com.menu.util;


import com.menu.bean.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedInheritableThreadLocal;
import org.springframework.core.NamedThreadLocal;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName
 * @Author
 * @Data 2019/7/3 17:44
 * @Version 1.0
 * @Description   用来获取已经登录的用户
 */
@Component
public class UserAccountUtils {

    @Autowired
    public RedisTemplate redisTemplate;

    public final String ACCOUNT_TOKEN_KEY="MENU:ACCOUNT:TOKEN:";
    public final String ACCOUNT_USERNAME_KEY="MENU:ACCOUNT:USERNAME:";


    private static final ThreadLocal<UserAccount> CURRENT_ACCOUNT_USER_CONTEXT_THREAD_LOCAL = new NamedInheritableThreadLocal<>("UserAccount");


    public  static  void  setCurrentUserContextThreadLocal(UserAccount userContextThreadLocal){
        CURRENT_ACCOUNT_USER_CONTEXT_THREAD_LOCAL.set(userContextThreadLocal);
    }


    public  static  void  removeCurrentUserContextThreadLocal(){
        CURRENT_ACCOUNT_USER_CONTEXT_THREAD_LOCAL.remove();
    }

    /**
     * 获取用户
     * @return
     */
    public static UserAccount getUserAccount(){
        UserAccount userAccount = CURRENT_ACCOUNT_USER_CONTEXT_THREAD_LOCAL.get();
        return userAccount;
    }


    public String  setToken(UserAccount userAccount){
        String key =(String) redisTemplate.opsForValue().get(ACCOUNT_USERNAME_KEY + userAccount.getUsername());
        if (key!=null){
            List<String>keyList=new ArrayList<>();
            keyList.add(ACCOUNT_TOKEN_KEY+key);
            keyList.add(ACCOUNT_USERNAME_KEY + userAccount.getUsername());
            redisTemplate.delete(keyList);
        }
        String token = AccountTokenUtils.getToken();
        redisTemplate.opsForValue().set(ACCOUNT_TOKEN_KEY+token,userAccount.getId().toString());
        redisTemplate.opsForValue().set(ACCOUNT_USERNAME_KEY+userAccount.getUsername(),token);
        return token;
    }

    public void   deleteToken(UserAccount userAccount){
        String key =(String) redisTemplate.opsForValue().get(ACCOUNT_USERNAME_KEY + userAccount.getUsername());
        if (key!=null){
            List<String>keyList=new ArrayList<>();
            keyList.add(ACCOUNT_USERNAME_KEY + userAccount.getUsername());
            keyList.add(ACCOUNT_TOKEN_KEY+key);
            redisTemplate.delete(keyList);
        }
    }


}
