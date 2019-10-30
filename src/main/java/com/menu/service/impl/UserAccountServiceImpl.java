package com.menu.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.menu.bean.AccountUser;
import com.menu.bean.UserAccount;
import com.menu.dao.UserAccountMapper;
import com.menu.enums.SystemEnum;
import com.menu.enums.UserLockEnum;
import com.menu.exeception.ServletException;
import com.menu.service.UserAccountService;
import com.menu.util.*;
import com.menu.vo.QueryAccountUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

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
        userAccount.setGmtCreate(new Date());
        userAccount.setIsDelete(0);
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
        if (userAccount.getIsLock()==1){
            throw new ServletException(UserLockEnum.ACCOUNT_LOCKED.getCode(),
                    UserLockEnum.ACCOUNT_LOCKED.getMsg());
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

    @Override
    public ResultData<PageInfo<UserAccount>> queryBackGroundUserAccount(QueryAccountUserRequest queryAccountUserRequest) {
        AccountUser accountUser1 = AccountUserUtils.getUserAccount();
        if (accountUser1==null){
            throw new ServletException(SystemEnum.ACCOUNT_ALREADY_NO_EXISTS.getCode(),SystemEnum.ACCOUNT_ALREADY_NO_EXISTS.getMsg());
        }
        Page<UserAccount> objects = PageHelper.startPage(queryAccountUserRequest.getCurrPage(), queryAccountUserRequest.getPageSize());
        if (queryAccountUserRequest.getOrderBy()==null||queryAccountUserRequest.getOrderBy()==""){
            PageHelper.orderBy("gmt_create desc");
        }
        List<UserAccount> accountUsers = userAccountMapper.bgQueryUserAccountPage(queryAccountUserRequest);
        ResultData<PageInfo<UserAccount>> resultData=new ResultData<>();
        PageInfo info = new PageInfo<>(objects.getResult());
        info.setList(accountUsers);
        resultData.setData(info);
        return resultData;
    }

    @Override
    public ResultData<UserAccount> queryUserAccountById(Long id) {
        ResultData<UserAccount> resultData=new ResultData<>();
        UserAccount userAccount = userAccountMapper.selectByPrimaryKey(id);
        resultData.setData(userAccount);
        return resultData;
    }

    @Override
    public void deleteById(Long id) {
        UserAccount userAccount = userAccountMapper.selectByPrimaryKey(id);
        userAccount.setIsDelete(1);
        userAccountMapper.updateByPrimaryKeySelective(userAccount);
    }
}
