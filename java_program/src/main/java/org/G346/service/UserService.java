package org.G346.service;

import org.G346.mapper.UserMapper;
import org.G346.pojo.User;
import org.G346.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;


    /**
     * 分页查找用户
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public PageResult<User> selectUser(int pageNumber,int pageSize){
        PageResult.startPage(pageNumber,pageSize);
        List<User> users = userMapper.selectAll();
        return PageResult.build(users);
    }

    public PageResult<User> lookupUser(int pageNumber, int pageSize, String name){
        PageResult.startPage(pageNumber,pageSize);
        List<User> users = userMapper.lookupUser(name);
        return PageResult.build(users);
    }
    /**
     * 查找所有用户
     * @return
     */
    public PageResult<User> selectAll(){
        List<User> users = userMapper.selectAll();
        return PageResult.build(users);
    }

    /**
     * 插入用户
     * @param user
     * @return
     */
    public int insertUser(User user){
        return userMapper.insert(user);

    }


    public User selectByPrimaryKey(String userAccount){

        return userMapper.selectByPrimaryKey(userAccount);
    }


    public PageResult<User> findUserByAccount(String userAccount){
        List<User> users = userMapper.findUserByAccount(userAccount);
        return PageResult.build(users);
    }
    public int deleteByPrimaryKey(String userAccount){
        return userMapper.deleteByPrimaryKey(userAccount);
    }

    /**
     * 更新
     * @param row
     * @return
     */
    public int updateByPrimaryKey(User row){
        return userMapper.updateByPrimaryKey(row);
    }
}
