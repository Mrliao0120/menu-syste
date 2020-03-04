package com.menu.dao;

import com.menu.bean.CityDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface CityDOMapper {
    int deleteByPrimaryKey(String cityCode);

    int insert(CityDO record);

    int insertSelective(CityDO record);

    CityDO selectByPrimaryKey(String cityCode);

    int updateByPrimaryKeySelective(CityDO record);

    int updateByPrimaryKey(CityDO record);


    CityDO  selectByName(@Param("provinceCode") String  provinceCode);

    CityDO  selectByNameAndCode(@Param("cityName") String  cityName,@Param("provinceCode") String  provinceCode);

    List<CityDO> selectAll();
}