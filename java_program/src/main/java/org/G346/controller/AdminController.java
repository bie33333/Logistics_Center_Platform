package org.G346.controller;


import org.G346.pojo.Admin;
import org.G346.service.AdminService;
import org.G346.utils.PageResult;
import org.G346.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AdminController {


    @Autowired
    AdminService adminService;

    /**
     * 判断管理员是否登录成功
     * @param account
     * @param password
     * @return
     */
    @RequestMapping("adminLogin")
    public Result adminLogin(String account, String password) {
        if(account == null || account.equals(""))
            return Result.error(400, "用户名不能为空!", null);
        if(password == null || password.equals(""))
            return Result.error(400, "密码不能为空!", null);

        Admin admin = adminService.selectByPrimaryKey(account);
        if(admin==null) {
            return Result.error(400,"未找到管理员！");
        }
        else{
            String loginPassword = admin.getPassword();
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
     * 分页查找所有管理员
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @RequestMapping("selectAdmin")
    public PageResult<Admin> selectAdmin(String pageNumber, String pageSize) {
        return adminService.selectAdmin(Integer.parseInt(pageNumber),Integer.parseInt(pageSize));
    }
    /**
     * 查找所有管理员
     * @return
     */
    @RequestMapping("allAdmin")
    public PageResult<Admin> allAdmin(){
        return adminService.selectAll();
    }
    /**
     * 根据用户名查找单个管理员
     * @param account
     * @return
     */
    @RequestMapping("findAdminByAccount")
    public PageResult<Admin> findAdminByAccount(String account){
        return adminService.findAdminByAccount(account);

    }

    /**
     * 添加管理员
     * @param account
     * @param name
     * @param phone
     * @param password
     * @return
     */
    @RequestMapping("insertAdmin")
    public Result insertAdmin(String account,String name
            , String phone, String password){
        if(account == null || account.equals(""))
            return Result.error(400, "用户名不能为空!", null);
        if(name == null || account.equals(""))
            return Result.error(400, "姓名不能为空!", null);
        if(password == null || password.equals(""))
            return Result.error(400, "密码不能为空!", null);
        if(phone == null || phone.equals(""))
            return Result.error(400, "电话不能为空!", null);
        Admin admin = new Admin();
        admin.setAccount(account);
        admin.setName(name);
        admin.setPassword(password);
        admin.setPhone(phone);

        if (adminService.selectByPrimaryKey(account)!=null) {
            return Result.error(400,"添加管理员失败,用户名已存在！");
        } else {
            int row = adminService.insertAdmin(admin);
            if (row > 0) {
                return Result.ok();
            } else {
                return Result.error(400, "插入失败！", null);
            }
        }
    }

    /**
     * 根据用户名删除管理员
     * @param account
     * @return
     */
    @RequestMapping("deleteAdmin")
    public Result deleteAdmin(String account){
        if(account == null || account.equals(""))
            return Result.error(400, "用户名不能为空!", null);
        if (adminService.selectByPrimaryKey(account)==null) {
            return Result.error(400,"删除失败，用户不存在！");
        }else {
            adminService.deleteByPrimaryKey(account);
            return Result.ok();
        }
    }

    /**
     * 根据用户名更新管理员
     * @param account
     * @param name
     * @param phone
     * @param password
     * @return
     */
    @RequestMapping("updateAdmin")
    public Result updateUser(String account,String name
            , String phone, String password
            ) {
        if(account == null || account.equals(""))
            return Result.error(400, "用户名不能为空!", null);
        Admin admin = adminService.selectByPrimaryKey(account);
        if(admin==null)
            return Result.error(400, "用户不存在!", null);

        if(name!=null) admin.setName(name);
        if(password!=null)admin.setPassword(password);
        if(phone!=null)admin.setPhone(phone);
        adminService.updateByPrimaryKey(admin);
        return Result.ok();


    }

}
