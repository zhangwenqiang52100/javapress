package com.zwq.restful.impl;

import com.opensymphony.xwork2.ActionContext;
import com.zwq.domain.Taxonomy;
import com.zwq.domain.User;
import com.zwq.restful.TaxonomyRF;
import com.zwq.service.TaxonomyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by Lancer on 2017/5/3.
 */
@Component
public class TaxonomyRFImpl implements TaxonomyRF {

  @Autowired
  private TaxonomyService taxonomyService;

  public void setTaxonomyService(TaxonomyService taxonomyService) {
    this.taxonomyService = taxonomyService;
  }

  @Override
  public List<Taxonomy> findTaxonomyByUser(String user) {
    return taxonomyService.findTaxonomyByUser(user);
  }
}
