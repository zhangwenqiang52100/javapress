package com.zwq.action;

import static com.opensymphony.xwork2.Action.SUCCESS;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.zwq.domain.Article;
import com.zwq.domain.Cate;
import com.zwq.domain.PageBean;
import com.zwq.domain.User;
import com.zwq.service.ArticleService;
import com.zwq.service.CateService;
import com.zwq.service.TaxonomyService;
import com.zwq.util.DateUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

/**
 * Created by Lancer on 2017/4/25.
 */
public class ArticleAction implements ModelDriven<Article> {

  private Article article = new Article();
  private Cate cate = new Cate();
  User user = (User) ActionContext.getContext().getSession().get("user");
  private ArticleService articleService;
  private CateService cateService;
  private TaxonomyService taxonomyService;
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

  public void setArticleService(ArticleService articleService) {
    this.articleService = articleService;
  }

  @Override
  public Article getModel() {
    return article;
  }

  /**
   * 普通用户文章分页管理和关键字搜索.
   */
  public String getArticleList() {
    List<Cate> cateList = cateService.searchCateList(user.getId());
    ActionContext.getContext().getValueStack().set("cateList", cateList);
    HttpServletRequest request = ServletActionContext.getRequest();
    String keywords = request.getParameter("keywords");
    User user = (User) request.getSession().getAttribute("user");
    if (user != null) {
      PageBean<Article> pageBean = articleService
        .findArticleByPage(currPage, user.getUserName(), keywords);
      ActionContext.getContext().getValueStack().set("articleList", pageBean.getList());
      ActionContext.getContext().getValueStack().push(pageBean);
    }
    return SUCCESS;
  }

  /**
   *
   * @return
   */
  public String editArticle() {
    List<Cate> cateList = cateService.searchCateList(user.getId());
    ActionContext.getContext().getValueStack().set("cateList", cateList);
    return "editArticle";
  }

  public String saveOrUpdateArticle() throws IOException {
    LocalTime time = LocalTime.now();
    article.setDatetime(DateUtil.LocalTimeToUdate(time));
    Article oldArticle = articleService
      .searchArticle(user.getUserName(), null,
        String.valueOf(article.getId()));
    if (article.getAimg() == null && oldArticle != null) {
      article.setImgPath(oldArticle.getImgPath());
    }
    if (article.getAimg() != null) {
      String filePath = article.getAimg().getPath();//图片缓存路径
      File upload = new File(filePath);
      // 将商品图片上传到服务器上.
      // 获得上传图片的服务器端路径.
      String path = ServletActionContext.getServletContext().getRealPath(
        "/upload");
      // 创建文件类型对象:
      File diskFile = new File(
        path + "//" + user.getUserName() + "//article//" + article.getImgName());
      // 文件上传:
      FileUtils.copyFile(upload, diskFile);
      article.setImgPath("upload/" + user.getUserName() + "//article//" + article.getImgName());
    }
    Cate newCate = cateService.findTaxonomyByCateId(article.getCate().getId(), user.getId());
    article.setTaxonomy(newCate.getTaxonomy());
    boolean flag = articleService.saveOrUpdateArticle(article);
    return "saveOrUpdate";
  }

  public String deleteArticle() {
    if (article.getUser() == null || article.getUser().getId() != user.getId()
      || article.getCate().getId() <= 0) {
      return null;
    } else {
      boolean flag = articleService.deleteArticle(article);
    }

    return null;
  }

  public String findArticleById() {
    List<Cate> cateList = cateService.searchCateList(user.getId());
    ActionContext.getContext().getValueStack().set("cateList", cateList);
    article = articleService.findArticleById(article.getId(), user.getId());
    return "updateArticle";
  }

  /**
   * 普通用户通过cate加载列表
   */
  public String searchArticleByCate() {
    HttpServletRequest request = ServletActionContext.getRequest();
    String cateId = request.getParameter("cateId");
    List<Article> articleList = articleService.searchArticleByCate(user.getUserName(), cateId);
    ActionContext.getContext().getValueStack().set("articleList", articleList);
    return SUCCESS;
  }


  //批量是删除文章
  public String deleteArticlesById() {
    HttpServletRequest request = ServletActionContext.getRequest();
    String ids = request.getParameter("ids");
    String[] id = ids.split(",");
    for (String s : id) {
      articleService.deleteArticleById(s, String.valueOf(user.getId()));
    }
    return SUCCESS;
  }

}
