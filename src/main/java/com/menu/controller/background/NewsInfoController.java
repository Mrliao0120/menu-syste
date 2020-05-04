package com.menu.controller.background;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.menu.bean.NewsInfoDO;
import com.menu.dao.NewsInfoDOMapper;
import com.menu.util.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
@RequestMapping(value = "/news/info")
public class NewsInfoController {


    @Autowired
    NewsInfoDOMapper newsInfoDOMapper;


    @PostMapping(value = "/selectByPrimaryKey")
    public ResultData<NewsInfoDO>   selectByPrimaryKey(@RequestParam(value = "id",required = true) Integer id){
        ResultData<NewsInfoDO> resultData = new ResultData();
        NewsInfoDO newsInfoDO = newsInfoDOMapper.selectByPrimaryKey(id);
        resultData.setData(newsInfoDO);
        return resultData;
    }

    @PostMapping(value = "/addNewsInfo")
    public ResultData   addNewsInfo(@RequestBody NewsInfoDO newsInfoDO){
        newsInfoDOMapper.insertSelective(newsInfoDO);
        return new ResultData();
    }

    @PostMapping(value = "/delete")
    public ResultData   delete(@RequestParam(value = "id",required = true) Integer id){
        newsInfoDOMapper.deleteByPrimaryKey(id);
        return new ResultData();
    }

    @PostMapping(value = "/updateNewsInfo")
    public ResultData  updateNewsInfo(@RequestBody NewsInfoDO newsInfoDO){
        //
        /*String contentInfo = newsInfoDO.getContentInfo();
        if (!StringUtils.isEmpty(contentInfo)){
            String replace = contentInfo.replace("//localhost:3535/util/queryLocalHostImage?pathName=", "");
            newsInfoDO.setContentInfo(replace);
        }*/
        newsInfoDOMapper.updateByPrimaryKeySelective(newsInfoDO);
        return new ResultData();
    }

    @PostMapping(value = "/findNewsInfo")
    public ResultData<PageInfo<NewsInfoDO>>  findNewsInfo(@RequestBody NewsInfoDO weatherInfoDO){
        ResultData<PageInfo<NewsInfoDO>> resultData=new ResultData<>();
        Page<NewsInfoDO> objects = PageHelper.startPage(weatherInfoDO.getCurrPage(), weatherInfoDO.getPageSize());
        List<NewsInfoDO> weatherInfoDOS = newsInfoDOMapper.findPage(weatherInfoDO);
        PageInfo info = new PageInfo<>(objects.getResult());
        info.setList(weatherInfoDOS);
        resultData.setData(info);
        return resultData;
    }






}
