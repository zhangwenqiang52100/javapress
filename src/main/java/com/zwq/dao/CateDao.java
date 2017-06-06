package com.zwq.dao;

import com.zwq.domain.Cate;
import com.zwq.domain.Taxonomy;
import java.util.List;

/**
 * Created by Lancer on 2017/4/19.
 */
public interface CateDao {

  List<Cate> searchCateList(int id);

  void addOrUpdate(Cate cate);

  boolean deleteCate(Cate cate);

  List<Cate> findCateByTaxonomyIdAndUser(String user, int tid);

  Cate findTaxonomyByCateId(int cateId, int uid);

  int findAdminCateCount(String userName,String keywords);

  List<Cate> findAdminCateByPage(int begin, int pageSize, String userName,String keywords);
}
