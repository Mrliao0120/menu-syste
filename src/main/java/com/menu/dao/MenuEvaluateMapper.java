package com.menu.dao;

import com.menu.bean.MenuEvaluate;
import com.menu.vo.QueryMenuEvaluateRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MenuEvaluateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MenuEvaluate record);

    int insertSelective(MenuEvaluate record);

    MenuEvaluate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MenuEvaluate record);

    int updateByPrimaryKey(MenuEvaluate record);


    List<MenuEvaluate>  queryPageByCondition(QueryMenuEvaluateRequest queryMenuEvaluateRequest);

    MenuEvaluate selectByParentKey(Long id);


    MenuEvaluate selectByMenuIdAndUserId(@Param("menuId") Long menuId,@Param("userId") Long userId);
}