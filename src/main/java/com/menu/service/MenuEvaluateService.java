package com.menu.service;

import com.github.pagehelper.PageInfo;
import com.menu.bean.MenuEvaluate;
import com.menu.util.ResultData;
import com.menu.vo.QueryMenuEvaluateDetailVO;
import com.menu.vo.QueryMenuEvaluateRequest;
import com.menu.vo.QueryMenuEvaluateVO;
import com.menu.vo.QueryMyEvaluateVO;

import javax.xml.transform.Result;

/**
 * @author lhb
 * @Title:
 * @ProjectName menu-syste
 * @Description: TODO
 * @Date create in 20:34 2019/10/29
 */
public interface MenuEvaluateService {



    //分页查询
    ResultData<PageInfo<QueryMenuEvaluateVO>>  queryPageByCondition(QueryMenuEvaluateRequest queryMenuEvaluateRequest);

    //查看回复详情
    ResultData<QueryMenuEvaluateDetailVO>   queryMenuEvaluateDetail(Long  id);



    void  addReturnMenuEvaluate(String textEvaluate,Long id);


    void   updateMenuEvaluate(MenuEvaluate evaluate);


    ResultData<PageInfo<QueryMenuEvaluateDetailVO>> queryByPageMenuEvaluate(QueryMenuEvaluateRequest  queryMenuEvaluateRequest);

    void  addMenuEvaluate(MenuEvaluate menuEvaluate);


    ResultData<PageInfo<QueryMyEvaluateVO>>   queryMyEvaluate(QueryMenuEvaluateRequest queryMenuEvaluateRequest);
}
