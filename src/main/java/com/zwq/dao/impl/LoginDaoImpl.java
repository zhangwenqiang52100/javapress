package com.zwq.dao.impl;

import com.zwq.dao.LoginDao;
import com.zwq.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;


/**
 * Created by Archer on 2016/12/6.
 */
public class LoginDaoImpl implements LoginDao {

  private SessionFactory sessionFactory;

  public SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public User findByNameAndPass(User user) {
    System.out.println("执行了dao");
    String hql = "from User u where u.userName=:username and u.password=:password";
    Session session = getSessionFactory().getCurrentSession();
    Query query = session.createQuery(hql);
    query.setParameter("username", user.getUserName());
    query.setParameter("password", user.getPassword());
    List<User> userList = query.list();
    for (User user1 : userList) {
      System.out.println(user1.getUserName());
    }
    if (userList.size() > 0) {
      return userList.get(0);
    }
    return null;
  }
}