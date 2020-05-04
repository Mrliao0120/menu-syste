package com.menu.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.menu.bean.AccountUser;
import com.menu.bean.MenuAe;
import com.menu.bean.MenuEvaluate;
import com.menu.bean.UserAccount;
import com.menu.dao.MenuEvaluateMapper;
import com.menu.dao.UserAccountMapper;
import com.menu.enums.SystemEnum;
import com.menu.exeception.ServletException;
import com.menu.service.MenuAeService;
import com.menu.util.AccountUserUtils;
import com.menu.util.ResultData;
import com.menu.util.UserAccountUtils;
import com.menu.vo.QueryIndexMenuAeVO;
import com.menu.vo.QueryMenuAeRequest;
import com.menu.vo.QueryMenuEvaluateDetailVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.menu.dao.MenuAeMapper;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    UserAccountMapper userAccountMapper;
    @Autowired
    MenuEvaluateMapper menuEvaluateMapper;


    @Override
    public ResultData<PageInfo<MenuAe>> queryByCondition(QueryMenuAeRequest queryMenuAeRequest, HttpServletRequest httpServletRequest) {
        UserAccount userAccount = UserAccountUtils.getUserAccount();
        if (userAccount==null){
            throw new ServletException(SystemEnum.ACCOUNT_NOT_LOGGED_IN.getCode(),SystemEnum.ACCOUNT_NOT_LOGGED_IN.getMsg());
        }
        ResultData<PageInfo<MenuAe>> resultData=new ResultData<>();
        Page<MenuAe> objects = PageHelper.startPage(queryMenuAeRequest.getCurrPage(), queryMenuAeRequest.getPageSize());
        if (queryMenuAeRequest.getOrderBy()==null||queryMenuAeRequest.getOrderBy()==""){
            PageHelper.orderBy("gmt_create desc");
        }
        List<MenuAe> menuAes = menuAeMapper.queryByPageAndCondition(queryMenuAeRequest);
        PageInfo info = new PageInfo<>(objects.getResult());
        List<Long> menuAeListId = menuAes.stream().map(MenuAe::getId).collect(Collectors.toList());
        List<MenuEvaluate> menuEvaluates=new ArrayList<>();
        if(menuAeListId!=null&&menuAeListId.size()>0){
           menuEvaluates = menuEvaluateMapper.queryByMenuId(menuAeListId);
        }
        Map<Long, List<MenuEvaluate>> collect = menuEvaluates.stream().collect(Collectors.groupingBy(MenuEvaluate::getMenuId));
        menuAes.forEach(x->{
            List<MenuEvaluate> menuEvaluates1 = collect.get(x.getId());
            if (menuEvaluates1!=null&&menuEvaluates1.size()>0){
                BigDecimal  countBig=BigDecimal.ZERO;
                for (MenuEvaluate y:menuEvaluates1) {
                    countBig=countBig.add(BigDecimal.valueOf(y.getMenuEvaluateScore()));
                }
                countBig= countBig.divide(BigDecimal.valueOf(menuEvaluates1.size()));
                x.setMenuEvaluateScore(countBig.intValue());
            }
        });
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

    @Override
    public  QueryIndexMenuAeVO queryByAndIndexId(Long id) {
        UserAccount userAccount = UserAccountUtils.getUserAccount();
        if (userAccount==null){
            throw new ServletException(SystemEnum.ACCOUNT_NOT_LOGGED_IN.getCode(),SystemEnum.ACCOUNT_NOT_LOGGED_IN.getMsg());
        }
        MenuAe menuAe = menuAeMapper.selectByPrimaryKey(id);
        if (menuAe!=null){
            QueryIndexMenuAeVO queryIndexMenuAeVO=new QueryIndexMenuAeVO();
            BeanUtils.copyProperties(menuAe,queryIndexMenuAeVO);
            List<Long>list=new LinkedList<>();
            list.add(menuAe.getId());
            //查询评论相关
            List<MenuEvaluate> menuEvaluates = menuEvaluateMapper.queryByMenuId(list);
            if (menuEvaluates!=null&&menuEvaluates.size()>0){
                BigDecimal  countBig=BigDecimal.ZERO;
                for (MenuEvaluate y:menuEvaluates) {
                    countBig=countBig.add(BigDecimal.valueOf(y.getMenuEvaluateScore()));
                }
                countBig= countBig.divide(BigDecimal.valueOf(menuEvaluates.size()));
                menuAe.setMenuEvaluateScore(countBig.intValue());
                queryIndexMenuAeVO.setMenuEvaluateScore(countBig.intValue());
            }
            //查询本人相关评论
            List<MenuEvaluate> menuEvaluates1 = menuEvaluateMapper.selectByMenuIdAndUserId(menuAe.getId(), userAccount.getId(),null);
            if (menuEvaluates1!=null&&menuEvaluates1.size()>0){
                MenuEvaluate menuEvaluate = menuEvaluates1.get(0);
                MenuEvaluate menuEvaluate1 = menuEvaluateMapper.selectByParentKey(menuEvaluate.getId());
                if (menuEvaluate1!=null){
                    List<QueryMenuEvaluateDetailVO> queryMenuEvaluateDetailVOS=new ArrayList<>();
                    QueryMenuEvaluateDetailVO queryMenuEvaluateDetailVO=new QueryMenuEvaluateDetailVO();
                    BeanUtils.copyProperties(menuEvaluate1,queryMenuEvaluateDetailVO);
                    queryMenuEvaluateDetailVOS.add(queryMenuEvaluateDetailVO);
                    queryIndexMenuAeVO.setQueryMenuEvaluateDetailVOS(queryMenuEvaluateDetailVOS);
                }
                queryIndexMenuAeVO.setMenuEvaluate(menuEvaluate);
            }
            return queryIndexMenuAeVO;
        }
        return null;
    }

    @Override
    public List<MenuAe> findMenuAeLimit5() {
        List<MenuAe> menuAeLimit5 = menuAeMapper.findMenuAeLimit5();
        return menuAeLimit5;
    }
}
