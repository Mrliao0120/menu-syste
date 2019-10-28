package com.menu.controller.web;

import com.menu.bean.UserAccount;
import com.menu.service.UserAccountService;
import com.menu.util.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @ProjectName menu-system
 * @Author LHB
 * @Data 2019/10/28 13:49
 * @Version 1.0
 * @Description
 */
@RequestMapping(value = "/user")
@RestController
public class UserAccountController {

    @Autowired
    UserAccountService userAccountService;



    /**
     * 用户注册
     * @param userAccount
     * @param httpServletResponse
     * @return
     */
    @RequestMapping(value = "/userRegistered")
    public ResultData  userRegistered(@RequestBody UserAccount userAccount, HttpServletResponse httpServletResponse){
         userAccountService.insertUserAccount(userAccount, httpServletResponse);
        return null;
    }

    /**
     * 用户登录
     * @param userAccount
     * @param httpServletResponse
     * @return
     */
    @RequestMapping(value = "/login")
    public ResultData  login(@RequestBody UserAccount userAccount, HttpServletResponse httpServletResponse){
        ResultData<UserAccount> login = userAccountService.login(userAccount, httpServletResponse);
        return login;
    }




    /**
     * 用户退出
     * @return
     */
    @RequestMapping(value = "/loginOut")
    public ResultData  loginOut(){
        ResultData login = userAccountService.loginOut();
        return login;
    }


}
