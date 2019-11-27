package com.menu.controller.web;

import com.menu.bean.UserAccount;
import com.menu.service.UserAccountService;
import com.menu.util.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
@Api(value = "用户相关API")
public class UserAccountController {

    @Autowired
    UserAccountService userAccountService;
    @Autowired
    RedisTemplate redisTemplate;


    /**
     * 用户注册
     * @param userAccount
     * @param httpServletResponse
     * @return
     */
    @PostMapping(value = "/userRegistered")
    public ResultData  userRegistered(@RequestBody UserAccount userAccount, HttpServletResponse httpServletResponse){
        ResultData<UserAccount> userAccountResultData = userAccountService.insertUserAccount(userAccount, httpServletResponse);
        return userAccountResultData;
    }


    /**
     * 用户更新
     * @param userAccount
     * @param
     * @return
     */
    @PostMapping(value = "/updatePassWordAndNickName")
    @ApiOperation(value = "用户更新接口")
    public ResultData  updatePassWordAndNickName(@RequestBody UserAccount userAccount){
        ResultData userAccountResultData = userAccountService.updatePassWordAndNickName(userAccount);
        return userAccountResultData;
    }




    /**
     * 用户登录
     * @param userAccount
     * @param httpServletResponse
     * @return
     */
    @RequestMapping(value = "/login")
    @ApiOperation(value = "用户登录接口-返回值会有token 请缓存下来 每次接口需要再Heard中传入token")
    public ResultData  login(@RequestBody UserAccount userAccount, HttpServletResponse httpServletResponse){
        ResultData<UserAccount> login = userAccountService.login(userAccount, httpServletResponse);
        return login;
    }




    /**
     * 用户退出
     * @return
     */
    @RequestMapping(value = "/loginOut")
    @ApiOperation(value = "用户退出接口")
    public ResultData  loginOut(){
        ResultData login = userAccountService.loginOut();
        return login;
    }


    /**
     * 检测token
     * @param
     * @return
     */
    @PostMapping(value = "/queryToken")
    @ApiOperation(value = "检测token接口")
    public ResultData queryToken(HttpServletRequest httpServletRequest){
        ResultData resultData=new ResultData();
        String backgroundToken = httpServletRequest.getHeader("token");
        if (backgroundToken!=null){
            Object o = redisTemplate.opsForValue().get("MENU:ACCOUNT:TOKEN:" + backgroundToken);
            if (o!=null){
                resultData.setData(o);
                resultData.setCode(200);
            }else {
                resultData.setCode(502);
            }
        }else {
            resultData.setCode(502);
        }
        return resultData;
    }



}
