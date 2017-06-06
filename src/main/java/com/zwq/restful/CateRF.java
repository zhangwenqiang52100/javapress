package com.zwq.restful;

import com.zwq.domain.Cate;
import java.util.List;

/**
 * Created by Lancer on 2017/5/3.
 */
public interface CateRF {

  List<Cate> findCateByTaxonomyIdAndUser(String user, int tid);
}
