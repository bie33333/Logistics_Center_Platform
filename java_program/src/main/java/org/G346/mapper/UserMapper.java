package org.G346.mapper;

import java.util.List;
import org.G346.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(String userAccount);

    int insert(User row);

    User selectByPrimaryKey(String userAccount);
    List<User> findUserByAccount(String userAccount);
    List<User> selectAll();

    int updateByPrimaryKey(User row);
}