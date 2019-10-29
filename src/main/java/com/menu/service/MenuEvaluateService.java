package com.menu.service;

import com.github.pagehelper.PageInfo;
import com.menu.util.ResultData;
import com.menu.vo.QueryMenuEvaluateRequest;
import com.menu.vo.QueryMenuEvaluateVO;

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


}
