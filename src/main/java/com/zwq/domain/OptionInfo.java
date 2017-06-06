package com.zwq.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.File;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Blob;
import java.util.Arrays;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.Type;


/**
 * Created by Archer on 2016/12/11.
 */
@Entity
@Table(name = "option", catalog = "javapress")
public class OptionInfo implements Serializable {

  private int id;
  private String title;              //网站标题
  private String keywords;               //网站关键字
  private String description;            //网站描述
  private String name;                 //联系人
  private String phone;                 ///联系手机
  private String tel;                     //联系电话
  private String fax;                  //传真
  private String qq;                    //qq
  private String email;                   //电子邮件
  private String address;               //地址
  private String copyright;            //底部信息
  private User user;
  private String logoName;
  private String logoPath;
  private File logo;

  public String getLogoPath() {
    return logoPath;
  }

  public void setLogoPath(String logoPath) {
    this.logoPath = logoPath;
  }

  public String getLogoName() {
    return logoName;
  }

  public void setLogoName(String logoName) {
    this.logoName = logoName;
  }

  //这是此注解后该属性不会数据持久化也是本例要说明的注解
  @Transient
  public File getLogo() {
    return logo;
  }

  public void setLogo(File logo) {
    this.logo = logo;
  }

  @OneToOne
  public User getUser() {
    return user;
  }

  @JsonIgnore
  public void setUser(User user) {
    this.user = user;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getKeywords() {
    return keywords;
  }

  public void setKeywords(String keywords) {
    this.keywords = keywords;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String getFax() {
    return fax;
  }

  public void setFax(String fax) {
    this.fax = fax;
  }

  public String getQq() {
    return qq;
  }

  public void setQq(String qq) {
    this.qq = qq;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getCopyright() {
    return copyright;
  }

  public void setCopyright(String copyright) {
    this.copyright = copyright;
  }

  public OptionInfo() {
  }

  public OptionInfo(int id, String title,
    String keywords, String description, String name, String phone,
    String tel, String fax, String qq, String email, String address, String copyright,
    User user) {
    this.id = id;
    this.title = title;
    this.keywords = keywords;
    this.description = description;
    this.name = name;
    this.phone = phone;
    this.tel = tel;
    this.fax = fax;
    this.qq = qq;
    this.email = email;
    this.address = address;
    this.copyright = copyright;
    this.user = user;
  }
}
