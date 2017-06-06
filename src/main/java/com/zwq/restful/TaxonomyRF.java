package com.zwq.restful;

import com.zwq.domain.Taxonomy;
import java.util.List;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Lancer on 2017/5/3.
 */
public interface TaxonomyRF {

  List<Taxonomy> findTaxonomyByUser(String user);
}
