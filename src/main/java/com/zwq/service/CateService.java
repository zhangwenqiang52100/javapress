package com.zwq.service;

import com.zwq.domain.Cate;
import com.zwq.domain.PageBean;
import com.zwq.domain.Taxonomy;
import java.util.List;

/**
 * Created by Lancer on 2017/4/19.
 */
public interface CateService {

  List<Cate> searchCateList(int id);

  void addOrUpdate(Cate cate);

  boolean deleteCate(Cate cate);

  List<Cate> findCateByTaxonomyIdAndUser(String user, int tid);

  Cate findTaxonomyByCateId(int cateId, int uid);

  PageBean<Cate> findCateByPage(Integer currPage, String userName ,String keywords);
}
