package com.zwq.restful.impl;

import com.zwq.domain.Cate;
import com.zwq.restful.CateRF;
import com.zwq.service.CateService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Lancer on 2017/5/3.
 */
@Component
public class CateRFImpl implements CateRF {

  @Autowired
  private CateService cateService;


  @Override
  public List<Cate> findCateByTaxonomyIdAndUser(String user, int tid) {
    return cateService.findCateByTaxonomyIdAndUser(user,tid);
  }
}
