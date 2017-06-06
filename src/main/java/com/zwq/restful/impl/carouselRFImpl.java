package com.zwq.restful.impl;

import com.zwq.domain.Carousel;
import com.zwq.restful.carouselRF;
import com.zwq.service.CarouselService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Lancer on 2017/5/9.
 */
@Component
public class carouselRFImpl implements carouselRF {

  @Autowired
  private CarouselService carouselService;

  @Override
  public List<Carousel> searchCarouseListByUser(String user) {
    return carouselService.searchCarouseListByUser(user);
  }
}
