package com.zwq.dao.impl;

import com.zwq.dao.OptionDao;
import com.zwq.domain.Option;
import com.zwq.domain.OptionEnum;
import com.zwq.domain.OptionInfo;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Arrays;
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
   * @param optionInfo
   */
  @Override
  public void saveOption(OptionInfo optionInfo) {
    System.out.println("OptionDao执行了，，，，，");
    Session session = getSessionFactory().getCurrentSession();
    session.saveOrUpdate(optionInfo);
  }
  /**
   * 通过id获取option
   * @param sid
   * @return
   */
  @Override
  public OptionInfo findById(int sid) {
    Session session = sessionFactory.getCurrentSession();
    return (OptionInfo) session.get(OptionInfo.class, sid);
  }

  /**
   * 通过id获取option
   * @param sid
   * @return
   */
  @Override
  public OptionInfo getWebOption(int sid) {
    sid = 123;
    return getSession().get(OptionInfo.class, sid);
  }
}
