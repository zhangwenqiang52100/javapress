package com.zwq.dao.impl;

import com.zwq.dao.TaxonomyDao;
import com.zwq.domain.Article;
import com.zwq.domain.Taxonomy;
import com.zwq.restful.TaxonomyRF;
import com.zwq.service.TaxonomyService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;

/**
 * Created by Lancer on 2017/4/15.
 */
public class TaxonomyDaoImpl implements TaxonomyDao {

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

  private TaxonomyService taxonomyService;

  public void setTaxonomyService(TaxonomyService taxonomyService) {
    this.taxonomyService = taxonomyService;
  }

  /**
   * 更新和添加栏目
   */
  @Override
  public boolean addOrUpdate(Taxonomy taxonomy) {
    if (existTaxonomy(taxonomy.getId())) {
      getSession().update(getSession().merge(taxonomy));
    } else {
      getSession().save(taxonomy);
    }
    return true;
  }

  /**
   * 获取栏目列表
   *
   * @return 栏目列表
   */
  @Override
  public List<Taxonomy> searchTaxonomyList(int id) {
    String hql = "from Taxonomy as taxonomy where taxonomy.user.id=:uid";
    Query query = getSession().createQuery(hql);
    query.setParameter("uid", id);
    Optional<List<Taxonomy>> taxonomyList = Optional.ofNullable(query.list());
    return taxonomyList.get();
  }


  /**
   * 判断栏目是否存在
   *
   * @param id 栏目id
   */
  public static boolean existTaxonomy(int id) {
    Taxonomy taxonomy = getSession().get(Taxonomy.class, id);
    if (taxonomy != null) {
      return true;
    }
    return false;
  }


  public Taxonomy searchCarousel(int id) {
    Taxonomy taxonomy = getSession().get(Taxonomy.class, id);
    if (taxonomy != null) {
      return taxonomy;
    }
    return null;
  }

  /**
   * 删除栏目（包括下面的列表）。
   */
  @Override
  public boolean deleteTaxonomy(Taxonomy taxonomy) {
    taxonomy = getSession().load(Taxonomy.class, taxonomy.getId());
    taxonomy.getCateSet().remove(taxonomy);
    getSession().clear();
    getSession().delete(taxonomy);
    return true;
  }

  @Override
  public List<Taxonomy> findTaxonomyByUser(String user) {
    //select t.id,t.title,t.isshow
    String hql = "from Taxonomy as t where t.user.userName=:un";
    List<Taxonomy> taxonomyList = getSession().createQuery(hql).setParameter("un", user)
      .list();
    return taxonomyList.parallelStream()
      .filter(taxonomy -> taxonomy.getIsshow().equalsIgnoreCase("1")).collect(
        Collectors.toList());
  }
}
