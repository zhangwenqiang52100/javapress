package com.zwq.restful.impl;

import com.zwq.domain.OptionInfo;
import com.zwq.restful.OptionRF;
import com.zwq.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Lancer on 2017/5/5.
 */
@Component
public class OptionRFImpl implements OptionRF {

  @Autowired
  private OptionService optionService;

  @Override
  public OptionInfo findOptionByUser(String user) {
    return optionService.findOptionByUser(user);
  }
}
