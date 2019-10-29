package com.menu.service.impl;

import com.menu.bean.AccountUser;
import com.menu.dao.AccountUserMapper;
import com.menu.enums.SystemEnum;
import com.menu.enums.UserLockEnum;
import com.menu.exeception.ServletException;
import com.menu.service.AccountUserService;
import com.menu.util.AccountTokenUtils;
import com.menu.util.AccountUserUtils;
import com.menu.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @ProjectName menu-system
 * @Author LHB
 * @Data 2019/10/28 12:59
 * @Version 1.0
 * @Description 后台账号
 */
@Service
public class AccountUserServiceImpl implements AccountUserService {

    @Autowired
    AccountUserMapper accountUserMapper;

    //盐
    private final  String  salt="backgroundMenu";

    @Autowired
    AccountUserUtils accountUserUtils;
    @Override
    public AccountUser insertAccountUser(AccountUser accountUser, HttpServletResponse httpServletResponse) {
        AccountUser accountUser1 = accountUserMapper.selectByUserName(accountUser.getUsername());
        if (accountUser1!=null){
            throw new ServletException(SystemEnum.ACCOUNT_ALREADY_EXISTS.getCode(),SystemEnum.ACCOUNT_ALREADY_NO_EXISTS.getMsg());
        }
        accountUser.setId(null);
        String s = MD5Utils.md5Encryption(salt, accountUser.getPassword());
        accountUser.setPassword(s);
        accountUser.setIsDelete(0);
        accountUser.setGmtCreate(new Date());
        accountUserMapper.insertSelective(accountUser);
        String token = AccountTokenUtils.getToken();
        httpServletResponse.setHeader("BackgroundToken",token);
        return accountUser;
    }

    @Override
    public AccountUser login(AccountUser accountUser, HttpServletResponse httpServletResponse) {
        AccountUser accountUser1 = accountUserMapper.selectByUserName(accountUser.getUsername());
        if (accountUser1==null){
            throw new ServletException(SystemEnum.ACCOUNT_ALREADY_NO_EXISTS.getCode(),SystemEnum.ACCOUNT_ALREADY_NO_EXISTS.getMsg());
        }
        if (accountUser1.getIsLock()==1){
            throw new ServletException(UserLockEnum.ACCOUNT_LOCKED.getCode(),
                    UserLockEnum.ACCOUNT_LOCKED.getMsg());
        }
        String s = MD5Utils.md5Encryption(salt, accountUser.getPassword());
        //密码验证
        if (!accountUser1.getPassword().equals(s)){
            throw new ServletException(SystemEnum.INCORRECT_ACCOUNT_OR_PASSWORD.getCode(),
                    SystemEnum.INCORRECT_ACCOUNT_OR_PASSWORD.getMsg());
        }
        accountUserUtils.deleteToken(accountUser1);
        String token = accountUserUtils.setToken(accountUser1);
        httpServletResponse.setHeader("Access-Control-Expose-Headers", "BackgroundToken");
        httpServletResponse.setHeader("BackgroundToken",token);
        return accountUser1;
    }


    @Override
    public void loginOut() {
        AccountUser accountUser1 = AccountUserUtils.getUserAccount();
        if (accountUser1!=null){
            accountUserUtils.deleteToken(accountUser1);
        }
    }


    @Override
    public void deleteUser(Long id) {
        AccountUser accountUser = accountUserMapper.selectByPrimaryKey(id);
        accountUser.setIsDelete(1);
        accountUserMapper.updateByPrimaryKeySelective(accountUser);
    }




}
