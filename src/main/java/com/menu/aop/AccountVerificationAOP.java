package com.menu.aop;

import com.menu.bean.UserAccount;
import com.menu.dao.UserAccountMapper;
import com.menu.util.UserAccountUtils;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName ddcApp-v2
 * @Author LHB
 * @Data 2019/7/3 14:57
 * @Version 1.0
 * @Description
 */
@Aspect
@Component
@Slf4j
public class AccountVerificationAOP {

    @Autowired
    public RedisTemplate redisTemplate;
    @Autowired
    public UserAccountMapper userAccountMapper;
    public final String ACCOUNT_TOKEN_KEY="MENU:ACCOUNT:TOKEN:";


    @Pointcut("execution(public * com.menu.controller.web.*.*(..))")
    public void  checkToken(){}

    @Before("checkToken()")
    public void beforeCheckToken(JoinPoint joinPoint){
        UserAccountUtils.removeCurrentUserContextThreadLocal();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String headerName = request.getHeader("token");
        if (headerName!=null){
            String key = redisTemplate.opsForValue().get(ACCOUNT_TOKEN_KEY + headerName).toString();
            if (key!=null){
                UserAccount userAccount = userAccountMapper.selectByPrimaryKey(Long.valueOf(key));
                UserAccountUtils.setCurrentUserContextThreadLocal(userAccount);
            }
        }
    }




}
