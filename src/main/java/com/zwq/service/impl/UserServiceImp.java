package com.zwq.service.impl;

import com.zwq.dao.UserDao;
import com.zwq.domain.PageBean;
import com.zwq.domain.User;
import com.zwq.service.UserService;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Lancer on 2017/3/24.
 */
@Transactional
public class UserServiceImp implements UserService {

  private UserDao userDao;

  public void setUserDao(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public Map<String, String> getBaseInfo(String userName) {
    return userDao.getBaseInfo(userName);
  }

  @Override
  public boolean updatePassword(User user) {
    return userDao.updatePassword(user);
  }

  @Override
  public boolean addUser(User user) {
    return userDao.addUser(user);
  }

  @Override
  public boolean checkUserName(String userName) {
    return userDao.checkUserName(userName);
  }

  @Override
  public User findUserByUserName(String userName) {
    return userDao.findUserByUserName(userName);
  }

  @Override
  public List<User> getAllUser() {
    return userDao.getAllUser();
  }

  @Override
  public boolean deleteUser(User user) {
    return userDao.deleteUser(user);
  }

  @Override
  public PageBean<User> findUserByPage(Integer currPage) {
    PageBean<User> pageBean=new PageBean<User>();
    //设置起始页面
    pageBean.setCurrPage(currPage);
    //设置页面大小
    int pageSize=5;
    pageBean.setPageSize(pageSize);
    //统计个数
    int totalCount=userDao.findUserCount();
    pageBean.setTotalCount(totalCount);
    //统计页面个数
    double tc=totalCount;
    Double num=Math.ceil(tc/pageSize);
    pageBean.setTotalpage(num.intValue());
    //开始页面
    int begin=((currPage-1)*pageSize);
    List<User> list= userDao.findUserByPage(begin,pageSize);
    pageBean.setList(list);
    return pageBean;
  }

  @Override
  public void rootDeleteUsersById(String s) {
    userDao.rootDeleteUsersById(s);
  }
}
