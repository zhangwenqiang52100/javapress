package com.zwq.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Index;


/**
 * Created by Archer on 2016/12/5.
 */
@Entity
@Table(name = "users", catalog = "javapress")
public class User implements Serializable {

  private int id;
  private String userName;          //登陆名
  private String nickName;           //昵称
  private String realName;           //真实姓名
  private String password;          //密码
  private String salt;                //盐
  private String email;              //电子邮件
  private String email_status;       //邮件状态(是否认证等)
  private String mobile;                //电话

  public User() {
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Column(name = "userName")
  @Index(name = "userName")
  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getRealName() {
    return realName;
  }

  public void setRealName(String realName) {
    this.realName = realName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getEmail_status() {
    return email_status;
  }

  public void setEmail_status(String email_status) {
    this.email_status = email_status;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }
}
