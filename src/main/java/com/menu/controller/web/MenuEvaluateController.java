package com.menu.controller.web;

import com.github.pagehelper.PageInfo;
import com.menu.bean.MenuEvaluate;
import com.menu.service.MenuEvaluateService;
import com.menu.util.ResultData;
import com.menu.vo.QueryMenuEvaluateDetailVO;
import com.menu.vo.QueryMenuEvaluateRequest;
import com.menu.vo.QueryMyEvaluateVO;
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
public class MenuEvaluateController {

    @Autowired
    MenuEvaluateService menuEvaluateService;


    /**
     * 查询相关菜品
     * @param queryMenuEvaluateRequest
     * @return
     */
    @PostMapping(value = "/queryByPageMenuEvaluate")
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
    public ResultData<PageInfo<QueryMyEvaluateVO>>  queryMyEvaluate(@RequestBody QueryMenuEvaluateRequest queryMenuEvaluateRequest){
        ResultData<PageInfo<QueryMyEvaluateVO>> pageInfoResultData = menuEvaluateService.queryMyEvaluate(queryMenuEvaluateRequest);
        return pageInfoResultData;
    }



}
