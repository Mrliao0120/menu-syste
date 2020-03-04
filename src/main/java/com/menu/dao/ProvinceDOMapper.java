package com.menu.dao;

import com.menu.bean.ProvinceDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface ProvinceDOMapper {
    int deleteByPrimaryKey(String id);

    int insert(ProvinceDO record);

    int insertSelective(ProvinceDO record);

    ProvinceDO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ProvinceDO record);

    int updateByPrimaryKey(ProvinceDO record);

    ProvinceDO  selectByName(@Param("province") String province);


    List<ProvinceDO> selectAll();
}