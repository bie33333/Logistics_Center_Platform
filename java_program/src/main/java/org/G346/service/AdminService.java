package org.G346.service;

import org.G346.mapper.AdminMapper;
import org.G346.pojo.Admin;
import org.G346.pojo.User;
import org.G346.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;

    /**
     * 分页查找管理员
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public PageResult<Admin> selectAdmin(int pageNumber, int pageSize){
        PageResult.startPage(pageNumber,pageSize);
        List<Admin> admins = adminMapper.selectAll();
        return PageResult.build(admins);
    }
    /**
     * 查找所有管理员
     * @return
     */
    public PageResult<Admin> selectAll(){
        List<Admin> admins = adminMapper.selectAll();
        return PageResult.build(admins);
    }
    public int insertAdmin(Admin admin){
        return adminMapper.insert(admin);

    }
    public Admin selectByPrimaryKey(String account){
        return adminMapper.selectByPrimaryKey(account);
    }

    public PageResult<Admin> findAdminByAccount(String account){
        List<Admin> admins = adminMapper.findAdminByAccount(account);
        return PageResult.build(admins);
    }

    public int deleteByPrimaryKey(String account){
        return adminMapper.deleteByPrimaryKey(account);
    }

    public int updateByPrimaryKey(Admin admin){
        return adminMapper.updateByPrimaryKey(admin);
    }
}
