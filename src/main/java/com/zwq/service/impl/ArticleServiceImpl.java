package com.zwq.service.impl;

import com.zwq.dao.ArticleDao;
import com.zwq.domain.Article;
import com.zwq.domain.Comment;
import com.zwq.domain.PageBean;
import com.zwq.service.ArticleService;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Lancer on 2017/4/25.
 */
@Transactional
public class ArticleServiceImpl implements ArticleService {

  private ArticleDao articleDao;

  public void setArticleDao(ArticleDao articleDao) {
    this.articleDao = articleDao;
  }

  @Override
  public List<Article> searchArticleList(int id) {
    return articleDao.searchArticleList(id);
  }

  @Override
  public boolean saveOrUpdateArticle(Article article) {
    return articleDao.saveOrUpdateArticle(article);
  }

  @Override
  public Article findArticleById(int id, int userId) {
    return articleDao.findArticleById(id, userId);
  }

  @Override
  public boolean deleteArticle(Article article) {
    return articleDao.deleteArticle(article);
  }

  @Override
  public List<Article> findArticlesByUser(String user) {
    return articleDao.findArticlesByUser(user);
  }

  @Override
  public List<Article> searchArticlesByKeyword(String user, String keyword) {
    return articleDao.searchArticlesByKeyword(user, keyword);
  }

  @Override
  public List<Article> recommendArticle(String user) {
    return articleDao.recommendArticle(user);
  }

  @Override
  public Article searchArticle(String user, String cateId, String articleId) {
    return articleDao.searchArticle(user, cateId, articleId);
  }

  @Override
  public List<Article> articleListByCateOrTanxonomy(String user, String tid, String cid) {
    return articleDao.articleListByCateOrTanxonomy(user, tid, cid);
  }

  @Override
  public PageBean<Article> findArticleByPage(Integer currPage, String userName, String keywords) {
    PageBean<Article> pageBean = new PageBean<>();
    //设置起始页面
    pageBean.setCurrPage(currPage);
    //设置页面大小
    int pageSize = 5;
    pageBean.setPageSize(pageSize);
    //统计个数
    int totalCount = articleDao.findAdminArticleCount(userName, keywords);
    pageBean.setTotalCount(totalCount);
    //统计页面个数
    double tc = totalCount;
    Double num = Math.ceil(tc / pageSize);
    pageBean.setTotalpage(num.intValue());
    //开始页面
    int begin = ((currPage - 1) * pageSize);
    List<Article> list = articleDao.findAdminArticleByPage(begin, pageSize, userName, keywords);
    pageBean.setList(list);
    return pageBean;
  }

  @Override
  public List<Article> searchArticleByCate(String userName, String cateId) {
    return articleDao.searchArticleByCate(userName, cateId);
  }

  @Override
  public void articleAddViewCount(String user, String aid) {
    articleDao.articleAddViewCount(user, aid);
  }

  @Override
  public void deleteArticleById(String articleId, String userId) {
    Article article = articleDao
      .findArticleById(Integer.parseInt(articleId), Integer.parseInt(userId));
    articleDao.deleteArticle(article);
  }
}
