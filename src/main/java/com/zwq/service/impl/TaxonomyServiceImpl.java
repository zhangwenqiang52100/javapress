package com.zwq.service.impl;

import com.zwq.dao.TaxonomyDao;
import com.zwq.domain.Taxonomy;
import com.zwq.service.TaxonomyService;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Lancer on 2017/4/15.
 */
@Transactional
public class TaxonomyServiceImpl implements TaxonomyService {

  private TaxonomyDao taxonomyDao;

  public void setTaxonomyDao(TaxonomyDao taxonomyDao) {
    this.taxonomyDao = taxonomyDao;
  }

  @Override
  public boolean addOrUpdate(Taxonomy taxonomy) {
    return taxonomyDao.addOrUpdate(taxonomy);
  }

  @Override
  public Taxonomy searchCarousel(int id) {
    return taxonomyDao.searchCarousel(id);
  }

  @Override
  public List<Taxonomy> searchTaxonomyList(int id) {
    return taxonomyDao.searchTaxonomyList(id);
  }

  @Override
  public boolean deleteTaxonomy(Taxonomy taxonomy) {
    return taxonomyDao.deleteTaxonomy(taxonomy);
  }

  @Override
  public List<Taxonomy> findTaxonomyByUser(String user) {
    return taxonomyDao.findTaxonomyByUser(user);
  }
}
