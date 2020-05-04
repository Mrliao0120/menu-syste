package com.menu.dao;

import com.menu.bean.WeatherInfoDO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface WeatherInfoDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WeatherInfoDO record);

    int insertSelective(WeatherInfoDO record);

    WeatherInfoDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WeatherInfoDO record);

    int updateByPrimaryKey(WeatherInfoDO record);



    List<WeatherInfoDO>  fingPage(WeatherInfoDO weatherInfoDO);


    WeatherInfoDO selectLimit1(WeatherInfoDO weatherInfoDO);
}