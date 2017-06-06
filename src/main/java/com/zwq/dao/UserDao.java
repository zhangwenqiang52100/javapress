package com.zwq.dao;

import com.zwq.domain.User;
import java.util.List;
import java.util.Map;

/**
 * Created by Lancer on 2017/3/24.
 */
public interface UserDao {

  Map<String, String> getBaseInfo(String userName);

  boolean updatePassword(User user);

  boolean addUser(User user);

  boolean checkUserName(String userName);

  User findUserByUserName(String userName);

  List<User> getAllUser();

  boolean deleteUser(User user);

  int findUserCount();

  List<User> findUserByPage(int begin, int pageSize);

  void rootDeleteUsersById(String s);
}
