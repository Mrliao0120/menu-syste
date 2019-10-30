package com.menu.aop;

import com.menu.bean.AccountUser;
import com.menu.dao.AccountUserMapper;
import com.menu.util.AccountUserUtils;
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
 * @author
 * @Title:
 * @ProjectName menu-syste
 * @Description: TODO 后台切面
 * @Date create in 19:52 2019/10/28
 */
@Aspect
@Component
@Slf4j
public class AccountUserVerificationAOP {


    @Autowired
    public RedisTemplate redisTemplate;
    @Autowired
    public AccountUserMapper accountUserMapper;
    public final String ACCOUNT_TOKEN_KEY="BACKGROUND:ACCOUNT:TOKEN:";


    @Pointcut("execution(public * com.menu.controller.background.*.*(..))")
    public void  checkToken(){}

    @Before("checkToken()")
    public void beforeCheckToken(JoinPoint joinPoint){
        AccountUserUtils.removeCurrentUserContextThreadLocal();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String headerName = request.getHeader("BackgroundToken");
        if (headerName!=null){
            Object o = redisTemplate.opsForValue().get(ACCOUNT_TOKEN_KEY + headerName);
            if (o!=null){
                String key = o.toString();
                if (key!=null){
                    AccountUser userAccount = accountUserMapper.selectByPrimaryKey(Long.valueOf(key));
                    AccountUserUtils.setCurrentUserContextThreadLocal(userAccount);
                }
            }

        }
    }
}
