package com.zwq.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashSet;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by Lancer on 2017/4/14.
 */
@Entity
@Table(name = "taxonomy", catalog = "javapress")
public class Taxonomy implements Serializable {

  private int id;
  private String title;
  private String taxonomyDesc;
  private String isshow;
  private Set<Cate> cateSet;
  private Set<Article> articleSet = new HashSet<>();
  private User user;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "taxonomy")
  public Set<Article> getArticleSet() {
    return articleSet;
  }

  @JsonIgnore
  public void setArticleSet(Set<Article> articleSet) {
    this.articleSet = articleSet;
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

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "taxonomy")
  public Set<Cate> getCateSet() {
    return cateSet;
  }

  @JsonIgnore
  public void setCateSet(Set<Cate> cateSet) {
    this.cateSet = cateSet;
  }

  public String getTaxonomyDesc() {
    return taxonomyDesc;
  }

  public void setTaxonomyDesc(String taxonomyDesc) {
    this.taxonomyDesc = taxonomyDesc;
  }



  public String getIsshow() {
    return isshow;
  }

  public void setIsshow(String isshow) {
    this.isshow = isshow;
  }

  public Taxonomy() {
  }

}
