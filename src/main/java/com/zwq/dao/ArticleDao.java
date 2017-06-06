package com.zwq.dao;

import com.zwq.domain.Article;
import java.util.List;

/**
 * Created by Lancer on 2017/4/25.
 */
public interface ArticleDao {

  List<Article> searchArticleList(int id);

  boolean saveOrUpdateArticle(Article article);

  Article findArticleById(int id,int userId);

  boolean deleteArticle(Article article);

  List<Article> findArticlesByUser(String user);

  List<Article> searchArticlesByKeyword(String user, String keyword);

  List<Article> recommendArticle(String user);

  Article searchArticle(String user, String articleId, String cateId);

  List<Article> articleListByCateOrTanxonomy(String user,String tid, String cid);

  int findAdminArticleCount(String userName,String keywords);

  List<Article> findAdminArticleByPage(int begin, int pageSize, String userName,String keywords);

  List<Article> searchArticleByCate(String userName, String cateId);

  void articleAddViewCount(String user, String aid);
}
