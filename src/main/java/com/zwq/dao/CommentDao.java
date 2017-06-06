package com.zwq.dao;

import com.zwq.domain.Article;
import com.zwq.domain.Comment;
import java.util.List;

/**
 * Created by Lancer on 2017/5/11.
 */
public interface CommentDao {

  List<Comment> getAllComment(String userName,String aid);

  Comment addComment(String user, String aid, Comment comment);

  boolean deleteComment(Comment comment);

  List<Comment> rootAllCommentList();

  boolean rootDeleteComment(Comment comment);

  int findRootCommentCount();

  List<Comment> findRootCommentByPage(int begin, int pageSize);

  int findAdminCommentCount(String userName);

  List<Comment> findAdminCommentByPage(int begin, int pageSize, String userName);

  Article latestCommentArticle(String user);

  void rootDeleteCommentById(String s);

  Comment findCommentByCommentId(String commentId,String userId);
}
