package com.menu.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.menu.bean.AccountUser;
import com.menu.bean.MenuAe;
import com.menu.bean.MenuEvaluate;
import com.menu.bean.UserAccount;
import com.menu.dao.AccountUserMapper;
import com.menu.dao.MenuAeMapper;
import com.menu.dao.MenuEvaluateMapper;
import com.menu.dao.UserAccountMapper;
import com.menu.enums.SystemEnum;
import com.menu.exeception.ServletException;
import com.menu.service.MenuAeService;
import com.menu.service.MenuEvaluateService;
import com.menu.util.AccountUserUtils;
import com.menu.util.ResultData;
import com.menu.util.UserAccountUtils;
import com.menu.vo.QueryMenuEvaluateDetailVO;
import com.menu.vo.QueryMenuEvaluateRequest;
import com.menu.vo.QueryMenuEvaluateVO;
import com.menu.vo.QueryMyEvaluateVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

/**
 * @author lhb
 * @Title:
 * @ProjectName menu-syste
 * @Description: TODO
 * @Date create in 20:34 2019/10/29
 */
@Service
public class MenuEvaluateServiceImpl implements MenuEvaluateService{

    @Autowired
    MenuEvaluateMapper menuEvaluateMapper;
    @Autowired
    MenuAeMapper menuAeMapper;
    @Autowired
    UserAccountMapper userAccountMapper;
    @Autowired
    AccountUserMapper accountUserMapper;
    @Override
    public ResultData<PageInfo<QueryMenuEvaluateVO>> queryPageByCondition(QueryMenuEvaluateRequest queryMenuEvaluateRequest) {
        AccountUser userAccount = AccountUserUtils.getUserAccount();
        if (userAccount==null){
            throw new ServletException(SystemEnum.ACCOUNT_NOT_LOGGED_IN.getCode(),SystemEnum.ACCOUNT_NOT_LOGGED_IN.getMsg());
        }
        Page<MenuEvaluate> objects = PageHelper.startPage(queryMenuEvaluateRequest.getCurrPage(), queryMenuEvaluateRequest.getPageSize());
        if (queryMenuEvaluateRequest.getOrderBy()==null||queryMenuEvaluateRequest.getOrderBy()==""){
            PageHelper.orderBy("gmt_create desc");
        }
        queryMenuEvaluateRequest.setCreateUserId(userAccount.getId());
        List<MenuEvaluate> menuEvaluates = menuEvaluateMapper.queryPageByCondition(queryMenuEvaluateRequest);
        List<QueryMenuEvaluateVO> list=new LinkedList<>();
        if (menuEvaluates!=null&&menuEvaluates.size()>0){
            //从对象里面提取ID 封装成集合
            List<Long> menuIdList = menuEvaluates.stream().map(MenuEvaluate::getMenuId).collect(Collectors.toList());
            //将集合中的值放置到菜品中去查询
            List<MenuAe> menuAes = menuAeMapper.queryByIds(menuIdList);
            //将集合中的值提取成map  long类型为菜品id
            Map<Long, MenuAe> collectMenuAe = menuAes.stream().collect(Collectors.toMap(MenuAe::getId, x -> x));
            menuEvaluates.forEach(x->{
                QueryMenuEvaluateVO queryMenuEvaluateVO=new QueryMenuEvaluateVO();
                //拷贝对象
                BeanUtils.copyProperties(x,queryMenuEvaluateVO);
                MenuAe ae = collectMenuAe.get(x.getMenuId());
                if (ae!=null){
                    queryMenuEvaluateVO.setMenuId(ae.getId());
                    queryMenuEvaluateVO.setMenuName(ae.getMenuName());
                }
                list.add(queryMenuEvaluateVO);
            });
        }
        ResultData<PageInfo<QueryMenuEvaluateVO>> resultData=new ResultData<>();
        PageInfo info = new PageInfo<>(objects.getResult());
        info.setList(list);
        resultData.setData(info);
        return resultData;
    }

    @Override
    public ResultData<QueryMenuEvaluateDetailVO> queryMenuEvaluateDetail(Long id) {
        ResultData<QueryMenuEvaluateDetailVO> resultData=new ResultData<>();
        QueryMenuEvaluateDetailVO queryMenuEvaluateDetailVO=new QueryMenuEvaluateDetailVO();
        MenuEvaluate menuEvaluate = menuEvaluateMapper.selectByPrimaryKey(id);
        if (menuEvaluate!=null){
            BeanUtils.copyProperties(menuEvaluate,queryMenuEvaluateDetailVO);
            UserAccount userAccount = userAccountMapper.selectByPrimaryKey(menuEvaluate.getUserId());
            MenuAe ae = menuAeMapper.selectByPrimaryKey(menuEvaluate.getMenuId());
            if (ae!=null){
                queryMenuEvaluateDetailVO.setMenuName(ae.getMenuName());
            }
            if (userAccount!=null){
                queryMenuEvaluateDetailVO.setNickName(userAccount.getNickname());
            }
            MenuEvaluate menuEvaluate1 = menuEvaluateMapper.selectByParentKey(menuEvaluate.getId());
            if (menuEvaluate1!=null){
                QueryMenuEvaluateDetailVO queryMenuEvaluateDetailVO2=new QueryMenuEvaluateDetailVO();
                BeanUtils.copyProperties(menuEvaluate1,queryMenuEvaluateDetailVO2);
                AccountUser accountUser = accountUserMapper.selectByPrimaryKey(menuEvaluate1.getUserId());
                queryMenuEvaluateDetailVO2.setNickName(accountUser.getUsername());
                queryMenuEvaluateDetailVO.setReturnMenuEvaluate(queryMenuEvaluateDetailVO2);
            }
        }
        resultData.setData(queryMenuEvaluateDetailVO);
        return resultData;
    }

    @Override
    public void addReturnMenuEvaluate(String textEvaluate, Long id) {
        AccountUser userAccount = AccountUserUtils.getUserAccount();
        if (userAccount==null){
            throw new ServletException(SystemEnum.ACCOUNT_NOT_LOGGED_IN.getCode(),SystemEnum.ACCOUNT_NOT_LOGGED_IN.getMsg());
        }
       /* MenuEvaluate menuEvaluate = menuEvaluateMapper.selectByPrimaryKey(id);*/
        MenuEvaluate returnMenuEvaluate =new  MenuEvaluate();
        returnMenuEvaluate.setIsDelete(0);
        returnMenuEvaluate.setGmtCreate(new Date());
        returnMenuEvaluate.setMenuEvaluate(textEvaluate);
        returnMenuEvaluate.setIsRootReply(1);
        returnMenuEvaluate.setMenuType(2);
        returnMenuEvaluate.setMenuEvaluateId(id);
        returnMenuEvaluate.setUserId(userAccount.getId());
        menuEvaluateMapper.insertSelective(returnMenuEvaluate);


    }

    @Override
    public void updateMenuEvaluate(MenuEvaluate evaluate) {
        menuEvaluateMapper.updateByPrimaryKeySelective(evaluate);
    }

    @Override
    public ResultData<PageInfo<QueryMenuEvaluateDetailVO>> queryByPageMenuEvaluate(QueryMenuEvaluateRequest queryMenuEvaluateRequest) {
        UserAccount userAccount = UserAccountUtils.getUserAccount();
        if (userAccount==null){
            throw new ServletException(SystemEnum.ACCOUNT_NOT_LOGGED_IN.getCode(),SystemEnum.ACCOUNT_NOT_LOGGED_IN.getMsg());
        }
        ResultData<PageInfo<QueryMenuEvaluateDetailVO>> resultData=new ResultData<>();
        Page<MenuEvaluate> objects = PageHelper.startPage(queryMenuEvaluateRequest.getCurrPage(), queryMenuEvaluateRequest.getPageSize());
        if (queryMenuEvaluateRequest.getOrderBy()==null||queryMenuEvaluateRequest.getOrderBy()==""){
            PageHelper.orderBy("gmt_create desc");
        }
        //正常评论
        List<MenuEvaluate> menuEvaluates = menuEvaluateMapper.selectByMenuIdAndUserId(queryMenuEvaluateRequest.getId(), null,null);
        List<QueryMenuEvaluateDetailVO> queryMenuEvaluateDetailVOList=new ArrayList<>();
        if (menuEvaluates!=null&&menuEvaluates.size()>0){
            //菜品
            MenuAe menuAe = menuAeMapper.selectByPrimaryKey(queryMenuEvaluateRequest.getId());
            //管理员评论
            List<Long>  menuEvaluateId = menuEvaluates.stream().map(MenuEvaluate::getId).collect(Collectors.toList());
            List<MenuEvaluate> menuEvaluates1 = menuEvaluateMapper.queryByMenuIdAndBg(menuEvaluateId);
            Map<Long, MenuEvaluate> evaluateMap = menuEvaluates1.stream().collect(Collectors.toMap(MenuEvaluate::getMenuEvaluateId, x -> x));
            //获取评论用户名称
            List<Long> userList = menuEvaluates.stream().map(MenuEvaluate::getUserId).collect(Collectors.toList());
            //查询前台用户
            Map<Long, UserAccount> collect=new HashMap<>();
            if (userList!=null&&userList.size()>0){
                List<UserAccount> userAccounts = userAccountMapper.queryUserById(userList);
                collect = userAccounts.stream().collect(Collectors.toMap(UserAccount::getId, u -> u));
            }

            //后台用户
            Map<Long, AccountUser> accountUserMap=new HashMap<>();
            if (menuEvaluates1!=null&&menuEvaluates1.size()>0){
                List<Long> menuEvaluatesList = menuEvaluates1.stream().map(MenuEvaluate::getUserId).collect(Collectors.toList());
                List<AccountUser> accountUsers = accountUserMapper.queryByIds(menuEvaluatesList);
                if (accountUsers!=null){
                    Map<Long, AccountUser> collect1 = accountUsers.stream().collect(Collectors.toMap(AccountUser::getId, x -> x));
                    accountUserMap=collect1;
                }
            }

            for (MenuEvaluate x:menuEvaluates) {
                //对象封装
                QueryMenuEvaluateDetailVO queryMenuEvaluateDetailVO=new QueryMenuEvaluateDetailVO();
                BeanUtils.copyProperties(x,queryMenuEvaluateDetailVO);
                queryMenuEvaluateDetailVO.setMenuName(menuAe.getMenuName());
                if (collect!=null){
                    UserAccount userAccount1 = collect.get(x.getUserId());
                    if (userAccount1!=null){
                        queryMenuEvaluateDetailVO.setNickName(userAccount1.getNickname());
                    }
                }
                //管理员回复
                MenuEvaluate menuEvaluate = evaluateMap.get(x.getId());
                //回复
                if (menuEvaluate!=null){
                    QueryMenuEvaluateDetailVO returnMenuEvaluateDetailVO=new QueryMenuEvaluateDetailVO();
                    BeanUtils.copyProperties(menuEvaluate,returnMenuEvaluateDetailVO);
                    AccountUser accountUser = accountUserMap.get(menuEvaluate.getUserId());
                    if (accountUser!=null){
                        if (accountUser.getNickname()!=null){
                            returnMenuEvaluateDetailVO.setNickName(accountUser.getNickname());
                        }else {
                            returnMenuEvaluateDetailVO.setNickName(accountUser.getUsername());
                        }
                    }
                    queryMenuEvaluateDetailVO.setReturnMenuEvaluate(returnMenuEvaluateDetailVO);
                }
                queryMenuEvaluateDetailVOList.add(queryMenuEvaluateDetailVO);
            }
        }
        PageInfo info = new PageInfo<>(objects.getResult());
        info.setList(queryMenuEvaluateDetailVOList);
        resultData.setData(info);
        return resultData;
    }

    @Override
    public void addMenuEvaluate(MenuEvaluate menuEvaluate) {
        UserAccount userAccount = UserAccountUtils.getUserAccount();
        if (userAccount==null){
            throw new ServletException(SystemEnum.ACCOUNT_NOT_LOGGED_IN.getCode(),SystemEnum.ACCOUNT_NOT_LOGGED_IN.getMsg());
        }
        menuEvaluate.setUserId(userAccount.getId());
        menuEvaluate.setIsRootReply(0);
        menuEvaluate.setMenuType(1);
        menuEvaluate.setGmtCreate(new Date());
        menuEvaluate.setIsDelete(0);
        menuEvaluate.setMenuId(menuEvaluate.getId());
        menuEvaluateMapper.insertSelective(menuEvaluate);
    }

    @Override
    public ResultData<PageInfo<QueryMyEvaluateVO>> queryMyEvaluate(QueryMenuEvaluateRequest queryMenuEvaluateRequest) {
        UserAccount userAccount = UserAccountUtils.getUserAccount();
        if (userAccount==null){
            throw new ServletException(SystemEnum.ACCOUNT_NOT_LOGGED_IN.getCode(),SystemEnum.ACCOUNT_NOT_LOGGED_IN.getMsg());
        }
        Page<MenuEvaluate> objects = PageHelper.startPage(queryMenuEvaluateRequest.getCurrPage(), queryMenuEvaluateRequest.getPageSize());
        if (queryMenuEvaluateRequest.getOrderBy()==null||queryMenuEvaluateRequest.getOrderBy()==""){
            PageHelper.orderBy("gmt_create desc");
        }
        //评论
        List<MenuEvaluate> menuEvaluates = menuEvaluateMapper.selectMenuAndMenuName(null, userAccount.getId(),queryMenuEvaluateRequest.getMenuName());
        List<QueryMyEvaluateVO> queryMenuEvaluateDetailVOS=new ArrayList<>();
        if (menuEvaluates!=null&&menuEvaluates.size()>0){
            List<Long> collect = menuEvaluates.stream().map(MenuEvaluate::getMenuId).collect(Collectors.toList());
            //菜品
            List<MenuAe> menuAes = menuAeMapper.queryByIds(collect);
            Map<Long, MenuAe> menuAeMap = menuAes.stream().collect(Collectors.toMap(MenuAe::getId, x -> x));
            menuEvaluates.forEach(x->{
                QueryMyEvaluateVO queryMyEvaluateVO=new QueryMyEvaluateVO();
                BeanUtils.copyProperties(x,queryMyEvaluateVO);
                MenuAe ae = menuAeMap.get(x.getMenuId());
                if (ae!=null){
                    queryMyEvaluateVO.setMenuId(ae.getId());
                    queryMyEvaluateVO.setMenuImage(isNull(ae.getMenuImage())?null:ae.getMenuImage());
                    queryMyEvaluateVO.setMenuName(isNull(ae.getMenuName())?null:ae.getMenuName());
                }
                queryMenuEvaluateDetailVOS.add(queryMyEvaluateVO);
            });
        }
        ResultData<PageInfo<QueryMyEvaluateVO>> resultData=new ResultData<>();
        PageInfo info = new PageInfo<>(objects.getResult());
        info.setList(queryMenuEvaluateDetailVOS);
        resultData.setData(info);
        return resultData;
    }
}
