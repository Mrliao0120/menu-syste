package com.menu.controller.web;

import com.github.pagehelper.PageInfo;
import com.menu.bean.MenuAe;
import com.menu.service.MenuAeService;
import com.menu.util.ResultData;
import com.menu.vo.QueryMenuAeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lhb
 * @Title:
 * @ProjectName menu-syste
 * @Description: TODO
 * @Date create in 19:10 2019/10/28
 */
@RequestMapping(value = "/menu")
@RestController
public class MenuAeController {


    @Autowired
    MenuAeService menuAeService;


    /**
     * 分页查询菜品
     * @param queryMenuAeRequest
     * @return
     */
    @PostMapping(value = "/queryByPageCondition")
    public ResultData<PageInfo<MenuAe>>   queryByPageCondition(@RequestBody QueryMenuAeRequest queryMenuAeRequest, HttpServletRequest httpServletRequest){
        ResultData<PageInfo<MenuAe>> resultData = menuAeService.queryByCondition(queryMenuAeRequest,httpServletRequest);
        return  resultData;
    }




}
