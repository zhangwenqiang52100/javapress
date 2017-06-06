package com.zwq.restful;

import com.zwq.domain.OptionInfo;

/**
 * Created by Lancer on 2017/5/5.
 */
public interface OptionRF {

  OptionInfo findOptionByUser(String user);
}
