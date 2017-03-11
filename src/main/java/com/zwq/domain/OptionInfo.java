package com.zwq.domain;

import java.io.Serializable;
import java.sql.Blob;
import javax.persistence.*;


/**
 * Created by Archer on 2016/12/11.
 */
@Entity
@Table(name = "option", catalog = "javapress")
public class OptionInfo implements Serializable {

  private int sid;
  private String stitle;              //网站标题
  private Blob slogo;                             //网站logo
  private String surl;                   //网站域名
  private String sentitle;                 //附加标题
  private String skeywords;               //网站关键字
  private String sdescription;            //网站描述
  private String s_name;                 //联系人
  private String s_phone;                 ///联系手机
  private String s_tel;                     //联系电话
  private String s_fax;                  //传真
  private String s_qq;                    //qq
  private String s_qqu;                  //qq群
  private String s_email;                   //电子邮件
  private String s_address;               //地址
  private String scopyright;            //底部信息

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public int getSid() {
    return sid;
  }

  public void setSid(int sid) {
    this.sid = sid;
  }

  public String getStitle() {
    return stitle;
  }

  public void setStitle(String stitle) {
    this.stitle = stitle;
  }

  public Blob getSlogo() {
    return slogo;
  }

  public void setSlogo(Blob slogo) {
    this.slogo = slogo;
  }

  public String getSurl() {
    return surl;
  }

  public void setSurl(String surl) {
    this.surl = surl;
  }

  public String getSentitle() {
    return sentitle;
  }

  public void setSentitle(String sentitle) {
    this.sentitle = sentitle;
  }

  public String getSkeywords() {
    return skeywords;
  }

  public void setSkeywords(String skeywords) {
    this.skeywords = skeywords;
  }

  public String getSdescription() {
    return sdescription;
  }

  public void setSdescription(String sdescription) {
    this.sdescription = sdescription;
  }

  public String getS_name() {
    return s_name;
  }

  public void setS_name(String s_name) {
    this.s_name = s_name;
  }

  public String getS_phone() {
    return s_phone;
  }

  public void setS_phone(String s_phone) {
    this.s_phone = s_phone;
  }

  public String getS_tel() {
    return s_tel;
  }

  public void setS_tel(String s_tel) {
    this.s_tel = s_tel;
  }

  public String getS_fax() {
    return s_fax;
  }

  public void setS_fax(String s_fax) {
    this.s_fax = s_fax;
  }

  public String getS_qq() {
    return s_qq;
  }

  public void setS_qq(String s_qq) {
    this.s_qq = s_qq;
  }

  public String getS_qqu() {
    return s_qqu;
  }

  public void setS_qqu(String s_qqu) {
    this.s_qqu = s_qqu;
  }

  public String getS_email() {
    return s_email;
  }

  public void setS_email(String s_email) {
    this.s_email = s_email;
  }

  public String getS_address() {
    return s_address;
  }

  public void setS_address(String s_address) {
    this.s_address = s_address;
  }

  public String getScopyright() {
    return scopyright;
  }

  public void setScopyright(String scopyright) {
    this.scopyright = scopyright;
  }

  @Override
  public String toString() {
    return "OptionInfo{" +
        "stitle='" + stitle + '\'' +
        ", slogo=" + slogo +
        ", surl='" + surl + '\'' +
        ", sentitle='" + sentitle + '\'' +
        ", skeywords='" + skeywords + '\'' +
        ", sdescription='" + sdescription + '\'' +
        ", s_name='" + s_name + '\'' +
        ", s_phone='" + s_phone + '\'' +
        ", s_tel='" + s_tel + '\'' +
        ", s_fax='" + s_fax + '\'' +
        ", s_qq='" + s_qq + '\'' +
        ", s_qqu='" + s_qqu + '\'' +
        ", s_email='" + s_email + '\'' +
        ", s_address='" + s_address + '\'' +
        ", scopyright='" + scopyright + '\'' +
        '}';
  }

  public OptionInfo() {
  }

  public OptionInfo(String stitle, Blob slogo, String surl, String sentitle, String skeywords,
      String sdescription, String s_name, String s_phone, String s_tel, String s_fax, String s_qq,
      String s_qqu, String s_email, String s_address, String scopyright) {
    this.stitle = stitle;
    this.slogo = slogo;
    this.surl = surl;
    this.sentitle = sentitle;
    this.skeywords = skeywords;
    this.sdescription = sdescription;
    this.s_name = s_name;
    this.s_phone = s_phone;
    this.s_tel = s_tel;
    this.s_fax = s_fax;
    this.s_qq = s_qq;
    this.s_qqu = s_qqu;
    this.s_email = s_email;
    this.s_address = s_address;
    this.scopyright = scopyright;
  }
}
