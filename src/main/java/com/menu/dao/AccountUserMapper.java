package com.menu.dao;

import com.menu.bean.AccountUser;
import com.menu.vo.QueryAccountUserRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AccountUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AccountUser record);

    int insertSelective(AccountUser record);

    AccountUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AccountUser record);

    int updateByPrimaryKey(AccountUser record);


    AccountUser selectByUserName(@Param("userName") String  userName);

    List<AccountUser>  queryByPageAndCondition(QueryAccountUserRequest queryAccountUserRequest);
}