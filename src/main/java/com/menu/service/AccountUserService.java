package com.menu.service;

import com.github.pagehelper.PageInfo;
import com.menu.bean.AccountUser;
import com.menu.util.ResultData;
import com.menu.vo.QueryAccountUserRequest;

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


    void  updateAccount(AccountUser accountUser);

    ResultData<PageInfo<AccountUser>>  queryAccountPage(QueryAccountUserRequest queryAccountUserRequest);

}
