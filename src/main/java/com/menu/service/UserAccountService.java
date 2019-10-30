package com.menu.service;

import com.github.pagehelper.PageInfo;
import com.menu.bean.UserAccount;
import com.menu.util.ResultData;
import com.menu.vo.QueryAccountUserRequest;

import javax.servlet.http.HttpServletResponse;

/**
 * @ProjectName menu-system
 * @Author LHB
 * @Data 2019/10/28 13:25
 * @Version 1.0
 * @Description
 */
public interface UserAccountService {



    ResultData<UserAccount>  insertUserAccount(UserAccount userAccount, HttpServletResponse httpServletResponse);


    ResultData<UserAccount> login(UserAccount userAccount, HttpServletResponse httpServletResponse);


    ResultData  loginOut();


    ResultData<PageInfo<UserAccount>> queryBackGroundUserAccount(QueryAccountUserRequest queryAccountUserRequest);

    ResultData<UserAccount>  queryUserAccountById(Long id);

    void  deleteById(Long id);
}
