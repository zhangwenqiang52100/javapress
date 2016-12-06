package com.zwq.service.impl;

import com.zwq.dao.LoginDao;
import com.zwq.domain.User;
import com.zwq.service.LoginService;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Archer on 2016/12/6.
 */
@Transactional
public class LoginServiceImpl implements LoginService {
    private LoginDao loginDao;

    public void setLoginDao(LoginDao loginDao) {
        this.loginDao = loginDao;
    }
    @Override
    public User login(User user) {
        System.out.println("执行了service");
        User user1= loginDao.findByNameAndPass(user);
        return user1;
    }
}
