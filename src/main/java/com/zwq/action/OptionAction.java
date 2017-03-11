package com.zwq.action;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.xml.internal.fastinfoset.stax.events.Util;
import com.zwq.domain.Option;
import com.zwq.domain.OptionEnum;
import com.zwq.domain.OptionInfo;
import com.zwq.service.OptionService;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Archer on 2016/12/10.
 */
public class OptionAction extends ActionSupport implements ModelDriven<OptionInfo> {

  private OptionInfo optionInfo = new OptionInfo();
  private OptionService optionService;

  public void setOptionService(OptionService optionService) {
    this.optionService = optionService;
  }

  @Override
  public OptionInfo getModel() {
    return optionInfo;
  }

  public String updataWebOption() {
    //需要先获取查找到的信息
    if ((Integer) optionInfo.getSid() != null) {
      optionInfo = optionService.findById(optionInfo.getSid());
    } else {
      optionService.saveOption(optionInfo);
      return ERROR;
    }
    return "update";
  }

  @Override
  public Collection<String> getActionErrors() {
    System.out.println("这是error");
    return super.getActionErrors();
  }

  @Override
  public Collection<String> getActionMessages() {
    System.out.println("这是messag");
    return super.getActionMessages();
  }

  @Override
  public Map<String, List<String>> getFieldErrors() {
    System.out.println("这是field");
    return super.getFieldErrors();
  }

  /**
   *
   * @return
   */
  public String getWebOption() {
    optionInfo = optionService.getWebOption(optionInfo.getSid());
    ActionContext.getContext().getSession().put("optionInfo", optionInfo);
    return SUCCESS;
  }

  public String findById() {
    return SUCCESS;
  }

  public String findAll() {
    return SUCCESS;
  }
}
