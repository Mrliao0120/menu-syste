package com.menu.util;

import com.menu.bean.AccountUser;
import com.menu.bean.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @Title:
 * @ProjectName menu-syste
 * @Description: 后台用户拦截
 * @Date create in 19:50 2019/10/28
 */
@Component
public class AccountUserUtils {
    @Autowired
    public RedisTemplate redisTemplate;

    public final String ACCOUNT_TOKEN_KEY="BACKGROUND:ACCOUNT:TOKEN:";
    public final String ACCOUNT_USERNAME_KEY="BACKGROUND:ACCOUNT:USERNAME:";


    private static final ThreadLocal<AccountUser> CURRENT_USER_CONTEXT_THREAD_LOCAL = new NamedThreadLocal<>("userAccount");


    public  static  void  setCurrentUserContextThreadLocal(AccountUser userContextThreadLocal){
        CURRENT_USER_CONTEXT_THREAD_LOCAL.set(userContextThreadLocal);
    }


    public  static  void  removeCurrentUserContextThreadLocal(){
        CURRENT_USER_CONTEXT_THREAD_LOCAL.remove();
    }

    /**
     * 获取用户
     * @return
     */
    public static AccountUser getUserAccount(){
        AccountUser userAccount = CURRENT_USER_CONTEXT_THREAD_LOCAL.get();
        return userAccount;
    }


    public String  setToken(AccountUser userAccount){
        String key =(String) redisTemplate.opsForValue().get(ACCOUNT_USERNAME_KEY + userAccount.getUsername());
        if (key!=null){
            List<String> keyList=new ArrayList<>();
            keyList.add(ACCOUNT_TOKEN_KEY+key);
            keyList.add(ACCOUNT_USERNAME_KEY + userAccount.getUsername());
            redisTemplate.delete(keyList);
        }
        String token = AccountTokenUtils.getToken();
        redisTemplate.opsForValue().set(ACCOUNT_USERNAME_KEY+userAccount.getUsername(),token);
        redisTemplate.opsForValue().set(ACCOUNT_TOKEN_KEY+token,userAccount.getId().toString());

        return token;
    }

    public void   deleteToken(AccountUser userAccount){
        String key =(String) redisTemplate.opsForValue().get(ACCOUNT_USERNAME_KEY + userAccount.getUsername());
        if (key!=null){
            List<String>keyList=new ArrayList<>();
            keyList.add(ACCOUNT_TOKEN_KEY+key);
            keyList.add(ACCOUNT_USERNAME_KEY + userAccount.getUsername());
            redisTemplate.delete(keyList);
        }
    }
}
