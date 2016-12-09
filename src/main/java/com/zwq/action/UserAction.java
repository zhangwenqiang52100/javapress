package com.zwq.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zwq.domain.User;
import com.zwq.service.LoginService;

/**
 * Created by Archer on 2016/12/6.
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {
    private User user = new User();
    private LoginService loginService;

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public User getModel() {
        return user;
    }

    public String login() {
        User exitUser = loginService.login(user);
        System.out.println("执行了action");
        if (exitUser==null)
        {
            this.addActionError("用户名密码为空");
            return INPUT;
        }
        ActionContext.getContext().getSession().put("userInfo",exitUser);
        return SUCCESS;
    }
}
