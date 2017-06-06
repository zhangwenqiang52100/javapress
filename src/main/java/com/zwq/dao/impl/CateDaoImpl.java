package com.zwq.dao.impl;

import com.zwq.dao.CateDao;
import com.zwq.domain.Article;
import com.zwq.domain.Cate;
import com.zwq.domain.Taxonomy;
import com.zwq.domain.entities.Cate_;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
 * Created by Lancer on 2017/4/19.
 */
public class CateDaoImpl implements CateDao {

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
   * 查询分类列表
   */
  @Override
  public List<Cate> searchCateList(int id) {
    String hql = "from Cate cate where cate.user.id=:uid";
    Query query = getSession().createQuery(hql);
    query.setParameter("uid", id);
    Optional<List<Cate>> cateList = Optional.ofNullable(query.list());
    return cateList.get();
  }

  @Override
  public void addOrUpdate(Cate cate) {
    getSession().saveOrUpdate(getSession().merge(cate));
  }

  @Override
  public boolean deleteCate(Cate cate) {
    cate = getSession().load(Cate.class, cate.getId());
    cate.getArticleSet().remove(cate);
    getSession().clear();
    getSession().delete(cate);
    return true;
  }

  @Override
  public List<Cate> findCateByTaxonomyIdAndUser(String user, int tid) {
    CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
    CriteriaQuery<Cate> cateCriteriaQuery = criteriaBuilder.createQuery(Cate.class);
    Root<Cate> cateRoot = cateCriteriaQuery.from(Cate.class);
    List<Predicate> predicateList = new ArrayList<>();
    Predicate unamePredicate = criteriaBuilder.equal(cateRoot.get("user").get("userName"), user);
    Predicate tidPredicate = criteriaBuilder.equal(cateRoot.get("taxonomy").get("id"), tid);
    predicateList.add(unamePredicate);
    predicateList.add(tidPredicate);
    Path<Integer> cateId = cateRoot.get("id");
    Path<String> cateTitle = cateRoot.get("title");
    cateCriteriaQuery.select((Selection<? extends Cate>) criteriaBuilder.tuple(cateId, cateTitle));
    cateCriteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
    TypedQuery<Cate> typedQuery = getSession().createQuery(cateCriteriaQuery);
    List<Cate> result = typedQuery.getResultList();
    return result;
  }

  @Override
  public Cate findTaxonomyByCateId(int cateId, int uid) {
    return getSession().load(Cate.class, cateId);
  }

  @Override
  public int findAdminCateCount(String userName, String keywords) {
    CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
    CriteriaQuery<Cate> cateCriteriaQuery = criteriaBuilder.createQuery(Cate.class);
    Root<Cate> cateRoot = cateCriteriaQuery.from(Cate.class);
    List<Predicate> predicateList = new ArrayList<>();
    Predicate unamePredicate = criteriaBuilder
      .equal(cateRoot.get("user").get("userName"), userName);
    predicateList.add(unamePredicate);
    if (keywords != null) {
      Predicate keywordPredicate = criteriaBuilder
        .like(cateRoot.get("keywords"), "%" + keywords + "%");
      predicateList.add(keywordPredicate);
    }
    cateCriteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
    TypedQuery<Cate> typedQuery = getSession().createQuery(cateCriteriaQuery);
    List<Cate> result = typedQuery.getResultList();
    return result.size();
  }

  @Override
  public List<Cate> findAdminCateByPage(int begin, int pageSize, String userName, String keywords) {
    CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
    CriteriaQuery<Cate> cateCriteriaQuery = criteriaBuilder.createQuery(Cate.class);
    Root<Cate> cateRoot = cateCriteriaQuery.from(Cate.class);
    List<Predicate> predicateList = new ArrayList<>();
    Predicate unamePredicate = criteriaBuilder
      .equal(cateRoot.get("user").get("userName"), userName);
    predicateList.add(unamePredicate);
    if (keywords != null) {
      Predicate keywordPredicate = criteriaBuilder
        .like(cateRoot.get("keywords"), "%" + keywords + "%");
      predicateList.add(keywordPredicate);
    }
    cateCriteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
    TypedQuery<Cate> typedQuery = getSession().createQuery(cateCriteriaQuery);
    typedQuery.setFirstResult(begin);
    typedQuery.setMaxResults(pageSize);
    List<Cate> result = typedQuery.getResultList();
    return result;
  }
}
