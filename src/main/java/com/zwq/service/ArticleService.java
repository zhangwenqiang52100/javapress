package com.zwq.service;

import com.zwq.domain.Article;
import com.zwq.domain.PageBean;
import java.util.List;

/**
 * Created by Lancer on 2017/4/25.
 */
public interface ArticleService {

  List<Article> searchArticleList(int id);

  boolean saveOrUpdateArticle(Article article);

  Article findArticleById(int id,int userId);

  boolean deleteArticle(Article article);

  List<Article> findArticlesByUser(String user);

  List<Article> searchArticlesByKeyword(String user, String keyword);

  List<Article> recommendArticle(String user);

  Article searchArticle(String user, String cateId, String articleId);

  List<Article> articleListByCateOrTanxonomy(String user,String tid, String cid);

  PageBean<Article> findArticleByPage(Integer currPage, String userName,String keywords);

  List<Article> searchArticleByCate(String userName, String cateId);

  void articleAddViewCount(String user, String aid);

  void deleteArticleById(String articleId, String userId);
}
