package com.menu.dao;

import com.menu.bean.NewsInfoDO;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface NewsInfoDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NewsInfoDO record);

    int insertSelective(NewsInfoDO record);

    NewsInfoDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NewsInfoDO record);

    int updateByPrimaryKey(NewsInfoDO record);


    List<NewsInfoDO>   findPage(NewsInfoDO newsInfoDO);


    List<NewsInfoDO>   findPageLimit8(NewsInfoDO newsInfoDO);
}