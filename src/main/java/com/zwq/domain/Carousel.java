package com.zwq.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.awt.Image;
import java.io.File;
import java.io.Serializable;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by Lancer on 2017/4/10.
 */
@Entity
@Table(name = "carousel", catalog = "javapress")
public class Carousel implements Serializable {

  private int id;
  private String title;
  private String url;
  private String note;
  private User user;
  private File image;
  private String imageName;
  private String imagePath;

  @Transient
  public File getImage() {
    return image;
  }

  public void setImage(File image) {
    this.image = image;
  }

  public String getImageName() {
    return imageName;
  }

  public void setImageName(String imageName) {
    this.imageName = imageName;
  }

  public String getImagePath() {
    return imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
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

  @Column(name = "link_url")
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

}
