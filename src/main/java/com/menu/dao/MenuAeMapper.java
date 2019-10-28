package com.menu.dao;

import com.menu.bean.MenuAe;

public interface MenuAeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MenuAe record);

    int insertSelective(MenuAe record);

    MenuAe selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MenuAe record);

    int updateByPrimaryKey(MenuAe record);
}