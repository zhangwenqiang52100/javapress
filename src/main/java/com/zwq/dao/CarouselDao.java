package com.zwq.dao;

import com.zwq.domain.Carousel;
import java.util.List;

/**
 * Created by Lancer on 2017/4/12.
 */
public interface CarouselDao {

  void addCarousel(Carousel content);

  List<Carousel> searchCarouselList(int id);

  boolean deleteCarousel(Carousel carousel);

  Carousel editCarousel(int carouselId,int uid);

  List<Carousel> searchCarouseListByUser(String user);
}
