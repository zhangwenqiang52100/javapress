package com.zwq.domain;

import java.io.File;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.Index;


/**
 * Created by Archer on 2016/12/5.
 */
@Entity
@Table(name = "users", catalog = "javapress")
public class User implements Serializable {

  private int id;
  private String userName;          //登陆名
  private String password;          //密码
  private String email;              //电子邮件
  private String mobile;                //电话
  private String states;            //国籍
  private String zip;                   //邮编
  private String comments;                //備註
  private String date;                     //出生日期
  private File file;             //头像文件
  private String filePath;          //头像路径
  private String fileName;                //头像图片名字
  private String isRoot;

  public String getIsRoot() {
    return isRoot;
  }


  public void setIsRoot(String isRoot) {
    this.isRoot = isRoot;
  }

  private OptionInfo optionInfo;
  private Set<Carousel> carouselSet = new HashSet<>();
  private Set<Taxonomy> taxonomySet = new HashSet<>();
  private Set<Cate> cateSet = new HashSet<>();
  private Set<Article> articleSet = new HashSet<>();
  private Set<Comment> commentSet = new HashSet<>();

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
  public Set<Comment> getCommentSet() {
    return commentSet;
  }

  public void setCommentSet(Set<Comment> commentSet) {
    this.commentSet = commentSet;
  }

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
  public Set<Article> getArticleSet() {
    return articleSet;
  }

  public void setArticleSet(Set<Article> articleSet) {
    this.articleSet = articleSet;
  }

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
  public Set<Cate> getCateSet() {
    return cateSet;
  }

  public void setCateSet(Set<Cate> cateSet) {
    this.cateSet = cateSet;
  }

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
  public Set<Taxonomy> getTaxonomySet() {
    return taxonomySet;
  }

  public void setTaxonomySet(Set<Taxonomy> taxonomySet) {
    this.taxonomySet = taxonomySet;
  }

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
  public Set<Carousel> getCarouselSet() {
    return carouselSet;
  }

  public void setCarouselSet(Set<Carousel> carouselSet) {
    this.carouselSet = carouselSet;
  }


  @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
  public OptionInfo getOptionInfo() {
    return optionInfo;
  }

  public void setOptionInfo(OptionInfo optionInfo) {
    this.optionInfo = optionInfo;
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getStates() {
    return states;
  }

  public void setStates(String states) {
    this.states = states;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  @Transient
  public File getFile() {
    return file;
  }

  public void setFile(File file) {
    this.file = file;
  }

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public User(int id) {
    this.id = id;
  }

  public User() {

  }

}
