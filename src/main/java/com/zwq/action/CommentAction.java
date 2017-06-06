package com.zwq.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zwq.domain.Comment;
import com.zwq.domain.PageBean;
import com.zwq.domain.User;
import com.zwq.service.CommentService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Lancer on 2017/5/11.
 */
@Component
public class CommentAction extends ActionSupport implements ModelDriven<Comment> {

  private Comment comment = new Comment();
  private Integer currPage = 1;

  public void setCurrPage(Integer currPage) {
    this.currPage = currPage;
  }

  @Autowired
  private CommentService commentService;

  @Override
  public Comment getModel() {
    return comment;
  }

  /**
   * 普通账户分页获取留言
   */
  public String getAllComment() {
    HttpServletRequest request = ServletActionContext.getRequest();
    User user = (User) request.getSession().getAttribute("user");
    if (user != null) {
      PageBean<Comment> pageBean = commentService.findCommentByPage(currPage, user.getUserName());
      ActionContext.getContext().getValueStack().set("commentList", pageBean.getList());
      ActionContext.getContext().getValueStack().push(pageBean);
    }

/*    User user = (User) ActionContext.getContext().getSession().get("user");
    List<Comment> commentList = commentService.getAllComment(user.getUserName(), null);
    ActionContext.getContext().getValueStack().set("commentList", commentList);*/
    return SUCCESS;
  }

  public String deleteComment() {
    User user = (User) ActionContext.getContext().getSession().get("user");
    if ((comment.getUser() == null
      || comment.getUser().getId() != user.getId())
      || (comment.getArticle() == null
      || comment.getArticle().getId() <= 0)
      || (comment.getId() <= 0)) {
      return null;
    } else {
      boolean flag = commentService.deleteComment(comment);
    }
    return SUCCESS;
  }


  //获取所有留言(root管理员账户)
  public String getRootAllComment() {
    HttpServletRequest request = ServletActionContext.getRequest();
    User user = (User) request.getSession().getAttribute("user");
    if (user != null && user.getIsRoot().equalsIgnoreCase("root")) {
      PageBean<Comment> pageBean = commentService.findRootCommentByPage(currPage);
      ActionContext.getContext().getValueStack().set("rootAllCommentList", pageBean.getList());
      ActionContext.getContext().getValueStack().push(pageBean);
    }
    return "rootAllCommentList";
  }

  //root用户删除留言
  public String rootDeleteComment() {
    boolean flag = commentService.rootDeleteComment(comment);
    return SUCCESS;
  }


  //root用户批量删除留言
  public String rootDeleteComments() {
    HttpServletRequest request = ServletActionContext.getRequest();
    String ids = request.getParameter("ids");
    String[] id = ids.split(",");
    for (String s : id) {
      commentService.rootDeleteCommentById(s);
    }
    return SUCCESS;
  }

  //admin账户批量删除留言
  public String adminDeleteCommentsById() {
    HttpServletRequest request = ServletActionContext.getRequest();
    User user = (User) request.getSession().getAttribute("user");
    String ids = request.getParameter("ids");
    String[] id = ids.split(",");
    for (String s : id) {
      commentService.adminDeleteCommentsById(s, String.valueOf(user.getId()));
    }
    return SUCCESS;
  }
}
