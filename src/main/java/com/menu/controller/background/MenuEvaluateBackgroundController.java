package com.menu.controller.background;

import com.github.pagehelper.PageInfo;
import com.menu.service.MenuEvaluateService;
import com.menu.util.ResultData;
import com.menu.vo.QueryMenuEvaluateRequest;
import com.menu.vo.QueryMenuEvaluateVO;
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
 * @Date create in 20:33 2019/10/29
 */
@RestController
@RequestMapping(value = "/menuEvaluateBackground")
public class MenuEvaluateBackgroundController {

    @Autowired
    MenuEvaluateService menuEvaluateService;




    @PostMapping(value = "/queryPageByCondition")
    public  ResultData<PageInfo<QueryMenuEvaluateVO>>   queryPageByCondition(@RequestBody QueryMenuEvaluateRequest  queryMenuEvaluateRequest){
        ResultData<PageInfo<QueryMenuEvaluateVO>> resultData = menuEvaluateService.queryPageByCondition(queryMenuEvaluateRequest);
        return resultData;
    }

}
