package com.menu.dao;

import com.menu.bean.MenuAe;
import com.menu.vo.QueryMenuAeRequest;

import java.util.List;

public interface MenuAeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MenuAe record);

    int insertSelective(MenuAe record);

    MenuAe selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MenuAe record);

    int updateByPrimaryKey(MenuAe record);


    List<MenuAe>  queryByPageAndCondition(QueryMenuAeRequest queryMenuAeRequest);
}