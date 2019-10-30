package com.menu.controller.background;

import com.github.pagehelper.PageInfo;
import com.menu.bean.AccountUser;
import com.menu.bean.UserAccount;
import com.menu.enums.SystemEnum;
import com.menu.exeception.ServletException;
import com.menu.service.UserAccountService;
import com.menu.util.AccountUserUtils;
import com.menu.util.ResultData;
import com.menu.vo.QueryAccountUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @ProjectName menu-system
 * @Author LHB
 * @Data 2019/10/28 13:49
 * @Version 1.0
 * @Description
 */
@RequestMapping(value = "/backGroundUser")
@RestController
public class UserAccountBackGroundController {

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
        AccountUser accountUser1 = AccountUserUtils.getUserAccount();
        if (accountUser1==null){
            throw new ServletException(SystemEnum.THE_PARAMETER_IS_INCORRECT.getCode(),
                    SystemEnum.THE_PARAMETER_IS_INCORRECT.getMsg());
        }
        ResultData<UserAccount> userAccountResultData = userAccountService.insertUserAccount(userAccount, httpServletResponse);
        return userAccountResultData;
    }



    /**
     * 用户列表
     * @param queryAccountUserRequest
     * @return
     */
    @PostMapping(value = "/queryUserAccountPage")
    public ResultData<PageInfo<UserAccount>> queryUserAccountPage(@RequestBody QueryAccountUserRequest queryAccountUserRequest){
        AccountUser accountUser1 = AccountUserUtils.getUserAccount();
        if (accountUser1==null){
            throw new ServletException(SystemEnum.THE_PARAMETER_IS_INCORRECT.getCode(),
                    SystemEnum.THE_PARAMETER_IS_INCORRECT.getMsg());
        }
        ResultData<PageInfo<UserAccount>> resultData = userAccountService.queryBackGroundUserAccount(queryAccountUserRequest);
        return resultData;
    }



    /**
     * 查询用户详情
     * @param id
     * @return
     */
    @PostMapping(value = "/queryUserAccountById")
    public ResultData<UserAccount> queryUserAccountById(@RequestParam(value = "id",required = true)Long id){
        AccountUser accountUser1 = AccountUserUtils.getUserAccount();
        if (accountUser1==null){
            throw new ServletException(SystemEnum.THE_PARAMETER_IS_INCORRECT.getCode(),
                    SystemEnum.THE_PARAMETER_IS_INCORRECT.getMsg());
        }
        ResultData<UserAccount> resultData = userAccountService.queryUserAccountById(id);
        return resultData;
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @PostMapping(value = "/deleteById")
    public ResultData deleteById(@RequestParam(value = "id",required = true)Long id){
        AccountUser accountUser1 = AccountUserUtils.getUserAccount();
        if (accountUser1==null){
            throw new ServletException(SystemEnum.THE_PARAMETER_IS_INCORRECT.getCode(),
                    SystemEnum.THE_PARAMETER_IS_INCORRECT.getMsg());
        }
       userAccountService.deleteById(id);
        return new ResultData();
    }



}
