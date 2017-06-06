package com.zwq.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zwq.domain.OptionInfo;
import com.zwq.domain.User;
import com.zwq.service.OptionService;
import com.zwq.util.FileOrByte;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

/**
 * Created by Archer on 2016/12/10.
 */
public class OptionAction extends ActionSupport implements ModelDriven<OptionInfo> {

  private OptionInfo optionInfo = new OptionInfo();
  private OptionService optionService;
  User user = (User) ActionContext.getContext().getSession().get("user");

  public void setOptionService(OptionService optionService) {
    this.optionService = optionService;
  }

  @Override
  public OptionInfo getModel() {
    return optionInfo;
  }

  /**
   * 更新网站基本设置信息
   */
  public String updataWebOption() throws IOException {
    OptionInfo oldOption = optionService.getWebOption(user.getId());
    if (optionInfo.getLogo()==null) {
      optionInfo.setLogoPath(oldOption.getLogoPath());
    }
    if (optionInfo.getLogo() != null) {
      String filePath = optionInfo.getLogo().getPath();//图片缓存路径
      File upload = new File(filePath);
      // 将商品图片上传到服务器上.
      // 获得上传图片的服务器端路径.
      String path = ServletActionContext.getServletContext().getRealPath(
        "/upload");
      // 创建文件类型对象:
      File diskFile = new File(path + "//" + user.getUserName() + "//" + optionInfo.getLogoName());
      // 文件上传:
      FileUtils.copyFile(upload, diskFile);
      optionInfo.setLogoPath("upload/" + user.getUserName() + "//" + optionInfo.getLogoName());
    }

    boolean flag = optionService.saveOption(optionInfo);
    if (flag == true) {
      ActionContext.getContext().getSession().put("optionInfo", optionInfo);
      return "update";
    } else {
      return INPUT;
    }
  }


  /**
   * 需要把option的值给optioninfo（最佳方案
   * ）
   */
  public String initOption() {
    OptionInfo optionInfo = optionService.getWebOption(user.getId());
    ActionContext.getContext().getSession().put("optionInfo", optionInfo);
    return SUCCESS;
  }

  public String getWebOption() {
    OptionInfo optionInfo = optionService.getWebOption(user.getId());
    ActionContext.getContext().getSession().put("optionInfo", optionInfo);
    return "optionInfo";
  }

}
