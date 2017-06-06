package com.zwq.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Created by Lancer on 2017/4/14.
 */
@Entity
@Table(name = "cate", catalog = "javapress")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Cate implements Serializable {

  private int id;
  private String title;
  private String keywords;
  private String cateDesc;
  private Taxonomy taxonomy;
  private User user;
  private Set<Article> articleSet = new HashSet<>();

  @ManyToOne(fetch = FetchType.EAGER)
  public User getUser() {
    return user;
  }

  @JsonIgnore
  public void setUser(User user) {
    this.user = user;
  }

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "cate" )
  public Set<Article> getArticleSet() {
    return articleSet;
  }

  @JsonIgnore
  public void setArticleSet(Set<Article> articleSet) {
    this.articleSet = articleSet;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  public Taxonomy getTaxonomy() {
    return taxonomy;
  }

  @JsonIgnore
  public void setTaxonomy(Taxonomy taxonomy) {
    this.taxonomy = taxonomy;
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

  public String getCateDesc() {
    return cateDesc;
  }

  public void setCateDesc(String cateDesc) {
    this.cateDesc = cateDesc;
  }

  public String getKeywords() {
    return keywords;
  }

  public void setKeywords(String keywords) {
    this.keywords = keywords;
  }


}
