package com.zwq.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zwq.domain.Cate;
import com.zwq.domain.PageBean;
import com.zwq.domain.Taxonomy;
import com.zwq.domain.User;
import com.zwq.service.CateService;
import com.zwq.service.TaxonomyService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 * Created by Lancer on 2017/4/19.
 */
public class CateAction extends ActionSupport implements ModelDriven<Cate> {

  private Taxonomy taxonomy = new Taxonomy();
  private Cate cate = new Cate();
  private List<Taxonomy> taxonomyList = new ArrayList<>();
  private CateService cateService;
  private TaxonomyService taxonomyService;
  User user = (User) ActionContext.getContext().getSession().get("user");
  private Integer currPage = 1;

  public void setCurrPage(Integer currPage) {
    this.currPage = currPage;
  }

  public void setTaxonomyService(TaxonomyService taxonomyService) {
    this.taxonomyService = taxonomyService;
  }

  public void setCateService(CateService cateService) {
    this.cateService = cateService;
  }

  /**
   * 添加和修改分类
   */
  public String addOrUpdateCate() {
    cateService.addOrUpdate(cate);
    taxonomyList = taxonomyService.searchTaxonomyList(user.getId());
    ActionContext.getContext().getValueStack().set("taxonomyList", taxonomyList);
    return "addOrUpdateCate";
  }

  /**
   * 普通用户查询分类列表
   */
  public String searchCateList() {
    List<Cate> cateList = cateService.searchCateList(user.getId());
    taxonomyList = taxonomyService.searchTaxonomyList(user.getId());
    HttpServletRequest request = ServletActionContext.getRequest();
    String keywords = request.getParameter("keywords");
    ActionContext.getContext().getValueStack().set("taxonomyList", taxonomyList);
    if (user != null) {
      PageBean<Cate> pageBean = cateService.findCateByPage(currPage, user.getUserName(),keywords);
      ActionContext.getContext().getValueStack().set("cateList", pageBean.getList());
      ActionContext.getContext().getValueStack().push(pageBean);
    }

   /* List<Cate> cateList = cateService.searchCateList(user.getId());
    taxonomyList = taxonomyService.searchTaxonomyList(user.getId());
    ActionContext.getContext().getValueStack().set("taxonomyList", taxonomyList);
    ActionContext.getContext().getValueStack().set("cateList", cateList);*/
    return SUCCESS;
  }

  public String deleteCate() {
    if (cate.getUser() == null || cate.getUser().getId() != user.getId()
      || cate.getTaxonomy().getId() <= 0) {
      return null;
    } else {
      boolean flag = cateService.deleteCate(cate);
    }
    return null;
  }

  @Override
  public Cate getModel() {
    return cate;
  }
}
