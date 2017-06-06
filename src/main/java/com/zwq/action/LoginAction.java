package com.zwq.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zwq.domain.User;
import com.zwq.service.LoginService;
import com.zwq.service.UserService;
import com.zwq.util.Identifyingcode;
import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

/**
 * Created by Lancer on 2017/3/24.
 */
public class LoginAction extends ActionSupport implements ModelDriven<User> {

  private User user = new User();
  private LoginService loginService;
  private UserService userService;

  public UserService getUserService() {
    return userService;
  }

  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  public void setLoginService(LoginService loginService) {
    this.loginService = loginService;
  }

  HttpServletRequest request = ServletActionContext.getRequest();
  HttpServletResponse response = ServletActionContext.getResponse();
  HttpSession session = request.getSession();

  @Override
  public User getModel() {
    return user;
  }

  /**
   * 用户登录(user管理员)
   */
  public String login() throws ServletException, IOException {
    User exitUser = null;
    if (Identifyingcode.verifyCodeGoogle(request, response)) {
      exitUser = loginService.login(user);
    } else {
      this.addActionError("验证码错误");
      return INPUT;
    }
    if (exitUser == null) {
      this.addActionError("用户名密码为空");
      return INPUT;
    }
    if (exitUser.getIsRoot().equalsIgnoreCase("root")) {
      session.setAttribute("user", exitUser);
      return "rootPage";
    }
    session.setAttribute("user", exitUser);
    return SUCCESS;
  }

  /**
   * 用户登出
   */
  public String loginout() {
    ActionContext.getContext().getSession().clear();
    System.out.println("退出后的session信息:" + ActionContext.getContext().getSession().get("user"));
    return INPUT;
  }
  /**
   * 用户注册
   */
  public String registerUser() throws IOException {
    if (user.getFile() != null) {
      String filePath = user.getFile().getPath();//图片缓存路径
      File file = new File(filePath);
      // 将商品图片上传到服务器上.
      // 获得上传图片的服务器端路径.
      String path = ServletActionContext.getServletContext().getRealPath(
        "/upload");
      // 创建文件类型对象:
      File diskFile = new File(
        path + "//" + user.getUserName() + "//headerIcon//" + user.getFileName());
      // 文件上传:
      FileUtils.copyFile(file, diskFile);
      user.setFilePath("upload/" + user.getUserName() + "//headerIcon//" + user.getFileName());
    }
    user.setIsRoot("admin");
    boolean flag = userService.addUser(user);
    return "login";
  }


}
