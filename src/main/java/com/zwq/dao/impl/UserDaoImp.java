package com.zwq.dao.impl;

import com.zwq.dao.UserDao;
import com.zwq.domain.User;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/**
 * Created by Lancer on 2017/3/24.
 */
public class UserDaoImp implements UserDao {

  private static SessionFactory sessionFactory;

  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public static Session getSession() {
    return sessionFactory.getCurrentSession();
  }

  @Override
  public Map<String, String> getBaseInfo(String userName) {
    return null;
  }

  @Override
  public boolean updatePassword(User user) {
     String newPassWord = user.getPassword();
    user = getSession().load(User.class, user.getId());
    user.getCommentSet().remove(user);
    user.getCarouselSet().remove(user);
    user.getTaxonomySet().remove(user);
    user.getCateSet().remove(user);
    user.getArticleSet().remove(user);
    getSession().clear();
    user.setPassword(newPassWord);
    getSession().update(user);
    return true;
  }

  @Override
  public boolean addUser(User user) {
    getSession().save(user);
    return true;
  }

  @Override
  public boolean checkUserName(String userName) {
    CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
    CriteriaQuery<User> userCriteriaQuery = criteriaBuilder.createQuery(User.class);
    Root<User> userRoot = userCriteriaQuery.from(User.class);
    Predicate unamePredicate = criteriaBuilder.equal(userRoot.get("userName"), userName);
    userCriteriaQuery.where(unamePredicate);
    TypedQuery<User> typedQuery = getSession().createQuery(userCriteriaQuery);
    List<User> result = typedQuery.getResultList();
    if (result.size() > 0) {
      return false;
    } else {
      return true;
    }
  }

  @Override
  public User findUserByUserName(String userName) {
    CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
    CriteriaQuery<User> userCriteriaQuery = criteriaBuilder.createQuery(User.class);
    Root<User> userRoot = userCriteriaQuery.from(User.class);
    Predicate unamePredicate = criteriaBuilder.equal(userRoot.get("userName"), userName);
    userCriteriaQuery.where(unamePredicate);
    TypedQuery<User> typedQuery = getSession().createQuery(userCriteriaQuery);
    List<User> result = typedQuery.getResultList();
    return result.size() > 0 ? result.get(0) : null;
  }

  @Override
  public List<User> getAllUser() {
    CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
    CriteriaQuery<User> userCriteriaQuery = criteriaBuilder.createQuery(User.class);
    Root<User> userRoot = userCriteriaQuery.from(User.class);
    userCriteriaQuery.select(userRoot);
    TypedQuery<User> typedQuery = getSession().createQuery(userCriteriaQuery);
    List<User> result = typedQuery.getResultList();
    return result;
  }

  @Override
  public boolean deleteUser(User user) {
    user = getSession().load(User.class, user.getId());
    user.getCommentSet().remove(user);
    user.getCarouselSet().remove(user);
    user.getTaxonomySet().remove(user);
    user.getCateSet().remove(user);
    user.getArticleSet().remove(user);
    getSession().clear();
    getSession().delete(user);
    return false;
  }

  @Override
  public int findUserCount() {
    String hql = "select count(*) from User";
    Query query = getSession().createQuery(hql);
    List<Long> list = query.list();
    if (list.size() > 0) {
      return list.get(0).intValue() - 1;
    }
    return 0;
  }

  @Override
  public List<User> findUserByPage(int begin, int pageSize) {
    CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
    CriteriaQuery<User> userCriteriaQuery = criteriaBuilder.createQuery(User.class);
    Root<User> userRoot = userCriteriaQuery.from(User.class);
    TypedQuery<User> typedQuery = getSession().createQuery(userCriteriaQuery);
    typedQuery.setFirstResult(begin);
    typedQuery.setMaxResults(pageSize);
    List<User> result = typedQuery.getResultList();
    result = result.parallelStream()
      .filter(user1 -> !user1.getIsRoot().equalsIgnoreCase("root")).collect(
        Collectors.toList());
    return result;
  }

  @Override
  public void rootDeleteUsersById(String s) {
    getSession().delete(new User(Integer.parseInt(s)));
  }
}
