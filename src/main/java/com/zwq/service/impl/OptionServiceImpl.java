package com.zwq.service.impl;

import com.zwq.dao.OptionDao;
import com.zwq.domain.OptionInfo;
import com.zwq.service.OptionService;
import java.io.IOException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Archer on 2016/12/11.
 */
@Transactional
@Component
public class OptionServiceImpl implements OptionService {

  private OptionDao optionDao;

  public void setOptionDao(OptionDao optionDao) {
    this.optionDao = optionDao;
  }

  @Override
  public boolean saveOption(OptionInfo optionInfo) throws IOException {
    return optionDao.saveOption(optionInfo);
  }

  @Override
  public OptionInfo findById(int sid) {

    return optionDao.findById(sid);
  }

  @Override
  public OptionInfo getWebOption(int id) {
    return optionDao.getWebOption(id);
  }

  @Override
  public OptionInfo findOptionByUser(String user) {
    return optionDao.findOptionByUser(user);
  }

}
