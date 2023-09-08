package org.G346.controller;

import org.G346.pojo.User;
import org.G346.utils.PageResult;
import org.G346.utils.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class LoginController {

    /**
     * 判断用户是否登录成功
     * @return Result
     */
    @RequestMapping("userLogin")
    public Result userLogin() {
        return Result.ok();
    }

    //select
    @RequestMapping("selectUser")
    public PageResult<User> selectUser() {
        User user01 = new User();
        User user02 = new User();
        return PageResult.build(List.of(user01,user02));
    }
}
