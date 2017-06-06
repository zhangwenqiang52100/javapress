package com.zwq.service;

import com.zwq.domain.Taxonomy;
import java.util.List;

/**
 * Created by Lancer on 2017/4/15.
 */
public interface TaxonomyService {

  boolean addOrUpdate(Taxonomy taxonomy);

  Taxonomy searchCarousel(int id);

  List<Taxonomy> searchTaxonomyList(int id);

  boolean deleteTaxonomy(Taxonomy taxonomy);

  List<Taxonomy> findTaxonomyByUser(String user);
}
