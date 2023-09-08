package org.G346.mapper;

import java.util.List;
import org.G346.pojo.User;

public interface UserMapper {
    int insert(User row);

    List<User> selectAll();
}