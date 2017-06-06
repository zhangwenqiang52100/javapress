package com.zwq.dao;

import com.zwq.domain.Taxonomy;
import java.util.List;

/**
 * Created by Lancer on 2017/4/15.
 */
public interface TaxonomyDao {

  boolean addOrUpdate(Taxonomy taxonomy);

  List<Taxonomy> searchTaxonomyList(int id);

  Taxonomy searchCarousel(int id);

  boolean deleteTaxonomy(Taxonomy taxonomy);

  List<Taxonomy> findTaxonomyByUser(String user);
}
