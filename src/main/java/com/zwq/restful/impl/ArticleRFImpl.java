package com.zwq.restful.impl;

import com.zwq.domain.Article;
import com.zwq.restful.ArticleRF;
import com.zwq.service.ArticleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Lancer on 2017/5/4.
 */
@Component
public class ArticleRFImpl implements ArticleRF {

  @Autowired
  private ArticleService articleService;

  @Override
  public List<Article> findArticlesByUser(String user) {
    return articleService.findArticlesByUser(user);
  }

  @Override
  public List<Article> searchArticlesByKeyword(String user, String keyword) {
    return articleService.searchArticlesByKeyword(user, keyword);
  }

  @Override
  public List<Article> recommendArticle(String user) {
    return articleService.recommendArticle(user);
  }

  @Override
  public Article searchArticle(String user, String cateId, String articleId) {
    return articleService.searchArticle(user, cateId, articleId);
  }

  @Override
  public List<Article> articleListByCateOrTanxonomy(String user,String tid, String cid) {
    return articleService.articleListByCateOrTanxonomy(user,tid,cid);
  }

  @Override
  public void articleAddViewCount(String user, String aid) {
    articleService.articleAddViewCount(user,aid);
  }
}
