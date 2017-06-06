package com.zwq.service.impl;

import com.zwq.dao.CarouselDao;
import com.zwq.domain.Carousel;
import com.zwq.service.CarouselService;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Lancer on 2017/4/12.
 */
@Transactional
public class CarouselServiceImpl implements CarouselService {

  private CarouselDao carouselDao;

  public void setCarouselDao(CarouselDao carouselDao) {
    this.carouselDao = carouselDao;
  }

  @Override
  public void addCarousel(Carousel carousel) {
    carouselDao.addCarousel(carousel);
  }

  @Override
  public List<Carousel> searchCarouselList(int id) {
    return carouselDao.searchCarouselList(id);
  }

  @Override
  public boolean deleteCarousel(Carousel carousel) {
    return carouselDao.deleteCarousel(carousel);
  }

  @Override
  public Carousel editCarousel(int carouselId, int uid) {
    return carouselDao.editCarousel(carouselId,uid);
  }

  @Override
  public List<Carousel> searchCarouseListByUser(String user) {
    return carouselDao.searchCarouseListByUser(user);
  }
}
