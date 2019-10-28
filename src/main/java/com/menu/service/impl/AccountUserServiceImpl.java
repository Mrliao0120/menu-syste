package com.menu.service.impl;

import com.menu.bean.AccountUser;
import com.menu.dao.AccountUserMapper;
import com.menu.enums.SystemEnum;
import com.menu.exeception.ServletException;
import com.menu.service.AccountUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public void insertAccountUser(AccountUser accountUser) {
        AccountUser accountUser1 = accountUserMapper.selectByUserName(accountUser.getUsername());
        if (accountUser1!=null){
            throw new ServletException(SystemEnum.ACCOUNT_ALREADY_EXISTS.getCode(),SystemEnum.ACCOUNT_ALREADY_NO_EXISTS.getMsg());
        }
        accountUser.setId(null);
        accountUserMapper.insertSelective(accountUser);
    }
}
