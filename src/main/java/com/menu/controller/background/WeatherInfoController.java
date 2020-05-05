package com.menu.controller.background;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.menu.bean.MenuEvaluate;
import com.menu.bean.WeatherInfoDO;
import com.menu.dao.WeatherInfoDOMapper;
import com.menu.util.ResultData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

/**
 * @author
 * @Title: com.menu.controller.background.WeatherInfoController
 * @ProjectName menu-syste
 * @Description: for WeatherInfoController  handle
 * @Date create in 16:54 2020/5/3
 */
@RestController
@RequestMapping(value = "/weatherInfo")
public class WeatherInfoController {

    @Autowired
   private WeatherInfoDOMapper weatherInfoDOMapper;

    @Autowired
    private RestTemplate restTemplate;


    @PostMapping(value = "/addWeatherInfo")
    public ResultData  addWeatherInfo(@RequestBody WeatherInfoDO weatherInfoDO){
        weatherInfoDOMapper.insertSelective(weatherInfoDO);
        return new ResultData();
    }

    @PostMapping(value = "/findWeatherInfo")
    public ResultData<WeatherInfoDO>  findWeatherInfo(@RequestBody WeatherInfoDO weatherInfoDO){
        ResultData<WeatherInfoDO> resultData = new ResultData();
        WeatherInfoDO weatherInfoDO1 = weatherInfoDOMapper.selectByPrimaryKey(weatherInfoDO.getId());
        resultData.setData(weatherInfoDO1);
        return resultData;
    }


    @PostMapping(value = "/updateWeatherInfo")
    public ResultData  updateWeatherInfo(@RequestBody WeatherInfoDO weatherInfoDO){
        weatherInfoDOMapper.updateByPrimaryKeySelective(weatherInfoDO);
        return new ResultData();
    }



    @PostMapping(value = "/findWeatherInfoDO")
    public ResultData<PageInfo<WeatherInfoDO>>  findWeatherInfoDO(@RequestBody WeatherInfoDO weatherInfoDO){
        ResultData<PageInfo<WeatherInfoDO>> resultData=new ResultData<>();
        Page<WeatherInfoDO> objects = PageHelper.startPage(weatherInfoDO.getCurrPage(), weatherInfoDO.getPageSize());
        List<WeatherInfoDO> weatherInfoDOS = weatherInfoDOMapper.fingPage(weatherInfoDO);
        PageInfo info = new PageInfo<>(objects.getResult());
        info.setList(weatherInfoDOS);
        resultData.setData(info);
        return resultData;
    }


    /**
     * 爬去数据
     * @param weatherInfoDO
     * @return
     */
    @PostMapping(value = "/insertWeatherInfoDO")
    public ResultData<WeatherInfoDO>  insertWeatherInfoDO(@RequestBody WeatherInfoDO weatherInfoDO){
        ResultData<WeatherInfoDO> resultData = new ResultData();
        String  url="https://free-api.heweather.net/s6/weather/now?location="+weatherInfoDO.getLocation()+"&key=1b00e7840a2e43f49af0a534f3adb54c";
        String forObject = restTemplate.getForObject(url, String.class, new Object());
        //JSON格式
        if (forObject!=null){
            JSONObject jsonObject = JSON.parseObject(forObject);
            JSONArray heWeather6 = JSON.parseArray(jsonObject.getString("HeWeather6"));
            JSONObject jsonObject1 = heWeather6.getJSONObject(0);
            String status = jsonObject1.getString("status");
            if (status.equals("ok")){
                WeatherInfoDO now = jsonObject1.getObject("now", weatherInfoDO.getClass());
                //JSONObject update = jsonObject1.getJSONObject("update");
                WeatherInfoDO basic = jsonObject1.getObject("basic", weatherInfoDO.getClass());
                //dun
                BeanUtils.copyProperties(basic,weatherInfoDO);
                weatherInfoDO.setFl(now.getFl());
                weatherInfoDO.setWindDeg(now.getWindDeg());
                weatherInfoDO.setWindDir(now.getWindDir());
                weatherInfoDO.setTmp(now.getTmp());
                weatherInfoDO.setCondTxt(now.getCondTxt());
                //
                weatherInfoDO.setGmtCreate(new Date());
                WeatherInfoDO weatherInfoDO1 = weatherInfoDOMapper.selectLimit1(weatherInfoDO);
                if (weatherInfoDO1!=null){
                    resultData.setData(weatherInfoDO1);
                    return resultData;
                }
                weatherInfoDO.setGmtCreate(new Date());
                weatherInfoDOMapper.insertSelective(weatherInfoDO);
                resultData.setData(weatherInfoDO);
            }
        }
        return resultData;
    }



}
