package com.zwq.dao.impl;

import com.zwq.dao.OptionDao;
import com.zwq.domain.OptionInfo;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.Optional;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Hibernate;
import org.hibernate.LobHelper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by Archer on 2016/12/11.
 */
public class OptionDaoImpl implements OptionDao {

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

  /**
   * 更新option
   */
  @Override
  public boolean saveOption(OptionInfo optionInfo) throws IOException {
 /*   //得到LobHelper
    LobHelper lobHelper = getSession().getLobHelper();
    //得到图片的blob
    InputStream in = new FileInputStream(optionInfo.getLogoPath().getPath());
    Blob blob = lobHelper.createBlob(in, in.available());
    optionInfo.setLogo(blob);*/
    getSession().saveOrUpdate(getSession().merge(optionInfo));
    return true;
  }

  /**
   * 通过id获取option
   */
  @Override
  public OptionInfo findById(int sid) {
    Session session = sessionFactory.getCurrentSession();
    return (OptionInfo) session.get(OptionInfo.class, sid);
  }

  /**
   * 获取option数据
   */
  @Override
  public OptionInfo getWebOption(int id) {
    String hql = "from OptionInfo as option where option.user.id=:id";
    Query query = getSession().createQuery(hql);
    query.setParameter("id", id);
    Optional<List<OptionInfo>> optionInfoList = Optional.ofNullable(query.list());
    if (optionInfoList.get().size() <= 0) {
      return null;
    }
    return optionInfoList.get().get(0);
  }

  @Override
  public OptionInfo findOptionByUsername(String username) {
    String hql = "from OptionInfo as oi where oi.user.userName=:uname";
    Query query = getSession().createQuery(hql);
    query.setParameter("uname", username);
    Optional<List<OptionInfo>> optionInfoList = Optional.ofNullable(query.list());
    if (optionInfoList.get() != null) {
      return optionInfoList.get().get(0);
    }
    return null;
  }

  @Override
  public OptionInfo findOptionByUser(String user) {
    CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
    CriteriaQuery<OptionInfo> articleCriteriaQuery = criteriaBuilder.createQuery(OptionInfo.class);
    Root<OptionInfo> cateRoot = articleCriteriaQuery.from(OptionInfo.class);
    Predicate unamePredicate = criteriaBuilder.equal(cateRoot.get("user").get("userName"), user);
    articleCriteriaQuery.where(unamePredicate);
    TypedQuery<OptionInfo> typedQuery = getSession().createQuery(articleCriteriaQuery);
    List<OptionInfo> result = typedQuery.getResultList();
    return result.get(0);
  }

}
