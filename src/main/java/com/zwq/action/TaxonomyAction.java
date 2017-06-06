package com.zwq.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zwq.domain.Taxonomy;
import com.zwq.domain.User;
import com.zwq.service.TaxonomyService;
import java.util.List;
import sun.plugin.javascript.navig.LinkArray;

/**
 * Created by Lancer on 2017/4/15.
 */
public class TaxonomyAction extends ActionSupport implements ModelDriven<Taxonomy> {

  private Taxonomy taxonomy = new Taxonomy();
  private TaxonomyService taxonomyService;
  User user = (User) ActionContext.getContext().getSession().get("user");

  public void setTaxonomyService(TaxonomyService taxonomyService) {
    this.taxonomyService = taxonomyService;
  }

  @Override
  public Taxonomy getModel() {
    return taxonomy;
  }

  /**
   * 更新和添加栏目
   */
  public String addOrUpdate() {
    boolean flag = taxonomyService.addOrUpdate(taxonomy);
    if (flag) {
      return "addOrUpdateTaxonomy";
    }
    return INPUT;
  }

  /**
   * 查询全部栏目
   */
  public String searchTaxonomyList() {
    List<Taxonomy> taxonomyList = taxonomyService.searchTaxonomyList(user.getId());
    ActionContext.getContext().getValueStack().set("taxonomyList", taxonomyList);
    return SUCCESS;
  }

  public String deleteTaxonomy() {
    if (taxonomy.getUser() == null || taxonomy.getUser().getId() != user.getId()) {
      return null;
    } else {
      boolean flag = taxonomyService.deleteTaxonomy(taxonomy);
    }
    return "deleteTaxonomy";
  }
}
