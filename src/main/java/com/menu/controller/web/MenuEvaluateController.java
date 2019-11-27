package com.menu.controller.web;

import com.github.pagehelper.PageInfo;
import com.menu.bean.MenuEvaluate;
import com.menu.enums.SystemEnum;
import com.menu.exeception.ServletException;
import com.menu.service.MenuEvaluateService;
import com.menu.util.ResultData;
import com.menu.vo.QueryMenuEvaluateDetailVO;
import com.menu.vo.QueryMenuEvaluateRequest;
import com.menu.vo.QueryMyEvaluateVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lhb
 * @Title:
 * @ProjectName menu-syste
 * @Description: TODO
 * @Date create in 16:44 2019/11/2
 */
@RequestMapping(value = "/menuEvaluate")
@RestController
@Api(value = "菜品评价相关API")
public class MenuEvaluateController {

    @Autowired
    MenuEvaluateService menuEvaluateService;


    /**
     * 查询相关菜品
     * @param queryMenuEvaluateRequest
     * @return
     */
    @PostMapping(value = "/queryByPageMenuEvaluate")
    @ApiOperation(value = "查询相关菜品")
    public ResultData<PageInfo<QueryMenuEvaluateDetailVO>>  queryByPageMenuEvaluate(@RequestBody QueryMenuEvaluateRequest queryMenuEvaluateRequest){
        ResultData<PageInfo<QueryMenuEvaluateDetailVO>> pageInfoResultData = menuEvaluateService.
                queryByPageMenuEvaluate(queryMenuEvaluateRequest);
        return pageInfoResultData;
    }




    /**
     * 查询相关菜品
     * @param menuEvaluate
     * @return
     */
    @PostMapping(value = "/addMenuEvaluate")
    @ApiOperation(value = "新增菜品评价")
    public ResultData  addMenuEvaluate(@RequestBody MenuEvaluate menuEvaluate){
         menuEvaluateService.addMenuEvaluate(menuEvaluate);
        return new ResultData();
    }



    /**
     * 查询我的评价
     * @param queryMenuEvaluateRequest
     * @return
     */
    @PostMapping(value = "/queryMyEvaluate")
    @ApiOperation(value = "查询我的评价")
    public ResultData<PageInfo<QueryMyEvaluateVO>>  queryMyEvaluate(@RequestBody QueryMenuEvaluateRequest queryMenuEvaluateRequest){
        ResultData<PageInfo<QueryMyEvaluateVO>> pageInfoResultData = menuEvaluateService.queryMyEvaluate(queryMenuEvaluateRequest);
        return pageInfoResultData;
    }


    /**
     * 删除我的评价
     * @param queryMenuEvaluateRequest
     * @return
     */
    @PostMapping(value = "/deleteMyEvaluate")
    @ApiOperation(value = "删除我的评价")
    public ResultData<PageInfo<QueryMyEvaluateVO>>  deleteMyEvaluate(@RequestBody QueryMenuEvaluateRequest queryMenuEvaluateRequest){
        if (queryMenuEvaluateRequest==null||queryMenuEvaluateRequest.getId()==null){
            throw new ServletException(SystemEnum.THE_PARAMETER_IS_INCORRECT.getCode(),SystemEnum.THE_PARAMETER_IS_INCORRECT.getMsg());
        }
        menuEvaluateService.deleteEvaluate(queryMenuEvaluateRequest);
        return new ResultData<>();
    }



}
