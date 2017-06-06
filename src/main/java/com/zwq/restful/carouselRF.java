package com.zwq.restful;

import com.zwq.domain.Carousel;
import java.util.List;

/**
 * Created by Lancer on 2017/5/9.
 */
public interface carouselRF {

  List<Carousel> searchCarouseListByUser(String user);
}
