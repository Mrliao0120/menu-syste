package com.menu.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.menu.bean.MenuAe;
import com.menu.service.MenuAeService;
import com.menu.util.ResultData;
import com.menu.vo.QueryMenuAeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.menu.dao.MenuAeMapper;

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
}
