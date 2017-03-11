package com.zwq.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * Created by Archer on 2016/12/10.
 */
/*@Entity
@Table(name = "option", catalog = "javapress")*/
public class Option implements Serializable {

  private int id;
  private String option_key;
  private String option_value;

  public Option() {
  }

  /* @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)*/
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getOption_key() {
    return option_key;
  }

  public void setOption_key(String option_key) {
    this.option_key = option_key;
  }

  public String getOption_value() {
    return option_value;
  }

  public void setOption_value(String option_value) {
    this.option_value = option_value;
  }

  @Override
  public String toString() {
    return "Option{"
        + "id=" + id
        + ", option_key='" + option_key + '\''
        + ", option_value=" + option_value
        + '}';
  }

  public Option(String option_key, String option_value) {
    this.option_key = option_key;
    this.option_value = option_value;
  }
}
