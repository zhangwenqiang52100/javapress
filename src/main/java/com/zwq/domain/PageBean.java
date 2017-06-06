package com.zwq.domain;

import java.util.List;

/**
 * Created by Lancer on 2017/5/13.
 */
public class PageBean<T> {

  private int currPage;//当前页数
  private int pageSize;//每页显示记录数
  private int totalCount;//总记录数
  private int totalpage;//总页数
  private List<T> list;//每页显示的数据

  public int getCurrPage() {
    return currPage;
  }

  public void setCurrPage(int currPage) {
    this.currPage = currPage;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public int getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
  }

  public int getTotalpage() {
    return totalpage;
  }

  public void setTotalpage(int totalpage) {
    this.totalpage = totalpage;
  }

  public List<T> getList() {
    return list;
  }

  public void setList(List<T> list) {
    this.list = list;
  }
}
