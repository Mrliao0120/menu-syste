package com.menu.controller.web;

import com.github.pagehelper.PageInfo;
import com.menu.bean.MenuAe;
import com.menu.service.MenuAeService;
import com.menu.util.ResultData;
import com.menu.vo.QueryIndexMenuAeVO;
import com.menu.vo.QueryMenuAeRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author
 * @Title:
 * @ProjectName menu-system
 * @Description: TODO
 * @Date create in 19:10 2019/10/28
 */
@RequestMapping(value = "/menu")
@RestController
@Api(value = "菜品相关API",description = "菜品相关API")
public class MenuAeController {


    @Autowired
    MenuAeService menuAeService;


    /**
     * 分页查询菜品
     * @param queryMenuAeRequest
     * @return
     */
    @PostMapping(value = "/queryByPageCondition")
    @ApiOperation(value = "分页查询菜品")
    public ResultData<PageInfo<MenuAe>>   queryByPageCondition(@RequestBody QueryMenuAeRequest queryMenuAeRequest, HttpServletRequest httpServletRequest){
        ResultData<PageInfo<MenuAe>> resultData = menuAeService.queryByCondition(queryMenuAeRequest,httpServletRequest);
        return  resultData;
    }


    /**
     * 菜品详情
     */
    @PostMapping(value = "/queryByAndIndexId")
    @ApiOperation(value = "菜品详情")
    @ApiImplicitParam(name = "id",value = "菜品ID",required = true,dataType ="Long",paramType ="query")
    public ResultData<QueryIndexMenuAeVO>   queryByAndIndexId(@RequestParam(value = "id",required = true)Long id){
        ResultData<QueryIndexMenuAeVO> resultData=new ResultData<>();
        QueryIndexMenuAeVO queryIndexMenuAeVO = menuAeService.queryByAndIndexId(id);
        resultData.setData(queryIndexMenuAeVO);
        return  resultData;
    }




    /**
     * 图片
     */
    @PostMapping(value = "/findMenuAeLimit5")
    public ResultData<List<MenuAe>>   findMenuAeLimit5(){
        ResultData<List<MenuAe>> resultData=new ResultData<>();
        List<MenuAe> menuAeLimit5 = menuAeService.findMenuAeLimit5();
        resultData.setData(menuAeLimit5);
        return  resultData;
    }

}
