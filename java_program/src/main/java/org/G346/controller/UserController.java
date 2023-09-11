package org.G346.controller;

import org.G346.mapper.UserMapper;
import org.G346.pojo.User;
import org.G346.service.UserService;
import org.G346.utils.PageResult;
import org.G346.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;
    /**
     * 判断用户是否登录成功
     * @return Result
     */
    @RequestMapping("userLogin")
    public Result userLogin(String account,String password) {
        if(account == null || account.equals(""))
            return Result.error(400, "用户名不能为空!", null);
        if(password == null || password.equals(""))
            return Result.error(400, "密码不能为空!", null);

        User user = userService.selectByPrimaryKey(account);
        if(user==null) {
            return Result.error(400,"未找到用户！");
        }
        else{
            String loginPassword = user.getUserPassword();
            if(loginPassword.equals(password)){
//                System.out.println("登陆成功！");
                return Result.ok();
            }
            else{
                return Result.error(400,"密码错误!");
            }

        }
    }

    /**
     * 分页查找所有用户
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @RequestMapping("selectUser")
    public PageResult<User> selectUser(String pageNumber,String pageSize) {
        return userService.selectUser(Integer.parseInt(pageNumber),Integer.parseInt(pageSize));
    }
    /**
     * 查找所有用户
     * @return
     */
    @RequestMapping("selectAllUser")
    public PageResult<User> selectAllUser(){
        return userService.selectAll();
    }

    /**
     * 根据用户名查找单个用户
     * @param account
     * @return
     */
    @RequestMapping("findUserByAccount")
    public PageResult<User> findUserByAccount(String account){
       return userService.findUserByAccount(account);
    }
    /**
     * 插入用户，成功返回200，失败返回400
     * @param account
     * @param name
     * @param phone
     * @param password
     * @param age
     * @param sex
     * @param address
     * @return
     */
    @RequestMapping("insertUser")
    public Result insertUser(String account,String name, String phone, String password
    ,String age, String sex, String address){
        if(account == null || account.equals(""))
            return Result.error(400, "用户名不能为空!", null);
        if(password == null || password.equals(""))
            return Result.error(400, "密码不能为空!", null);
        if(phone == null || phone.equals(""))
            return Result.error(400, "电话不能为空!", null);
        User user = new User();
        user.setUserAccount(account);
        user.setUserPassword(password);
        user.setUserPhone(phone);
        user.setUserName(name);
        user.setUserAddress(address);
        user.setUserSex(sex);
        if (age == null || age.equals(""))
            user.setUserAge(null);
        else
            user.setUserAge(Integer.parseInt(age));
        if (userService.selectByPrimaryKey(account)!=null) {
            return Result.error(400,"插入用户失败,用户已存在！");
        } else {
            int row = userService.insertUser(user);
            if (row > 0) {
                return Result.ok();
            } else {
                return Result.error(400, "插入用户失败！", null);
            }
        }
    }

    /**
     * 根据用户名删除用户
     * @param account
     * @return
     */
    @RequestMapping("deleteUser")
    public Result deleteUser(String account){
        if(account == null || account.equals(""))
            return Result.error(400, "用户名不能为空!", null);
        if (userService.selectByPrimaryKey(account)==null) {
            return Result.error(400,"删除失败，用户不存在！");
        }else {
            userService.deleteByPrimaryKey(account);
            return Result.ok();
        }
    }

    /**
     * 根据用户名修改用户信息
     * @param account
     * @param name
     * @param phone
     * @param password
     * @param age
     * @param sex
     * @param address
     * @return
     */
    @RequestMapping("updateUser")
    public Result updateUser(String account,String name, String phone, String password
            ,String age, String sex, String address) {
        if(account == null || account.equals(""))
            return Result.error(400, "用户名不能为空!", null);
        User user = userService.selectByPrimaryKey(account);
        if(user==null)
            return Result.error(400, "用户不存在!", null);

        if(name!=null) user.setUserName(name);
        if(address!=null)user.setUserAddress(address);
        if(sex!=null)user.setUserSex(sex);
        if (age != null)user.setUserAge(Integer.parseInt(age));
        if(password!=null)user.setUserPassword(password);
        if(phone!=null)user.setUserPhone(phone);

        userService.updateByPrimaryKey(user);
        return Result.ok();


    }
}
