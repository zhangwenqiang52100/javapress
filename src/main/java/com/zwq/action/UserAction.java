package com.zwq.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zwq.service.LoginService;
import com.zwq.util.Identifyingcode;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import com.zwq.domain.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

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

    /**
     * 用户登录
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String login() throws ServletException, IOException {
        User exitUser = null;
        System.out.println("执行了action");
        HttpServletRequest request= ServletActionContext.getRequest();
        HttpServletResponse response= ServletActionContext.getResponse();
        HttpSession session=  request.getSession();
        if (Identifyingcode.verifyCodeGoogle(request, response)) {
            exitUser = loginService.login(user);
        } else {
            this.addActionError("验证码错误");
            System.out.println("验证码错误");
            return INPUT;
        }
        if (exitUser == null) {
            this.addActionError("用户名密码为空");
            return INPUT;
        }
        session.setAttribute("userInfo", exitUser);
        return SUCCESS;
    }

    public String loginout() {
        ActionContext.getContext().getSession().clear();
        System.out.println("退出后的session信息:" + ActionContext.getContext().getSession().get("userInfo"));
        return INPUT;
    }
}
