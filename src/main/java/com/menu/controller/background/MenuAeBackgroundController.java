package com.menu.controller.background;

import com.github.pagehelper.PageInfo;
import com.menu.bean.MenuAe;
import com.menu.service.MenuAeService;
import com.menu.util.ResultData;
import com.menu.vo.QueryMenuAeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author  菜品
 * @Title:
 * @ProjectName menu-syste
 * @Description: TODO
 * @Date create in 20:23 2019/10/28
 */
@RestController
@RequestMapping(value = "/menuAeBackground")
public class MenuAeBackgroundController {

    @Autowired
    MenuAeService menuAeService;

    /**
     * 查询分页菜品
     * @param queryMenuAeRequest
     * @return
     */
    @PostMapping(value = "/queryMenuAe")
    public ResultData<PageInfo<MenuAe>>  queryMenuAe(@RequestBody QueryMenuAeRequest queryMenuAeRequest){
        ResultData<PageInfo<MenuAe>> resultData = menuAeService.queryByBackGroundCondition(queryMenuAeRequest);
        return resultData;
    }

    /**
     * 查询菜品
     * @param
     * @return
     */
    @PostMapping(value = "/queryById")
    public ResultData<MenuAe>  queryById(@RequestParam(value = "id",name = "id",required = true)Long id){
        ResultData<MenuAe> resultData=new ResultData<>();
        MenuAe menuAe = menuAeService.queryById(id);
        resultData.setData(menuAe);
        return resultData;
    }

    /**
     * 更新
     * @param
     * @return
     */
    @PostMapping(value = "/updateMenuAe")
    public ResultData  updateMenuAe(@RequestBody MenuAe menuAe){
        menuAeService.updateMenuAe(menuAe);
        return new ResultData();
    }

    /**
     * 增加菜品
     * @param
     * @return
     */
    @PostMapping(value = "/insertMenuAe")
    public ResultData  insertMenuAe(@RequestBody MenuAe menuAe){
       ResultData<MenuAe> resultData=new ResultData();
        MenuAe menuAe1 = menuAeService.insertMenuAe(menuAe);
        resultData.setData(menuAe1);
        return resultData;
    }




}
