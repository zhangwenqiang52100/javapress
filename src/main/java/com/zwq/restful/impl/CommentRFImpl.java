package com.zwq.restful.impl;

import com.zwq.domain.Article;
import com.zwq.domain.Comment;
import com.zwq.restful.CommentRF;
import com.zwq.service.CommentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Lancer on 2017/5/12.
 */
@Component
public class CommentRFImpl implements CommentRF {

  @Autowired
  private CommentService commentService;

  @Override
  public Comment addComment(String user, String aid, Comment comment) {
    return commentService.addComment(user, aid, comment);
  }

  @Override
  public List<Comment> getAllComment(String user,String aid) {
    return commentService.getAllComment(user,aid);
  }

  @Override
  public Article latestCommentArticle(String user) {
    //加载最新评论回来写
    return commentService.latestCommentArticle(user);
  }
}
