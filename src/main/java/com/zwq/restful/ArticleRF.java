package com.zwq.restful;

import com.zwq.domain.Article;
import java.util.List;

/**
 * Created by Lancer on 2017/5/4.
 */
public interface ArticleRF {

  List<Article> findArticlesByUser(String user);

  List<Article> searchArticlesByKeyword(String user, String keyword);

  List<Article> recommendArticle(String user);

  Article searchArticle(String user, String cateId, String articleId);

  List<Article> articleListByCateOrTanxonomy(String user,String tid, String cid);

  void articleAddViewCount(String user, String aid);
}
