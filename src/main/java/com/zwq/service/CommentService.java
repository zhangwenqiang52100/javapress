package com.zwq.service;

import com.zwq.domain.Article;
import com.zwq.domain.Comment;
import com.zwq.domain.PageBean;
import com.zwq.domain.User;
import java.util.List;

/**
 * Created by Lancer on 2017/5/11.
 */
public interface CommentService {

  //restful获取留言列表。
  List<Comment> getAllComment(String userName, String aid);

  //restful添加留言。
  Comment addComment(String user, String aid, Comment comment);

  boolean deleteComment(Comment comment);

  List<Comment> getRootAllComment();

  boolean rootDeleteComment(Comment comment);

  PageBean<Comment> findRootCommentByPage(Integer currPage);

  PageBean<Comment> findCommentByPage(Integer currPage, String userName);

  Article latestCommentArticle(String user);

  void rootDeleteCommentById(String s);

  void adminDeleteCommentsById(String s,String userId);

  Comment findCommentByCommentId(String commentId,String userId);
}
