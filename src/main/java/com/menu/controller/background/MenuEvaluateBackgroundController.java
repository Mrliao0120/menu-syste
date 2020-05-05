package com.menu.controller.background;

import com.github.pagehelper.PageInfo;
import com.menu.bean.AccountUser;
import com.menu.bean.MenuEvaluate;
import com.menu.enums.SystemEnum;
import com.menu.exeception.ServletException;
import com.menu.service.MenuEvaluateService;
import com.menu.util.AccountUserUtils;
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
     * 分页查询回复
     * @param queryMenuEvaluateRequest
     * @return
     */
    @PostMapping(value = "/queryPage")
    public  ResultData<PageInfo<MenuEvaluate>>   queryPage(@RequestBody QueryMenuEvaluateRequest  queryMenuEvaluateRequest){
        ResultData<PageInfo<MenuEvaluate>> resultData = menuEvaluateService.queryPage(queryMenuEvaluateRequest);
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
     * 新增回复
     * @param id
     * @return
     */
    @PostMapping(value = "/addReturnMenuEvaluate")
    public  ResultData   addReturnMenuEvaluate(@RequestParam(value = "textEvaluate",required = true)String textEvaluate,
                                                                            @RequestParam(value = "id",required = true)Long id){
        menuEvaluateService.addReturnMenuEvaluate(textEvaluate,id);
        return new ResultData();
    }

    /**
     * 更新
     * @param
     * @return
     */
    @PostMapping(value = "/updateMenuEvaluate")
    public  ResultData<PageInfo<QueryMenuEvaluateVO>>   updateMenuEvaluate(@RequestBody MenuEvaluate menuEvaluate){
        AccountUser accountUser1 = AccountUserUtils.getUserAccount();
        if (accountUser1==null){
            throw new ServletException(SystemEnum.THE_PARAMETER_IS_INCORRECT.getCode(),
                    SystemEnum.THE_PARAMETER_IS_INCORRECT.getMsg());
        }
        if (accountUser1.getSystemLevel()!=1){
            throw new ServletException(7777,
                    "您无权限删除");
        }
         menuEvaluateService.updateMenuEvaluate(menuEvaluate);
        return new ResultData<>();
    }

}
