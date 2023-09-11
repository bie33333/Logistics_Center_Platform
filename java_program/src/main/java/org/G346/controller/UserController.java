package org.G346.controller;

import org.G346.pojo.User;
import org.G346.service.UserService;
import org.G346.utils.PageResult;
import org.G346.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



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
    public Result userLogin(String userAccount,String userPassword) {
        if(userAccount == null || userAccount.equals(""))
            return Result.error(400, "用户名不能为空!", null);
        if(userPassword == null || userPassword.equals(""))
            return Result.error(400, "密码不能为空!", null);

        User user = userService.selectByPrimaryKey(userAccount);
        if(user==null) {
            return Result.error(400,"未找到用户！");
        }
        else{
            String loginPassword = user.getUserPassword();
            if(loginPassword.equals(userPassword)){
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
     * @param userAccount
     * @return
     */
    @RequestMapping("findUserByAccount")
    public PageResult<User> findUserByAccount(String userAccount){
       return userService.findUserByAccount(userAccount);
    }
    /**
     * 插入用户，成功返回200，失败返回400
     * @param userAccount
     * @param userName
     * @param userPhone
     * @param userPassword
     * @param userAge
     * @param userSex
     * @param userAddress
     * @return
     */
    @RequestMapping("insertUser")
    public Result insertUser(String userAccount,String userName, String userPhone, String userPassword
    ,String userAge, String userSex, String userAddress){
        if(userAccount == null || userAccount.equals(""))
            return Result.error(400, "用户名不能为空!", null);
        if(userPassword == null || userPassword.equals(""))
            return Result.error(400, "密码不能为空!", null);
        if(userPhone == null || userPhone.equals(""))
            return Result.error(400, "电话不能为空!", null);
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(userPassword);
        user.setUserPhone(userPhone);
        user.setUserName(userName);
        user.setUserAddress(userAddress);
        user.setUserSex(userSex);
        if (userAge == null || userAge.equals(""))
            user.setUserAge(null);
        else
            user.setUserAge(Integer.parseInt(userAge));
        if (userService.selectByPrimaryKey(userAccount)!=null) {
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
     * @param userAccount
     * @return
     */
    @RequestMapping("deleteUser")
    public Result deleteUser(String userAccount){
        if(userAccount == null || userAccount.equals(""))
            return Result.error(400, "用户名不能为空!", null);
        if (userService.selectByPrimaryKey(userAccount)==null) {
            return Result.error(400,"删除失败，用户不存在！");
        }else {
            userService.deleteByPrimaryKey(userAccount);
            return Result.ok();
        }
    }

    /**
     * 根据用户名修改用户信息
     * @param userAccount
     * @param userName
     * @param userPhone
     * @param userPassword
     * @param userAge
     * @param userSex
     * @param userAddress
     * @return
     */
    @RequestMapping("updateUser")
    public Result updateUser(String userAccount,String userName, String userPhone, String userPassword
            ,String userAge, String userSex, String userAddress) {
        if(userAccount == null || userAccount.equals(""))
            return Result.error(400, "用户名不能为空!", null);
        User user = userService.selectByPrimaryKey(userAccount);
        if(user==null)
            return Result.error(400, "用户不存在!", null);

        if(userName!=null) user.setUserName(userName);
        if(userAddress!=null)user.setUserAddress(userAddress);
        if(userSex!=null)user.setUserSex(userSex);
        if (userAge != null)user.setUserAge(Integer.parseInt(userAge));
        if(userPassword!=null)user.setUserPassword(userPassword);
        if(userPhone!=null)user.setUserPhone(userPhone);

        userService.updateByPrimaryKey(user);
        return Result.ok();


    }
}
