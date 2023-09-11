package org.G346.mapper;

import java.util.List;
import org.G346.pojo.Admin;

public interface AdminMapper {
    int deleteByPrimaryKey(String account);

    int insert(Admin row);

    Admin selectByPrimaryKey(String account);

    List<Admin> selectAll();

    List<Admin> findAdminByAccount(String account);

    int updateByPrimaryKey(Admin row);
}