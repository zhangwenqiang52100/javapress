package com.zwq.resource.api;

import com.zwq.domain.Article;
import com.zwq.domain.Carousel;
import com.zwq.domain.Cate;
import com.zwq.domain.Comment;
import com.zwq.domain.OptionInfo;
import com.zwq.domain.Taxonomy;
import com.zwq.restful.ArticleRF;
import com.zwq.restful.CateRF;
import com.zwq.restful.CommentRF;
import com.zwq.restful.OptionRF;
import com.zwq.restful.TaxonomyRF;
import com.zwq.restful.UserRF;
import com.zwq.restful.carouselRF;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Lancer on 2017/4/21.
 */
@Path("/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CateRestful {

  @Autowired
  private TaxonomyRF taxonomyRF;
  @Autowired
  private CateRF cateRF;
  @Autowired
  private ArticleRF articleRF;
  @Autowired
  private OptionRF optionRF;
  @Autowired
  private carouselRF carouselRF;
  @Autowired
  private UserRF userRF;
  @Autowired
  private CommentRF commentRF;

  /**
   * 获取网站设置
   *
   * @param user 用户名
   * @return 返回网站设置
   */
  @GET
  @Path("/{user}/option")
  public OptionInfo optionInfoRestful(@PathParam("user") String user) {
    return optionRF.findOptionByUser(user);
  }

  /**
   * 获取栏目
   *
   * @return 返回栏目
   */
  @GET
  @Path("/{user}/taxonomy")
  public List<Taxonomy> taxonomyRestful(@PathParam("user") String user) {
    return taxonomyRF.findTaxonomyByUser(user);
  }

  /**
   * 获取分类列表
   */
  @GET
  @Path("/{user}/cates/{tid}")
  public List<Cate> catesRestful(@PathParam("user") String user, @PathParam("tid") int tid) {
    return cateRF.findCateByTaxonomyIdAndUser(user, tid);
  }


  /**
   * 获取文章(通过关键字)
   */
  @GET
  @Path("/{user}/articles")
  public List<Article> articlesRestful(@PathParam("user") String user,
    @QueryParam("keyword") String keyword) {
    return articleRF.searchArticlesByKeyword(user, keyword);
  }

  /**
   * 获取推荐文章
   */
  @GET
  @Path("/{user}/recommendArticle")
  public List<Article> recommendArticleRestful(@PathParam("user") String user) {
    return articleRF.recommendArticle(user);
  }

  @GET
  @Path("/{user}/articleListByCateOrTanxonomy")
  public List<Article> articleListByCateOrTanxonomyRestful(@PathParam("user") String user,
    @QueryParam("tid") String tid, @QueryParam("cid") String cid) {
    return articleRF.articleListByCateOrTanxonomy(user, tid, cid);
  }

  /**
   * 获取详细的文章信息用於显示文章详情页。
   */
  @GET
  @Path("/{user}/article")
  public Article searchArticleRestful(@PathParam("user") String user,
    @QueryParam("cateid") String cateId, @QueryParam("articleid") String articleId) {
    return articleRF.searchArticle(user, cateId, articleId);
  }

  /**
   * 获取首页轮播
   */
  @GET
  @Path("/{user}/carouselList")
  public List<Carousel> carouselRestful(@PathParam("user") String user) {
    return carouselRF.searchCarouseListByUser(user);
  }

  /**
   * 检验用户名是否存在
   */
  @GET
  @Path("/checkusername")
  public boolean checkUserNameRestful(@QueryParam("username") String userName) {
    return userRF.checkUserName(userName);
  }

  @GET
  @Path("/{user}/comment/{aid}")
  public List<Comment> commentList(@PathParam("user") String user, @PathParam("aid") String aid) {
    return commentRF.getAllComment(user, aid);
  }

  /**
   * 添加评论。
   */
  @PUT
  @Path("/{user}/comments/{aid}/")
  public Comment addComment(@PathParam("user") String user, @PathParam("aid") String aid
    , Comment comment) {
    return commentRF.addComment(user, aid, comment);
  }

  /**
   * 获取最后评论的一篇文章
   */
  @GET
  @Path("/{user}/latestCommentArticle/")
  public Article latestCommentArticle(@PathParam("user") String user) {
    return commentRF.latestCommentArticle(user);
  }

  @PUT
  @Path("/{user}/articleaddviewcount/{aid}")
  public void articleaddviewcount(@PathParam("user") String user, @PathParam("aid") String aid) {
    articleRF.articleAddViewCount(user, aid);
  }

}
