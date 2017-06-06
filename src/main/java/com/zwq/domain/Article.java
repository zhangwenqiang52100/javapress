package com.zwq.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.crypto.Data;

/**
 * Created by Lancer on 2017/4/25.
 */
@Entity
@Table(name = "article", catalog = "javapress")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Article implements Serializable {

  private int id;
  private String title;
  private boolean isvouch;
  private String note;
  private String content;
  private String keywords;
  private Date datetime;
  private String authour;
  private String viewCount;
  private String commentCount;
  private File aimg;
  private String imgName;
  private String imgPath;

  private Cate cate;
  private User user;
  private Taxonomy taxonomy;
  private Set<Comment> commentSet = new HashSet<>();

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "article")
  public Set<Comment> getCommentSet() {
    return commentSet;
  }
  @JsonIgnore
  public void setCommentSet(Set<Comment> commentSet) {
    this.commentSet = commentSet;
  }

  @Transient
  public File getAimg() {
    return aimg;
  }

  public void setAimg(File aimg) {
    this.aimg = aimg;
  }

  public String getImgName() {
    return imgName;
  }

  public void setImgName(String imgName) {
    this.imgName = imgName;
  }

  public String getImgPath() {
    return imgPath;
  }

  public void setImgPath(String imgPath) {
    this.imgPath = imgPath;
  }

  @ManyToOne(fetch = FetchType.LAZY)

  public Taxonomy getTaxonomy() {
    return taxonomy;
  }

  @JsonIgnore
  public void setTaxonomy(Taxonomy taxonomy) {
    this.taxonomy = taxonomy;
  }

  @ManyToOne(fetch = FetchType.LAZY)

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


  public boolean isIsvouch() {
    return isvouch;
  }

  public void setIsvouch(boolean isvouch) {
    this.isvouch = isvouch;
  }


  public String getNote() {
    return note;
  }

  public String getViewCount() {
    return viewCount;
  }

  public void setViewCount(String viewCount) {
    this.viewCount = viewCount;
  }


  public String getCommentCount() {
    return commentCount;
  }

  public void setCommentCount(String commentCount) {
    this.commentCount = commentCount;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public String getKeywords() {
    return keywords;
  }

  public void setKeywords(String keywords) {
    this.keywords = keywords;
  }

  public Date getDatetime() {
    return datetime;
  }

  public void setDatetime(Date datetime) {
    this.datetime = datetime;
  }

  public String getAuthour() {
    return authour;
  }

  public void setAuthour(String authour) {
    this.authour = authour;
  }

  @ManyToOne(fetch = FetchType.LAZY)

  public Cate getCate() {
    return cate;
  }
  public void setCate(Cate cate) {
    this.cate = cate;
  }

  public Article() {
  }

  public Article(int id) {
    this.id = id;
  }
}
