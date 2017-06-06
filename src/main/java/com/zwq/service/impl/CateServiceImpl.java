package com.zwq.service.impl;

import com.zwq.dao.CateDao;
import com.zwq.domain.Cate;
import com.zwq.domain.PageBean;
import com.zwq.domain.Taxonomy;
import com.zwq.service.CateService;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Lancer on 2017/4/19.
 */
@Transactional
public class CateServiceImpl implements CateService {

  private CateDao cateDao;

  public void setCateDao(CateDao cateDao) {
    this.cateDao = cateDao;
  }

  @Override
  public List<Cate> searchCateList(int id) {
    return cateDao.searchCateList(id);
  }

  @Override
  public void addOrUpdate(Cate cate) {
    cateDao.addOrUpdate(cate);
  }

  @Override
  public boolean deleteCate(Cate cate) {
    return cateDao.deleteCate(cate);
  }

  @Override
  public List<Cate> findCateByTaxonomyIdAndUser(String user, int tid) {
    return cateDao.findCateByTaxonomyIdAndUser(user,tid);
  }

  @Override
  public Cate findTaxonomyByCateId(int cateId, int uid) {
    return cateDao.findTaxonomyByCateId(cateId,uid);
  }

  @Override
  public PageBean<Cate> findCateByPage(Integer currPage, String userName,String keywords) {
    PageBean<Cate> pageBean=new PageBean<>();
    //设置起始页面
    pageBean.setCurrPage(currPage);
    //设置页面大小
    int pageSize=5;
    pageBean.setPageSize(pageSize);
    //统计个数
    int totalCount=cateDao.findAdminCateCount(userName,keywords);
    pageBean.setTotalCount(totalCount);
    //统计页面个数
    double tc=totalCount;
    Double num=Math.ceil(tc/pageSize);
    pageBean.setTotalpage(num.intValue());
    //开始页面
    int begin=((currPage-1)*pageSize);
    List<Cate> list= cateDao.findAdminCateByPage(begin,pageSize,userName,keywords);
    pageBean.setList(list);
    return pageBean;
  }
}
