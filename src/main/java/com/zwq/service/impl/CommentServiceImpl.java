package com.zwq.service.impl;

import com.zwq.dao.CommentDao;
import com.zwq.domain.Article;
import com.zwq.domain.Comment;
import com.zwq.domain.PageBean;
import com.zwq.domain.User;
import com.zwq.service.CommentService;
import com.zwq.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Lancer on 2017/5/11.
 */
@Service
@Transactional
public class CommentServiceImpl implements CommentService {

  @Autowired
  private CommentDao commentDao;
  @Autowired
  private UserService userService;

  @Override
  public List<Comment> getAllComment(String userName, String aid) {
    return commentDao.getAllComment(userName, aid);
  }

  @Override
  public Comment addComment(String user, String aid, Comment comment) {
    User user1 = userService.findUserByUserName(user);
    comment.setUser(user1);
    return commentDao.addComment(user, aid, comment);
  }

  @Override
  public boolean deleteComment(Comment comment) {
    return commentDao.deleteComment(comment);
  }

  @Override
  public List<Comment> getRootAllComment() {
    return commentDao.rootAllCommentList();
  }

  @Override
  public boolean rootDeleteComment(Comment comment) {
    return commentDao.rootDeleteComment(comment);
  }

  @Override
  public PageBean<Comment> findRootCommentByPage(Integer currPage) {
    PageBean<Comment> pageBean = new PageBean<>();
    //设置起始页面
    pageBean.setCurrPage(currPage);
    //设置页面大小
    int pageSize = 5;
    pageBean.setPageSize(pageSize);
    //统计个数
    int totalCount = commentDao.findRootCommentCount();
    pageBean.setTotalCount(totalCount);
    //统计页面个数
    double tc = totalCount;
    Double num = Math.ceil(tc / pageSize);
    pageBean.setTotalpage(num.intValue());
    //开始页面
    int begin = ((currPage - 1) * pageSize);
    List<Comment> list = commentDao.findRootCommentByPage(begin, pageSize);
    pageBean.setList(list);
    return pageBean;
  }

  @Override
  public PageBean<Comment> findCommentByPage(Integer currPage, String userName) {
    PageBean<Comment> pageBean = new PageBean<>();
    //设置起始页面
    pageBean.setCurrPage(currPage);
    //设置页面大小
    int pageSize = 5;
    pageBean.setPageSize(pageSize);
    //统计个数
    int totalCount = commentDao.findAdminCommentCount(userName);
    pageBean.setTotalCount(totalCount);
    //统计页面个数
    double tc = totalCount;
    Double num = Math.ceil(tc / pageSize);
    pageBean.setTotalpage(num.intValue());
    //开始页面
    int begin = ((currPage - 1) * pageSize);
    List<Comment> list = commentDao.findAdminCommentByPage(begin, pageSize, userName);
    pageBean.setList(list);
    return pageBean;
  }

  /**
   * 加载最新评论文章
   */
  @Override
  public Article latestCommentArticle(String user) {
    return commentDao.latestCommentArticle(user);
  }

  @Override
  public void rootDeleteCommentById(String s) {
    commentDao.rootDeleteCommentById(s);
  }

  @Override
  public void adminDeleteCommentsById(String s, String userId) {
    Comment comment = commentDao.findCommentByCommentId(s, userId);
    commentDao.deleteComment(comment);
  }

  @Override
  public Comment findCommentByCommentId(String commentId, String userId) {
    return commentDao.findCommentByCommentId(commentId, userId);
  }
}
