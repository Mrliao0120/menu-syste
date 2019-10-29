package com.menu.controller.background;

import com.github.pagehelper.PageInfo;
import com.menu.service.MenuEvaluateService;
import com.menu.util.ResultData;
import com.menu.vo.QueryMenuEvaluateDetailVO;
import com.menu.vo.QueryMenuEvaluateRequest;
import com.menu.vo.QueryMenuEvaluateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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



    /**
     * 分页查询回复
     * @param queryMenuEvaluateRequest
     * @return
     */
    @PostMapping(value = "/queryPageByCondition")
    public  ResultData<PageInfo<QueryMenuEvaluateVO>>   queryPageByCondition(@RequestBody QueryMenuEvaluateRequest  queryMenuEvaluateRequest){
        ResultData<PageInfo<QueryMenuEvaluateVO>> resultData = menuEvaluateService.queryPageByCondition(queryMenuEvaluateRequest);
        return resultData;
    }



    /**
     * 查看评论详情
     * @param id
     * @return
     */
    @PostMapping(value = "/queryMenuEvaluateDetail")
    public  ResultData<QueryMenuEvaluateDetailVO>   queryMenuEvaluateDetail(@RequestParam(value = "id",required = true)Long id){
        ResultData<QueryMenuEvaluateDetailVO> resultData = menuEvaluateService.queryMenuEvaluateDetail(id);
        return resultData;
    }

    /**
     * 查看评论详情
     * @param id
     * @return
     */
    @PostMapping(value = "/addReturnMenuEvaluate")
    public  ResultData   addReturnMenuEvaluate(@RequestParam(value = "textEvaluate",required = true)String textEvaluate,
                                                                            @RequestParam(value = "id",required = true)Long id){
        menuEvaluateService.addReturnMenuEvaluate(textEvaluate,id);
        return new ResultData();
    }

}
