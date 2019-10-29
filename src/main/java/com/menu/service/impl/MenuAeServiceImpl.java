package com.menu.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.menu.bean.AccountUser;
import com.menu.bean.MenuAe;
import com.menu.enums.SystemEnum;
import com.menu.exeception.ServletException;
import com.menu.service.MenuAeService;
import com.menu.util.AccountUserUtils;
import com.menu.util.ResultData;
import com.menu.vo.QueryMenuAeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.menu.dao.MenuAeMapper;

import java.util.Date;
import java.util.List;

/**
 * @author
 * @Title:
 * @ProjectName menu-syste
 * @Description: TODO
 * @Date create in 19:11 2019/10/28
 */
@Service
public class MenuAeServiceImpl implements MenuAeService {


    @Autowired
    MenuAeMapper menuAeMapper;

    @Override
    public ResultData<PageInfo<MenuAe>> queryByCondition(QueryMenuAeRequest queryMenuAeRequest) {
        ResultData<PageInfo<MenuAe>> resultData=new ResultData<>();
        Page<MenuAe> objects = PageHelper.startPage(queryMenuAeRequest.getCurrPage(), queryMenuAeRequest.getPageSize());
        if (queryMenuAeRequest.getOrderBy()==null||queryMenuAeRequest.getOrderBy()==""){
            PageHelper.orderBy("gmt_create desc");
        }
        List<MenuAe> menuAes = menuAeMapper.queryByPageAndCondition(queryMenuAeRequest);
        PageInfo info = new PageInfo<>(objects.getResult());
        info.setList(menuAes);
        resultData.setData(info);
        return resultData;
    }

    @Override
    public ResultData<PageInfo<MenuAe>> queryByBackGroundCondition(QueryMenuAeRequest queryMenuAeRequest) {
        AccountUser userAccount = AccountUserUtils.getUserAccount();
        if (userAccount==null){
            throw new ServletException(SystemEnum.ACCOUNT_NOT_LOGGED_IN.getCode(),SystemEnum.ACCOUNT_NOT_LOGGED_IN.getMsg());
        }
        Page<MenuAe> objects = PageHelper.startPage(queryMenuAeRequest.getCurrPage(), queryMenuAeRequest.getPageSize());
        if (queryMenuAeRequest.getOrderBy()==null||queryMenuAeRequest.getOrderBy()==""){
            PageHelper.orderBy("gmt_create desc");
        }
        queryMenuAeRequest.setCreateUserId(userAccount.getId());
        List<MenuAe> menuAes = menuAeMapper.queryByBackgroundPageAndCondition(queryMenuAeRequest);
        PageInfo info = new PageInfo<>(objects.getResult());
        info.setList(menuAes);
        ResultData<PageInfo<MenuAe>> resultData=new ResultData<>();
        resultData.setData(info);
        return resultData;
    }

    @Override
    public void updateMenuAe(MenuAe menuAe) {
        AccountUser userAccount = AccountUserUtils.getUserAccount();
        if (userAccount==null){
            throw new ServletException(SystemEnum.ACCOUNT_NOT_LOGGED_IN.getCode(),SystemEnum.ACCOUNT_NOT_LOGGED_IN.getMsg());
        }
        if (menuAe==null||menuAe.getId()==null){
            throw new ServletException(SystemEnum.THE_PARAMETER_IS_INCORRECT.getCode(),SystemEnum.THE_PARAMETER_IS_INCORRECT.getMsg());
        }
        menuAeMapper.updateByPrimaryKeySelective(menuAe);
    }

    @Override
    public MenuAe insertMenuAe(MenuAe menuAe) {
        AccountUser userAccount = AccountUserUtils.getUserAccount();
        if (userAccount==null){
            throw new ServletException(SystemEnum.ACCOUNT_NOT_LOGGED_IN.getCode(),SystemEnum.ACCOUNT_NOT_LOGGED_IN.getMsg());
        }
        menuAe.setGmtCreate(new Date());
        menuAe.setIsDelete(0);
        menuAe.setCreateUserId(userAccount.getId());
        menuAeMapper.insertSelective(menuAe);
        return menuAe;
    }

    @Override
    public MenuAe queryById(Long id) {
        MenuAe menuAe = menuAeMapper.selectByPrimaryKey(id);
        return menuAe;
    }
}
