package com.zwq.dao.impl;

import com.zwq.dao.ArticleDao;
import com.zwq.domain.Article;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/**
 * Created by Lancer on 2017/4/25.
 */
public class ArticleDaoImpl implements ArticleDao {

  private static SessionFactory sessionFactory;

  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public static Session getSession() {
    return getSessionFactory().getCurrentSession();
  }

  @Override
  public List<Article> searchArticleList(int id) {
    String hql = "from Article as article where article.user.id=:uid";
    Query query = getSession().createQuery(hql);
    query.setParameter("uid", id);
    Optional<List<Article>> articleList = Optional.ofNullable(query.list());
    return articleList.get();
  }

  @Override
  public boolean saveOrUpdateArticle(Article article) {
    getSession().saveOrUpdate(getSession().merge(article));
    return true;
  }

  @Override
  public Article findArticleById(int id, int userId) {
    /*Article article = getSession().get(Article.class, id);*/

    CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
    CriteriaQuery<Article> articleCriteriaQuery = criteriaBuilder.createQuery(Article.class);
    Root<Article> articleRoot = articleCriteriaQuery.from(Article.class);
    List<Predicate> predicateList = new ArrayList<>();
    Predicate unamePredicate = criteriaBuilder.equal(articleRoot.get("user").get("id"), userId);
    Predicate articlePredicate = criteriaBuilder.equal(articleRoot.get("id"), id);
    predicateList.add(articlePredicate);
    predicateList.add(unamePredicate);
    articleCriteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
    TypedQuery<Article> typedQuery = getSession().createQuery(articleCriteriaQuery);
    List<Article> result = typedQuery.getResultList();
    return result.size() > 0 ? result.get(0) : null;
  }

  @Override
  public boolean deleteArticle(Article article) {
    article = getSession().load(Article.class, article.getId());
    article.getCommentSet().remove(article);
    getSession().clear();
    getSession().delete(article);
    return true;
  }

  @Override
  public List<Article> findArticlesByUser(String user) {
    CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
    CriteriaQuery<Article> articleCriteriaQuery = criteriaBuilder.createQuery(Article.class);
    Root<Article> articleRoot = articleCriteriaQuery.from(Article.class);
    Predicate unamePredicate = criteriaBuilder.equal(articleRoot.get("user").get("userName"), user);
    articleCriteriaQuery.orderBy(criteriaBuilder.desc(articleRoot.get("datetime")));
    articleCriteriaQuery.where(unamePredicate);
    TypedQuery<Article> typedQuery = getSession().createQuery(articleCriteriaQuery);
    List<Article> result = typedQuery.getResultList();
    return result;
  }

  /**
   * restful查询文章和首页加载文章
   */
  @Override
  public List<Article> searchArticlesByKeyword(String user, String keyword) {
    CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
    CriteriaQuery<Article> articleCriteriaQuery = criteriaBuilder.createQuery(Article.class);
    Root<Article> articleRoot = articleCriteriaQuery.from(Article.class);
    List<Predicate> predicateList = new ArrayList<>();
    Predicate unamePredicate = criteriaBuilder.equal(articleRoot.get("user").get("userName"), user);
    Predicate isshowPredicate = criteriaBuilder
      .equal(articleRoot.get("taxonomy").get("isshow"), "1");
    predicateList.add(isshowPredicate);
    if (keyword != null) {
      Predicate keywordPredicate = criteriaBuilder
        .like(articleRoot.get("keywords"), "%" + keyword + "%");
      predicateList.add(keywordPredicate);
    }
    articleCriteriaQuery.orderBy(criteriaBuilder.desc(articleRoot.get("datetime")));
    predicateList.add(unamePredicate);
    articleCriteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
    TypedQuery<Article> typedQuery = getSession().createQuery(articleCriteriaQuery);
    List<Article> result = typedQuery.getResultList();
    return result;
  }


  /**
   * restful加载推荐
   */
  @Override
  public List<Article> recommendArticle(String user) {
    CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
    CriteriaQuery<Article> articleCriteriaQuery = criteriaBuilder.createQuery(Article.class);
    Root<Article> articleRoot = articleCriteriaQuery.from(Article.class);
    List<Predicate> predicateList = new ArrayList<>();
    Predicate unamePredicate = criteriaBuilder.equal(articleRoot.get("user").get("userName"), user);
    Predicate keywordPredicate = criteriaBuilder.equal(articleRoot.get("isvouch"), true);
    Predicate isshowPredicate = criteriaBuilder
      .equal(articleRoot.get("taxonomy").get("isshow"), "1");
    predicateList.add(isshowPredicate);
    predicateList.add(keywordPredicate);
    predicateList.add(unamePredicate);
    articleCriteriaQuery.orderBy(criteriaBuilder.desc(articleRoot.get("datetime")));
    articleCriteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
    TypedQuery<Article> typedQuery = getSession().createQuery(articleCriteriaQuery);
    List<Article> result = typedQuery.getResultList();
    return result;
  }

  @Override
  public Article searchArticle(String user, String cateId, String articleId) {
    CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
    CriteriaQuery<Article> articleCriteriaQuery = criteriaBuilder.createQuery(Article.class);
    Root<Article> articleRoot = articleCriteriaQuery.from(Article.class);
    List<Predicate> predicateList = new ArrayList<>();
    Predicate unamePredicate = criteriaBuilder.equal(articleRoot.get("user").get("userName"), user);
    Predicate keywordPredicate = criteriaBuilder.equal(articleRoot.get("id"), articleId);
    if (cateId != null) {
      Predicate catePredicate = criteriaBuilder.equal(articleRoot.get("cate").get("id"), cateId);
      predicateList.add(catePredicate);
    }

    predicateList.add(keywordPredicate);
    predicateList.add(unamePredicate);
    articleCriteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
    TypedQuery<Article> typedQuery = getSession().createQuery(articleCriteriaQuery);
    List<Article> result = typedQuery.getResultList();
    Article article = result.size() <= 0 ? null : result.get(0);
    if (article != null) {
      article.setCommentCount(String.valueOf(article.getCommentSet().size()));
    }
    return article;
  }

  @Override
  public List<Article> articleListByCateOrTanxonomy(String user, String tid, String cid) {
    CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
    CriteriaQuery<Article> articleCriteriaQuery = criteriaBuilder.createQuery(Article.class);
    Root<Article> articleRoot = articleCriteriaQuery.from(Article.class);
    List<Predicate> predicateList = new ArrayList<>();
    Predicate unamePredicate = criteriaBuilder.equal(articleRoot.get("user").get("userName"), user);
    Predicate taxonomyPredicate = criteriaBuilder.equal(articleRoot.get("taxonomy").get("id"), tid);
    if (cid != null && !cid.equalsIgnoreCase("")) {
      Predicate catePredicate = criteriaBuilder.equal(articleRoot.get("cate").get("id"), cid);
      predicateList.add(catePredicate);
    }
    Predicate isshowPredicate = criteriaBuilder
      .equal(articleRoot.get("taxonomy").get("isshow"), "1");
    predicateList.add(isshowPredicate);
    predicateList.add(taxonomyPredicate);
    predicateList.add(unamePredicate);
    articleCriteriaQuery.orderBy(criteriaBuilder.desc(articleRoot.get("datetime")));
    articleCriteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
    TypedQuery<Article> typedQuery = getSession().createQuery(articleCriteriaQuery);
    List<Article> result = typedQuery.getResultList();
    return result;
  }

  @Override
  public int findAdminArticleCount(String userName, String keywords) {
    CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
    CriteriaQuery<Article> articleCriteriaQuery = criteriaBuilder.createQuery(Article.class);
    Root<Article> articleRoot = articleCriteriaQuery.from(Article.class);
    List<Predicate> predicateList = new ArrayList<>();
    Predicate unamePredicate = criteriaBuilder
      .equal(articleRoot.get("user").get("userName"), userName);
    predicateList.add(unamePredicate);
    if (keywords != null) {
      Predicate keywordPredicate = criteriaBuilder
        .like(articleRoot.get("keywords"), "%" + keywords + "%");
      predicateList.add(keywordPredicate);
    }
    articleCriteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
    TypedQuery<Article> typedQuery = getSession().createQuery(articleCriteriaQuery);
    List<Article> result = typedQuery.getResultList();
    return result.size();
  }

  @Override
  public List<Article> findAdminArticleByPage(int begin, int pageSize, String userName,
    String keywords) {
    CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
    CriteriaQuery<Article> articleCriteriaQuery = criteriaBuilder.createQuery(Article.class);
    Root<Article> articleRoot = articleCriteriaQuery.from(Article.class);
    List<Predicate> predicateList = new ArrayList<>();
    Predicate unamePredicate = criteriaBuilder
      .equal(articleRoot.get("user").get("userName"), userName);
    predicateList.add(unamePredicate);
    if (keywords != null) {
      Predicate keywordPredicate = criteriaBuilder
        .like(articleRoot.get("keywords"), "%" + keywords + "%");
      predicateList.add(keywordPredicate);
    }
    articleCriteriaQuery.orderBy(criteriaBuilder.desc(articleRoot.get("datetime")));
    articleCriteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
    TypedQuery<Article> typedQuery = getSession().createQuery(articleCriteriaQuery);
    typedQuery.setFirstResult(begin);
    typedQuery.setMaxResults(pageSize);
    List<Article> result = typedQuery.getResultList();
    return result;
  }

  @Override
  public List<Article> searchArticleByCate(String userName, String cateId) {
    CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
    CriteriaQuery<Article> articleCriteriaQuery = criteriaBuilder.createQuery(Article.class);
    Root<Article> articleRoot = articleCriteriaQuery.from(Article.class);
    List<Predicate> predicateList = new ArrayList<>();
    Predicate unamePredicate = criteriaBuilder
      .equal(articleRoot.get("user").get("userName"), userName);
    Predicate catePredicate = criteriaBuilder.equal(articleRoot.get("cate").get("id"), cateId);
    predicateList.add(catePredicate);
    predicateList.add(unamePredicate);
    articleCriteriaQuery.orderBy(criteriaBuilder.desc(articleRoot.get("datetime")));
    articleCriteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
    TypedQuery<Article> typedQuery = getSession().createQuery(articleCriteriaQuery);
    List<Article> result = typedQuery.getResultList();
    return result;
  }

  @Override
  public void articleAddViewCount(String user, String aid) {
    CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
    CriteriaQuery<Article> articleCriteriaQuery = criteriaBuilder.createQuery(Article.class);
    Root<Article> articleRoot = articleCriteriaQuery.from(Article.class);
    List<Predicate> predicateList = new ArrayList<>();
    Predicate unamePredicate = criteriaBuilder.equal(articleRoot.get("user").get("userName"), user);
    Predicate keywordPredicate = criteriaBuilder.equal(articleRoot.get("id"), aid);
    predicateList.add(keywordPredicate);
    predicateList.add(unamePredicate);
    articleCriteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
    TypedQuery<Article> typedQuery = getSession().createQuery(articleCriteriaQuery);
    List<Article> result = typedQuery.getResultList();
    Article article = result.size() <= 0 ? null : result.get(0);
    if (article != null) {
      if (article.getViewCount() == null) {
        article.setViewCount("1");
      } else {
        int viewCount = Integer.parseInt(article.getViewCount());
        viewCount++;
        article.setViewCount(String.valueOf(viewCount));
      }
    }
  }
}
