package com.zwq.dao.impl;

import com.zwq.dao.CommentDao;
import com.zwq.domain.Article;
import com.zwq.domain.Comment;
import com.zwq.domain.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import sun.util.resources.LocaleData;

/**
 * Created by Lancer on 2017/5/11.
 */
@Repository
public class CommentDaoImpl implements CommentDao {

  private static SessionFactory sessionFactory;

  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  public void setSessionFactory(SessionFactory sessionFactory) {
    CommentDaoImpl.sessionFactory = sessionFactory;
  }

  public static Session getSession() {
    return getSessionFactory().getCurrentSession();
  }


  @Override
  public List<Comment> getAllComment(String userName, String aid) {
    CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
    CriteriaQuery<Comment> commentCriteriaQuery = criteriaBuilder.createQuery(Comment.class);
    Root<Comment> commentRoot = commentCriteriaQuery.from(Comment.class);
    List<Predicate> predicateList = new ArrayList<>();
    Predicate unamePredicate = criteriaBuilder
      .equal(commentRoot.get("user").get("userName"), userName);
    if (aid != null && !aid.equals("")) {
      Predicate aidPredicate = criteriaBuilder
        .equal(commentRoot.get("article").get("id"), aid);
      predicateList.add(aidPredicate);
    }
    predicateList.add(unamePredicate);
    commentCriteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
    TypedQuery<Comment> typedQuery = getSession().createQuery(commentCriteriaQuery);
    List<Comment> result = typedQuery.getResultList();
    return result;
  }

  @Override
  public Comment addComment(String user, String aid, Comment comment) {
    LocalDateTime today = LocalDateTime.now();
    comment.setDate(today.toString());
    comment.setArticle(new Article(Integer.parseInt(aid)));
    getSession().save(comment);

    return comment;
  }

  @Override
  public boolean deleteComment(Comment comment) {
    getSession().delete(comment);
    return true;
  }

  @Override
  public List<Comment> rootAllCommentList() {
    CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
    CriteriaQuery<Comment> commentCriteriaQuery = criteriaBuilder.createQuery(Comment.class);
    Root<Comment> commentRoot = commentCriteriaQuery.from(Comment.class);
    commentCriteriaQuery.select(commentRoot);
    TypedQuery<Comment> typedQuery = getSession().createQuery(commentCriteriaQuery);
    List<Comment> result = typedQuery.getResultList();
    return result;
  }

  //root用户删除单个评论
  @Override
  public boolean rootDeleteComment(Comment comment) {
    getSession().delete(comment);
    return true;
  }


  @Override
  public int findRootCommentCount() {
    String hql = "select count(*) from Comment";
    Query query = getSession().createQuery(hql);
    List<Long> list = query.list();
    if (list.size() > 0) {
      return list.get(0).intValue();
    }
    return 0;
  }

  @Override
  public List<Comment> findRootCommentByPage(int begin, int pageSize) {
    CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
    CriteriaQuery<Comment> commentCriteriaQuery = criteriaBuilder.createQuery(Comment.class);
    Root<Comment> commentRoot = commentCriteriaQuery.from(Comment.class);
    TypedQuery<Comment> typedQuery = getSession().createQuery(commentCriteriaQuery);
    typedQuery.setFirstResult(begin);
    typedQuery.setMaxResults(pageSize);
    List<Comment> result = typedQuery.getResultList();
    return result;
  }

  @Override
  public int findAdminCommentCount(String userName) {
    CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
    CriteriaQuery<Comment> commentCriteriaQuery = criteriaBuilder.createQuery(Comment.class);
    Root<Comment> commentRoot = commentCriteriaQuery.from(Comment.class);
    Predicate unamePredicate = criteriaBuilder
      .equal(commentRoot.get("user").get("userName"), userName);
    commentCriteriaQuery.where(unamePredicate);
    TypedQuery<Comment> typedQuery = getSession().createQuery(commentCriteriaQuery);
    List<Comment> result = typedQuery.getResultList();
    return result.size();
  }

  @Override
  public List<Comment> findAdminCommentByPage(int begin, int pageSize, String userName) {
    CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
    CriteriaQuery<Comment> commentCriteriaQuery = criteriaBuilder.createQuery(Comment.class);
    Root<Comment> commentRoot = commentCriteriaQuery.from(Comment.class);
    Predicate unamePredicate = criteriaBuilder
      .equal(commentRoot.get("user").get("userName"), userName);
    commentCriteriaQuery.where(unamePredicate);
    TypedQuery<Comment> typedQuery = getSession().createQuery(commentCriteriaQuery);
    typedQuery.setFirstResult(begin);
    typedQuery.setMaxResults(pageSize);
    List<Comment> result = typedQuery.getResultList();
    return result;
  }

  /**
   * 最新评论的文章
   */
  @Override
  public Article latestCommentArticle(String user) {
    CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
    CriteriaQuery<Comment> commentCriteriaQuery = criteriaBuilder.createQuery(Comment.class);
    Root<Comment> commentRoot = commentCriteriaQuery.from(Comment.class);
    List<Predicate> predicateList = new ArrayList<>();
    Predicate unamePredicate = criteriaBuilder
      .equal(commentRoot.get("user").get("userName"), user);
    commentCriteriaQuery.orderBy(criteriaBuilder.desc(commentRoot.get("date")));
    Predicate showPredicate = criteriaBuilder
      .equal(commentRoot.get("article").get("taxonomy").get("isshow"), "1");
    predicateList.add(showPredicate);
    predicateList.add(unamePredicate);
    commentCriteriaQuery.orderBy(criteriaBuilder.desc(commentRoot.get("date")));
    commentCriteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
    TypedQuery<Comment> typedQuery = getSession().createQuery(commentCriteriaQuery);
    typedQuery.setFirstResult(0);
    typedQuery.setMaxResults(1);
    List<Comment> result = typedQuery.getResultList();
    return result.size() > 0 ? result.get(0).getArticle() : null;
  }

  @Override
  public void rootDeleteCommentById(String s) {
    getSession().delete(new Comment(Integer.parseInt(s)));
  }

  @Override
  public Comment findCommentByCommentId(String commentId, String userId) {
    CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
    CriteriaQuery<Comment> commentCriteriaQuery = criteriaBuilder.createQuery(Comment.class);
    Root<Comment> commentRoot = commentCriteriaQuery.from(Comment.class);
    List<Predicate> predicateList = new ArrayList<>();
    Predicate unamePredicate = criteriaBuilder
      .equal(commentRoot.get("user").get("id"), userId);
    Predicate commentIdPredicate = criteriaBuilder
      .equal(commentRoot.get("id"), commentId);
    predicateList.add(unamePredicate);
    predicateList.add(commentIdPredicate);
    commentCriteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
    TypedQuery<Comment> typedQuery = getSession().createQuery(commentCriteriaQuery);
    List<Comment> result = typedQuery.getResultList();
    return result.size() > 0 ? result.get(0) : null;
  }
}
