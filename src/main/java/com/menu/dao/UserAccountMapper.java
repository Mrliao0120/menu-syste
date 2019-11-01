package com.menu.dao;


import com.menu.bean.UserAccount;
import com.menu.vo.QueryAccountUserRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserAccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserAccount record);

    int insertSelective(UserAccount record);

    UserAccount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserAccount record);

    int updateByPrimaryKey(UserAccount record);

    UserAccount selectByUserName(@Param("username") String  username);


    List<UserAccount>   bgQueryUserAccountPage(QueryAccountUserRequest queryAccountUserRequest);


    List<UserAccount>  queryUserById(@Param("asList") List<Long> asList);
}