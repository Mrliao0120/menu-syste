package com.menu.service;

import com.menu.bean.AccountUser;

import javax.servlet.http.HttpServletResponse;

/**
 * @ProjectName menu-system
 * @Author LHB
 * @Data 2019/10/28 12:59
 * @Version 1.0
 * @Description
 */
public interface AccountUserService {




    AccountUser  insertAccountUser(AccountUser accountUser, HttpServletResponse httpServletResponse);


    AccountUser  login(AccountUser accountUser, HttpServletResponse httpServletResponse);

    void  loginOut();

    void   deleteUser(Long id);

}
