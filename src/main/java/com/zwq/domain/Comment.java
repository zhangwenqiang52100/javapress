package com.zwq.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Lancer on 2017/5/11.
 */
@Entity
@Table(name = "comment", catalog = "javapress")
public class Comment {

  private int id;
  private String commentName;
  private String commentEmail;
  private String content;
  private String date;
  private User user;
  private Article article;

  @ManyToOne(fetch = FetchType.LAZY)
  public User getUser() {
    return user;
  }

  @JsonIgnore
  public void setUser(User user) {
    this.user = user;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCommentName() {
    return commentName;
  }

  public void setCommentName(String commentName) {
    this.commentName = commentName;
  }

  public String getCommentEmail() {
    return commentEmail;
  }

  public void setCommentEmail(String commentEmail) {
    this.commentEmail = commentEmail;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @ManyToOne(fetch = FetchType.LAZY)

  public Article getArticle() {
    return article;
  }

  @JsonIgnore
  public void setArticle(Article article) {
    this.article = article;
  }

  public Comment(int id) {
    this.id = id;
  }

  public Comment() {
  }
}
