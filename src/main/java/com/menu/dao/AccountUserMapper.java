package com.menu.dao;

import com.menu.bean.AccountUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface AccountUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AccountUser record);

    int insertSelective(AccountUser record);

    AccountUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AccountUser record);

    int updateByPrimaryKey(AccountUser record);


    AccountUser selectByUserName(@Param("userName") String  userName);
}