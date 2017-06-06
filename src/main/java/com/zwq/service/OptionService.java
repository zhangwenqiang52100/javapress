package com.zwq.service;

import com.zwq.domain.OptionInfo;
import java.io.IOException;

/**
 * Created by Archer on 2016/12/11.
 */
public interface OptionService {

  boolean saveOption(OptionInfo optionInfo) throws IOException;

  OptionInfo findById(int sid);

  OptionInfo getWebOption(int id);

  OptionInfo findOptionByUser(String user);
}
