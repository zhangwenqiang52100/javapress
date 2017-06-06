package com.zwq.restful;

import com.zwq.domain.Article;
import com.zwq.domain.Comment;
import java.util.List;

/**
 * Created by Lancer on 2017/5/12.
 */
public interface CommentRF {

  Comment addComment(String user, String aid, Comment comment);

  List<Comment> getAllComment(String user,String aid);

  Article latestCommentArticle(String user);
}
