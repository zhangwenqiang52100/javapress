package com.zwq.dao;

import com.zwq.domain.OptionInfo;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Archer on 2016/12/11.
 */
public interface OptionDao {

  void saveOption(OptionInfo optionInfo);

  OptionInfo findById(int sid);

  OptionInfo getWebOption(int sid);
}
