package com.menu.dao;

import com.menu.bean.MenuEvaluate;

public interface MenuEvaluateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MenuEvaluate record);

    int insertSelective(MenuEvaluate record);

    MenuEvaluate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MenuEvaluate record);

    int updateByPrimaryKey(MenuEvaluate record);
}