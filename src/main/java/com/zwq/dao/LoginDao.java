package com.zwq.dao;

import com.zwq.domain.User;

/**
 * Created by Archer on 2016/12/6.
 */
public interface LoginDao {

    User findByNameAndPass(User user);
}
