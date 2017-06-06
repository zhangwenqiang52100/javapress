package com.zwq.dao;

import com.zwq.domain.OptionInfo;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Archer on 2016/12/11.
 */
public interface OptionDao {

  boolean saveOption(OptionInfo optionInfo) throws IOException;

  OptionInfo findById(int sid);

  OptionInfo getWebOption(int id);
  OptionInfo findOptionByUsername(String username);

  OptionInfo findOptionByUser(String user);
}
