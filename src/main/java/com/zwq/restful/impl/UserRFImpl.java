package com.zwq.restful.impl;

import com.zwq.restful.UserRF;
import com.zwq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Lancer on 2017/5/11.
 */
@Component
public class UserRFImpl implements UserRF {

  @Autowired
  private UserService userService;
  @Override
  public boolean checkUserName(String userName) {
    return userService.checkUserName(userName);
  }
}
