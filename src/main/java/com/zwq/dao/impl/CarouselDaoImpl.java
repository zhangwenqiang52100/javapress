package com.zwq.dao.impl;

import com.zwq.dao.CarouselDao;
import com.zwq.domain.Article;
import com.zwq.domain.Carousel;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/**
 * Created by Lancer on 2017/4/12.
 */
public class CarouselDaoImpl implements CarouselDao {

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
  public void addCarousel(Carousel carousel) {
    if (existCarouse(carousel.getId())) {
      getSession().update(getSession().merge(carousel));
    } else {
      getSession().save(carousel);
    }
  }

  @Override
  public List<Carousel> searchCarouselList(int id) {
    String hql = "from Carousel as carousel where carousel.user.id=:uid";
    Query query = getSession().createQuery(hql);
    query.setParameter("uid", id);
    Optional<List<Carousel>> carouselList = Optional.ofNullable(query.list());
    return carouselList.get();
  }

  @Override
  public boolean deleteCarousel(Carousel carousel) {
    getSession().delete(carousel);
    return true;
  }

  @Override
  public Carousel editCarousel(int carouselId, int uid) {
    CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
    CriteriaQuery<Carousel> carouselCriteriaQuery = criteriaBuilder.createQuery(Carousel.class);
    Root<Carousel> carouselRoot = carouselCriteriaQuery.from(Carousel.class);
    List<Predicate> predicateList = new ArrayList<>();
    Predicate unamePredicate = criteriaBuilder.equal(carouselRoot.get("user").get("id"), uid);
    Predicate keywordPredicate = criteriaBuilder.equal(carouselRoot.get("id"), carouselId);
    predicateList.add(keywordPredicate);
    predicateList.add(unamePredicate);
    carouselCriteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
    TypedQuery<Carousel> typedQuery = getSession().createQuery(carouselCriteriaQuery);
    List<Carousel> result = typedQuery.getResultList();
    return result.size() <= 0 ? null : result.get(0);
  }

  @Override
  public List<Carousel> searchCarouseListByUser(String user) {
    CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
    CriteriaQuery<Carousel> carouselCriteriaQuery = criteriaBuilder.createQuery(Carousel.class);
    Root<Carousel> carouselRoot = carouselCriteriaQuery.from(Carousel.class);
    List<Predicate> predicateList = new ArrayList<>();
    Predicate unamePredicate = criteriaBuilder.equal(carouselRoot.get("user").get("userName"), user);
    predicateList.add(unamePredicate);
    carouselCriteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
    TypedQuery<Carousel> typedQuery = getSession().createQuery(carouselCriteriaQuery);
    List<Carousel> result = typedQuery.getResultList();
    return result;
  }

  /**
   * 根据id查找轮播.
   */
  public static Boolean existCarouse(int id) {
    Carousel carousel = getSession().get(Carousel.class, id);
    if (carousel != null) {
      return true;
    } else {
      return false;
    }
  }
}
