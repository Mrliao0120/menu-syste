package com.menu.service;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.menu.bean.MenuAe;
import com.menu.util.ResultData;
import com.menu.vo.QueryIndexMenuAeVO;
import com.menu.vo.QueryMenuAeRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author
 * @Title:
 * @ProjectName menu-syste
 * @Description: TODO
 * @Date create in 19:10 2019/10/28
 */
public interface MenuAeService {



        ResultData<PageInfo<MenuAe>>    queryByCondition(QueryMenuAeRequest queryMenuAeRequest, HttpServletRequest httpServletRequest);




        ResultData<PageInfo<MenuAe>>  queryByBackGroundCondition(QueryMenuAeRequest queryMenuAeRequest);

        void  updateMenuAe(MenuAe menuAe);

        MenuAe insertMenuAe(MenuAe menuAe);


        MenuAe queryById(Long id);


        QueryIndexMenuAeVO queryByAndIndexId(Long id);
}
