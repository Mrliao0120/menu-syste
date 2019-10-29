package com.menu.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.menu.bean.AccountUser;
import com.menu.bean.MenuAe;
import com.menu.bean.MenuEvaluate;
import com.menu.dao.MenuAeMapper;
import com.menu.dao.MenuEvaluateMapper;
import com.menu.enums.SystemEnum;
import com.menu.exeception.ServletException;
import com.menu.service.MenuAeService;
import com.menu.service.MenuEvaluateService;
import com.menu.util.AccountUserUtils;
import com.menu.util.ResultData;
import com.menu.vo.QueryMenuEvaluateRequest;
import com.menu.vo.QueryMenuEvaluateVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
}
