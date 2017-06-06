package com.zwq.service;

import com.zwq.domain.Carousel;
import java.util.List;

/**
 * Created by Lancer on 2017/4/12.
 */
public interface CarouselService {

  void addCarousel(Carousel carousel);

  List<Carousel> searchCarouselList(int id);

  boolean deleteCarousel(Carousel carousel);

  Carousel editCarousel(int carouselId, int id);

  List<Carousel> searchCarouseListByUser(String user);
}
