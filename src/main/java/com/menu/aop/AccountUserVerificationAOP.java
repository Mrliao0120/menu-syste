package com.menu.aop;

import com.menu.bean.AccountUser;
import com.menu.dao.AccountUserMapper;
import com.menu.util.AccountUserUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lhb
 * @Title:
 * @ProjectName menu-syste
 * @Description: TODO
 * @Date create in 19:52 2019/10/28
 */
public class AccountUserVerificationAOP {


    @Autowired
    public RedisTemplate redisTemplate;
    @Autowired
    public AccountUserMapper accountUserMapper;
    public final String ACCOUNT_TOKEN_KEY="MENU:ACCOUNT:TOKEN:";


    @Pointcut("execution(public * com.menu.controller.background.*.*(..))")
    public void  checkToken(){}

    @Before("checkToken()")
    public void beforeCheckToken(JoinPoint joinPoint){
        AccountUserUtils.removeCurrentUserContextThreadLocal();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String headerName = request.getHeader("token");
        if (headerName!=null){
            String key = redisTemplate.opsForValue().get(ACCOUNT_TOKEN_KEY + headerName).toString();
            if (key!=null){
                AccountUser userAccount = accountUserMapper.selectByPrimaryKey(Long.valueOf(key));
                AccountUserUtils.setCurrentUserContextThreadLocal(userAccount);
            }
        }
    }
}
