package com.zwq.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zwq.domain.Carousel;
import com.zwq.domain.User;
import com.zwq.service.CarouselService;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.JakartaMultiPartRequest;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;

/**
 * Created by Lancer on 2017/4/12.
 */
public class CarouselAction extends ActionSupport implements ModelDriven<Carousel> {

  private Carousel carousel = new Carousel();
  private CarouselService carouselService;
  User user = (User) ActionContext.getContext().getSession().get("user");


  public void setCarouselService(CarouselService carouselService) {
    this.carouselService = carouselService;
  }

  @Override
  public Carousel getModel() {
    return carousel;
  }

  /**
   * 增加修改首页轮播
   */
  public String addOrUpdateCarouse() throws IOException {
    Carousel oldAcarousel = carouselService.editCarousel(carousel.getId(), user.getId());
    if (carousel.getImage() == null) {
      carousel.setImagePath(oldAcarousel.getImagePath());
    }
    if (carousel.getImage() != null) {
      String filePath = carousel.getImage().getPath();//图片缓存路径
      File upload = new File(filePath);
      // 将商品图片上传到服务器上.
      // 获得上传图片的服务器端路径.
      String path = ServletActionContext.getServletContext().getRealPath(
        "/upload");
      // 创建文件类型对象:
      File diskFile = new File(
        path + "//" + user.getUserName() + "//carousel//" + carousel.getImageName());
      // 文件上传:
      FileUtils.copyFile(upload, diskFile);
      carousel
        .setImagePath("upload/" + user.getUserName() + "//carousel//" + carousel.getImageName());
    }
    carouselService.addCarousel(carousel);
    return SUCCESS;
  }

  /**
   * 增加页面跳转
   */
  public String addCarousel() {
    return "addCarousel";
  }

  /**
   * 查询首页轮播列表
   */
  public String searchCarouselList() throws IOException {
    List<Carousel> carouselList = carouselService.searchCarouselList(user.getId());
    ActionContext.getContext().getValueStack().set("carouselList", carouselList);
    return "carouselList";
  }

  /**
   * 查询首页轮播
   */
  public String editCarousel() {
    Carousel editcarousel = carouselService.editCarousel(carousel.getId(), user.getId());
    ActionContext.getContext().getValueStack().set("editcarousel", editcarousel);
    return "editCarousel";
  }

  public String deleteCarousel() throws IOException {
    if (carousel.getUser() == null || carousel.getUser().getId() != user.getId()) {
      return null;
    } else {
      boolean flag = carouselService.deleteCarousel(carousel);
    }
    return "deleteCarousel";
  }
}
