package com.menu.controller.background;

import com.menu.bean.AccountUser;
import com.menu.enums.SystemEnum;
import com.menu.enums.UserLockEnum;
import com.menu.exeception.ServletException;
import com.menu.service.AccountUserService;
import com.menu.util.AccountUserUtils;
import com.menu.util.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ProjectName menu-system
 * @Author LHB
 * @Data 2019/10/28 13:00
 * @Version 1.0
 * @Description
 */
@RequestMapping(value = "/account")
@RestController
public class AccountUserBackgroundController {

    @Autowired
    AccountUserService accountUserService;


    /**
     * 添加用户
     * @param accountUser
     * @return
     */
    @PostMapping(value = "/addAccount")
    public ResultData<AccountUser> addAccount(@RequestBody AccountUser accountUser, HttpServletResponse httpServletResponse){
        AccountUser accountUser1 = AccountUserUtils.getUserAccount();
        if (accountUser1==null){
            throw new ServletException(SystemEnum.THE_PARAMETER_IS_INCORRECT.getCode(),
                    SystemEnum.THE_PARAMETER_IS_INCORRECT.getMsg());
        }
        accountUser.checkParameter(accountUser);
        if (accountUser1.getSystemLevel()<accountUser.getSystemLevel()){
            throw new ServletException(SystemEnum.PERMISSION_IS_TOO_LOW.getCode(),
                    SystemEnum.PERMISSION_IS_TOO_LOW.getMsg());
        }
        ResultData<AccountUser> resultData=new ResultData<>();
        AccountUser accountUser2 = accountUserService.insertAccountUser(accountUser, httpServletResponse);
        resultData.setData(accountUser2);
        return resultData;
    }


    /**
     * 用户登录
     * @param accountUser
     * @return
     */
    @PostMapping(value = "/login")
    public ResultData<AccountUser> login(@RequestBody AccountUser accountUser, HttpServletResponse httpServletResponse){
        accountUser.checkLoginParameter(accountUser);
        AccountUser login = accountUserService.login(accountUser,httpServletResponse);
        ResultData<AccountUser> resultData=new ResultData<>();
        resultData.setData(login);
        return resultData;
    }



    /**
     * 删除用户 弱验证
     * @param id
     * @return
     */
    @PostMapping(value = "/deleteUser")
    public ResultData deleteUser(@RequestParam(value = "id",name = "id",required = true) Long id){
        accountUserService.deleteUser(id);
        return new ResultData();
    }


    /**
     * 用户登出
     * @param
     * @return
     */
    @PostMapping(value = "/loginOut")
    public ResultData loginOut(){
        accountUserService.loginOut();
        return new ResultData();
    }

}
