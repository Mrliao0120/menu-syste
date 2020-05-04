package com.menu.controller.web;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.menu.bean.NewsInfoDO;
import com.menu.dao.NewsInfoDOMapper;
import com.menu.util.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lhb
 * @Title: com.menu.controller.background.NewsInfoController
 * @ProjectName menu-syste
 * @Description: for NewsInfoController  handle
 * @Date create in 17:17 2020/5/3
 */
@RestController
@RequestMapping(value = "/web/news/info")
public class WebNewsInfoController {


    @Autowired
    NewsInfoDOMapper newsInfoDOMapper;


    @GetMapping(value = "/selectByPrimaryKey")
    public ResultData<NewsInfoDO>   selectByPrimaryKey(@RequestParam(value = "id",required = true) Integer id){
        ResultData<NewsInfoDO> resultData = new ResultData();
        NewsInfoDO newsInfoDO = newsInfoDOMapper.selectByPrimaryKey(id);
        resultData.setData(newsInfoDO);
        return resultData;
    }


    @PostMapping(value = "/findNewsInfoList")
    public ResultData<List<NewsInfoDO>>  findNewsInfo(@RequestBody NewsInfoDO weatherInfoDO){
        ResultData<List<NewsInfoDO>> resultData=new ResultData<>();
        List<NewsInfoDO> weatherInfoDOS = newsInfoDOMapper.findPageLimit8(weatherInfoDO);
        resultData.setData(weatherInfoDOS);
        return resultData;
    }






}
