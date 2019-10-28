package com.menu.service.impl;

import com.menu.bean.AccountUser;
import com.menu.bean.UserAccount;
import com.menu.dao.UserAccountMapper;
import com.menu.enums.SystemEnum;
import com.menu.exeception.ServletException;
import com.menu.service.UserAccountService;
import com.menu.util.AccountTokenUtils;
import com.menu.util.MD5Utils;
import com.menu.util.ResultData;
import com.menu.util.UserAccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

/**
 * @ProjectName menu-system
 * @Author LHB
 * @Data 2019/10/28 13:26
 * @Version 1.0
 * @Description  普通账号
 */
@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    UserAccountMapper userAccountMapper;

    @Autowired
    UserAccountUtils userAccountUtils;

    @Autowired
    RedisTemplate redisTemplate;
    //盐
    private final  String  salt="menu";

    @Override
    public ResultData<UserAccount> insertUserAccount(UserAccount userAccount, HttpServletResponse httpServletResponse) {
        //参数效验
        userAccount.checkInsertParameter(userAccount);
        UserAccount userAccount1 = userAccountMapper.selectByUserName(userAccount.getUsername());
        if (userAccount1!=null){
            throw new ServletException(SystemEnum.ACCOUNT_ALREADY_EXISTS.getCode(),SystemEnum.ACCOUNT_ALREADY_NO_EXISTS.getMsg());
        }
        ResultData<UserAccount> resultData=new ResultData<>();
        userAccount.setId(null);
        //密码加密
        String s = MD5Utils.md5Encryption(salt, userAccount.getPassword());
        userAccount.setPassword(s);
        userAccountMapper.insertSelective(userAccount);
        String token = userAccountUtils.setToken(userAccount);
        httpServletResponse.setHeader("token",token);
        userAccount.setPassword(null);
        resultData.setData(userAccount);
        return resultData;
    }

    @Override
    public ResultData<UserAccount> login(UserAccount userAccount, HttpServletResponse httpServletResponse) {
        userAccount.checkLoginParameter(userAccount);
        ResultData<UserAccount> resultData=new ResultData<>();
        UserAccount loginUserInfo = userAccountMapper.selectByUserName(userAccount.getUsername());
        if (loginUserInfo==null){
            throw new ServletException(SystemEnum.ACCOUNT_ALREADY_EXISTS.getCode(),
                    SystemEnum.ACCOUNT_ALREADY_NO_EXISTS.getMsg());
        }
        String s = MD5Utils.md5Encryption(salt, userAccount.getPassword());
        //密码验证
        if (!loginUserInfo.getPassword().equals(s)){
            throw new ServletException(SystemEnum.INCORRECT_ACCOUNT_OR_PASSWORD.getCode(),
                    SystemEnum.INCORRECT_ACCOUNT_OR_PASSWORD.getMsg());
        }
        resultData.setData(loginUserInfo);
        //token操作相关
        userAccountUtils.deleteToken(userAccount);
        String token = userAccountUtils.setToken(userAccount);
        httpServletResponse.setHeader("token",token);
        return resultData;
    }

    @Override
    public ResultData loginOut() {
        UserAccount userAccount = UserAccountUtils.getUserAccount();
        if (userAccount!=null){
            userAccountUtils.deleteToken(userAccount);
        }
        return new ResultData();
    }
}
