package com.zwq.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zwq.domain.PageBean;
import com.zwq.domain.User;
import com.zwq.service.UserService;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

/**
 * Created by Archer on 2016/12/6.
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {

  private User user = new User();
  private UserService userService;
  private String result;
  private Integer currPage = 1;

  public void setCurrPage(Integer currPage) {
    this.currPage = currPage;
  }


  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  @Override
  public User getModel() {
    return user;
  }


  public String getBaseInfo() {
    HttpServletRequest request = ServletActionContext.getRequest();
    user = (User) request.getSession().getAttribute("user");
    return SUCCESS;
  }

  /**
   * 更改用户密码
   */
  public String updatePassword() {
    HttpServletRequest request = ServletActionContext.getRequest();
    String password = request.getParameter("renewpass");
    user = (User) request.getSession().getAttribute("user");
    user.setPassword(password);
    userService.updatePassword(user);
    ActionContext.getContext().getSession().clear();
    return "changePassword";
  }

  /**
   * 用户注册界面
   */
 /* public String registerUser() throws IOException {
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
  }*/

  public String getAllUser() {
    HttpServletRequest request = ServletActionContext.getRequest();
    user = (User) request.getSession().getAttribute("user");
    if (user != null && user.getIsRoot().equalsIgnoreCase("root")) {
      PageBean<User> pageBean = userService.findUserByPage(currPage);
/*    List<User> userList = userService.getAllUser();
    userList = userList.parallelStream()
      .filter(user1 -> !user1.getIsRoot().equalsIgnoreCase("root")).collect(
        Collectors.toList());
    ActionContext.getContext().getValueStack().set("userList", userList);*/
      ActionContext.getContext().getValueStack().set("userList", pageBean.getList());
      ActionContext.getContext().getValueStack().push(pageBean);
    }

    return "allUserPage";
  }

  public String getBaseInitAllUser() {
    HttpServletRequest request = ServletActionContext.getRequest();
    user = (User) request.getSession().getAttribute("user");
    if (user != null && user.getIsRoot().equalsIgnoreCase("root")) {
      List<User> userList = userService.getAllUser();
      ActionContext.getContext().getValueStack().set("userList", userList);
    }
    return "initAllUser";
  }

  //root删除用户
  public String deleteUser() {
    boolean flag = userService.deleteUser(user);
    return SUCCESS;
  }

  public String deleteUsers() {
    HttpServletRequest request = ServletActionContext.getRequest();
    String ids = request.getParameter("ids");
    String[] id = ids.split(",");
    for (String s : id) {
      userService.rootDeleteUsersById(s);
    }
    return SUCCESS;
  }

}
