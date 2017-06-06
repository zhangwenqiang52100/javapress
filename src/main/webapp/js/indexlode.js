/**
 * Created by Lancer on 2017/5/3.
 */
$(function () {
  var taxonomy = $(".nav.navbar-nav.navbar-right");
  var articles = $("#articles");
  var contact = $("#contact");
  var more = $(".more");
  var recommend = $("#recommend");
  var content = $(".content");
  var carousel = $("#focusslide");
  var latestCommentArticle = $("#latestCommentArticle");
  var userUrl = window.location.href;
  var useradmin = userUrl.match("\/[A-Za-z]+\/");

  //自定义分页p标签
  var jPagesdiv = $("<div>");
  jPagesdiv.prop("className", "holder");
  articles.after(jPagesdiv);

  var jPages = function () {
    if ($(".holder").length > 0) {
    } else {
      jPagesdiv = $("<div>");
      jPagesdiv.prop("className", "holder");
      articles.after(jPagesdiv);
    }
    $('.holder').jPages({
      containerID: 'articles',
      first: '首页',//false为不显示
      previous: '上一页',//false为不显示
      next: '下一页',//false为不显示 自定义按钮
      last: '尾页',//false为不显示
      perPage: 4,//每页最多显示5个
    });
  };

  window.onload = function () {
    //首页轮播渲染(存在一些问题)
    $.getJSON("/rest/v1" + useradmin + "carouselList", function (date) {
      if (date.length <= 0) {
        carousel.empty();
      } else {
        var ol = $("<ol>");
        ol.prop("className", "carousel-indicators");
        for (index in date) {

          var li = $("<li>");
          if (index == 0) {
            li = $(
              "<li  data-target='#focusslide' data-slide-to='0' >");
          } else {
            li = $(
              "<li data-target='#focusslide' data-slide-to='" + index + "'>");
          }
          ol.append(li);

        }
        var listbox = $("<div>");
        listbox.prop("className", "carousel-inner");
        carousel.prepend(ol);
        ol.after(listbox);
        $.each(date, function (id, item) {
          var boxdiv = $("<div>");
          if (id == 0) {
            boxdiv.prop("className", "item active");
          } else {
            boxdiv.prop("className", "item");
          }
          var diva = $("<a>");
          diva.prop({
            "href": item.url,
            "target": "_blank",
            "title": item.title
          });
          var aimg = $("<img>");
          aimg.prop({
            "src": item.imagePath,
            "alt": item.title,
            "className": "img-responsive"
          });
          listbox.append(boxdiv);
          boxdiv.append(diva);
          diva.append(aimg);
        });
      }

    });
//加载最新评论文章
    $.getJSON("/rest/v1" + useradmin + "latestCommentArticle", function (date) {
      if (date != undefined) {
        var li = $("<li>");
        var lia = $("<a>");
        lia.prop({
          "title": date.title,
          // "href": "#"
        });
        var liaspan1 = $("<span>")
        liaspan1.prop({
          "className": "thumbnail"
        });
        var liaspan1img = $("<img>");
        liaspan1img.prop({
          "className": "thumb",
          "src": date.imgPath,
          "alt": date.title
        }).css("display", "block");
        liaspan1img.data("original", date.imgPath);

        var liaspan2 = $("<span>")
        liaspan2.prop({
          "className": "text"
        });
        liaspan2.text(date.title);

        var liaspan3 = $("<span>")
        liaspan3.prop({
          "className": "muted"
        });
        liaspan3.text(datetimeformat(date.datetime));
        var liaspan3i = $("<i>");
        liaspan3i.prop({
          "className": "glyphicon glyphicon-time"
        });

        var liaspan4 = $("<span>")
        liaspan4.prop({
          "className": "muted"
        });
        liaspan4.text(date.viewCount);
        var liaspan4i = $("<i>");
        liaspan4i.prop({
          "className": "glyphicon glyphicon-eye-open"
        });
        lia.on("click", function () {
          $("article").remove();
          $("#focusslide").empty();
          $(".title").empty();
          content.empty();
          articleinfo(date);
        });
        latestCommentArticle.append(li);
        li.append(lia);
        lia.append(liaspan1);
        liaspan1.append(liaspan1img);
        liaspan1.after(liaspan2);
        liaspan2.after(liaspan3);
        liaspan3.prepend(liaspan3i);
        liaspan3.after(liaspan4);
        liaspan4.prepend(liaspan4i);
      }
    });
    //加载栏目
    $.getJSON("/rest/v1" + useradmin + "taxonomy", function (date) {
        var tul = $("<ul>");
        tul.prop({
          "className": "nav_menu",
        });
        $.each(date, function (id, item) {
          var tli = $("<li>");
          tli.prop({
            "id": item.id,
            "className": "nav_menu-item"
          });

          var tlia = $("<a>");
          tlia.text(item.title);
          tlia.on("click", function () {
            var $this = $(this);
            var tid = $this.parents("li").attr("id");
            catearticlelist(tid, "");
          });
          var tliaul = $("<ul>");
          tliaul.prop({
            "id": item.id,
            "className": "nav_submenu"
          });
          taxonomy.append(tul);
          tul.append(tli);
          tli.append(tlia);
          tlia.append(tliaul)
          $.getJSON("/rest/v1" + useradmin + "cates/" + item.id,
            function (catedate) {
              $.each(catedate, function (id, cateitem) {
                var tliaulli = $("<li>");
                tliaulli.prop({
                  "id": cateitem[0],
                  "className": "nav_submenu-item"
                });
                var tliaullia = $("<a>");
                tliaullia.text(cateitem[1]);

                tliaullia.on("click", function (e) {
                  var $this = $(this);
                  var cid = $this.parents("li").attr("id");
                  var tid = $this.parents("ul").attr("id");
                  catearticlelist(tid, cid);
                  e.stopPropagation();
                });

                tliaul.append(tliaulli);
                tliaulli.append(tliaullia);
              });
            });
        });
      }
    );

//加载推荐
    $.getJSON("/rest/v1" + useradmin + "recommendArticle", function (date) {
      $.each(date, function (id, item) {
        recommendArticle(id, item);
      });
    });
//加载文章
    $.getJSON("/rest/v1" + useradmin + "articles", function (date) {
      $.each(date, function (id, item) {
        articleload(id, item);
      });
      if (date.length > 0) {
        jPages();
      }

    });
//加载基本信息
    $.getJSON(" /rest/v1" + useradmin + "option", function (date) {
      $("#qq").text(date.qq);
      $("#email").text(date.email);
      var img = $("<img>");
      $(".logo.hvr-bounce-in").find("img").prop({
        "src": date.logoPath,
        "alt": date.logoName
      });
      $(".logo.hvr-bounce-in").find("a").prop("title", date.title);
      //最底部信息
      var footp2 = $("<p>");
      footp2.text(date.description);
      $("footer").find("p").text(date.copyright).prepend(footp2);
      //最顶部填充
      var topdiv = $(".header-topbar.hidden-xs.link-border");
      $(".header-topbar.hidden-xs.link-border").text(date.title);

      var topdivul = $("<ul>");

      topdiv.prepend(topdivul);
      var okeywords = date.keywords.split("，");
      for (index in okeywords) {
        var topdivulli = $("<li>");
        topdivulli.prop({
          "className": "site-nav topmenu"
        });
        var topdivullia = $("<a>");
        topdivullia.text(okeywords[index]);
        topdivul.append(topdivulli);
        topdivulli.append(topdivullia);
      }
    });
  };

//文章信息--推荐
  function articleinforecommend(id, item) {
    var li = $("<li>");
    var a = $("<a>");
    a.prop({
      // "href": "#",
      "title": item.note
    });
    a.text(item.title);
    a.on("click", function () {
      $("article").remove();
      $("#focusslide").empty();
      $(".title").empty();
      content.empty();
      articleinfo(item);
    });
    li.append(a);
    return li;
  }

//首页推荐文章函数
  function recommendArticle(id, item) {
    var article = $("<article>");
    var h2 = $("<h2>");
    var h2a = $("<a>");
    var span = $("<span>");
    var p = $("<p>");
    article.prop({
      "className": "excerpt-minic excerpt-minic-index",
      "id": item.cate.id
    });
    span.prop({"className": "red"});
    span.text("【推荐】");
    h2a.prop({
      id: item.id,
      title: item.title,
    });
    h2a.text(item.title);
    p.prop("className", "note");
    p.text(item.note);

    recommend.append(article);
    article.append(h2);
    h2.append(span);
    span.after(h2a);
    h2.after(p);
  }

//文章加载函数
  function articleload(id, item) {
    var article = $("<article></article>");
    article.prop({
      className: "excerpt excerpt-5",
      style: ""
    });
    var aa = $("<a>");
    aa.prop({
      className: "focus",
      title: item.title,
    });

    var img = $("<img>");
    img.prop({
      className: "thumb",
      dataOriginal: item.imgName,
      src: item.imgPath,
      alt: item.title,
      display: "inline;"
    });

    var header = $("<header></header>");
    var headera = $("<a>");
    headera.prop({
      id: item.cate.id,
      className: "cat",
      title: item.cate.title
    });
    headera.text(item.cate.title);
    var h2 = $("<h2></h2>");
    var h2a = $("<a>");
    h2a.prop({
      id: item.id,
      title: item.title,
      text: item.title
    });

    var p1 = $("<p>");
    p1.prop("className", "meta");
    var time = $("<time>");
    time.prop({
      className: "time",
    });

    time.text(datetimeformat(item.datetime));
    var timei = $("<i></i>");
    timei.prop({
      className: "glyphicon glyphicon-time"
    });

    //查看数目
    var p1span = $("<span></span>");
    p1span.prop({
      className: "views",
    });
    p1span.text(item.viewCount);

    var spani = $("<i>");
    spani.prop({
      className: "glyphicon glyphicon-eye-open"
    });

    //评论数目
    var pa = $("<a>");
    pa.prop({
      className: "comment",
      //  href: "##comment",
      title: "评论",
      //  target: "_blank"
    });
    var pi = $("<i></i>");
    pi.prop({
      className: "glyphicon glyphicon-comment"
    });
    pi.text(item.commentCount);

    var p2 = $("<p>");
    p2.prop({
      className: "note",
    });
    p2.text(item.note);

    //a标签
    aa.append(img);

    //head标签
    header.append(headera);
    headera.after(h2);
    h2.append(h2a);

    //p1标签
    p1.append(time);
    time.prepend(timei);
    time.after(p1span);
    p1span.prepend(spani);
    p1span.after(pa);
    pa.after(pi);

    //全部标签拼接
    articles.append(article);
    article.append(aa);
    aa.after(header);
    header.after(p1);
    p1.append(p2);
    // jPages();
  }

//文章界面渲染
  function articleinfo(item) {
    //加载文章的时候跟新观看的数量。
    $.ajax({
      "url": "/rest/v1" + useradmin + "articleaddviewcount/" + item.id,
      "type": "PUT",
      "contentType": "application/json",
      "success": function () {
      }
    });

    //加载文章的时候跟新观看的数量结束
    //头部
    var header = $("<header>");
    header.prop("className", "article-header");
    var headerh1 = $("<h1>");
    headerh1.prop("className", "article-title");
    var h1a = $("<a>");
    h1a.prop({
      id: item.id,
      // href: "#",
      title: item.title
    });
    h1a.text(item.title);

    var headerdiv = $("<div>");
    headerdiv.prop("className", "article-meta");

    var divspan = $("<span>");
    divspan.prop("className", "item article-meta-time");

    var spantime = $("<time>");
    spantime.prop({
      "className": "time",
//      "data-toggle": "tooltip",
      //     "data-placement": "bottom",
      "title": item.title,
      //     "data-original-title": datetimeformat(item.datetime)
    });
    spantime.data({
      "toggle": "tooltip",
      "placement": "bottom",
      "original-title": datetimeformat(item.datetime)
    });
    var spani = $("<i>");
    spani.prop("className", "glyphicon glyphicon-time").text(
      datetimeformat(item.datetime));

    var span2 = $("<span>");
    span2.prop({
      "className": "item article-meta-source",
      //  "data-toggle": "tooltip",
      //  "data-placement": "bottom",
      "title": item.authour,
      //    "data-original-title": item.title
    });
    span2.data({
      "toggle": "tooltip",
      "placement": "bottom",
      "original-title": item.authour
    });
    var span2i = $("<i>");
    span2i.prop("class", "glyphicon glyphicon-globe").text(item.authour);

    var span3 = $("<span>");
    span3.prop({
      "className": "item article-meta-category",
      //   "data-toggle": "tooltip",
      //    "data-placement": "bottom",
      "title": item.cate.title,
      // "data-original-title": item.cate.title
    });
    span3.data({
      "toggle": "tooltip",
      "placement": "bottom",
      "original-title": item.cate.title
    });
    var span3i = $("<i>");
    span3i.prop("className", "glyphicon glyphicon-list");
    var span3a = $("<a>");
    span3a.prop({
      //  href: "#",
      title: item.cate.title
    });
    span3a.text(item.cate.title);

    var span4 = $("<span>");
    span4.prop({
      "title": "",
    });
    span4.data({
      "toggle": "tooltip",
      "placement": "bottom",
      "original-title": item.viewCount
    });
    var span4i = $("<i>");
    span4i.prop("className", "glyphicon glyphicon-eye-open").text(
      item.viewCount);
    var span5 = $("<span>");
    span5.prop({
      "className": "item article-meta-comment",
      "title": "",
    });
    span5.data({
      "toggle": "tooltip",
      "placement": "bottom",
      "original-title": item.commentCount
    });
    span5.data({
      "toggle": "tooltip",
      "placement": "bottom",
      "original-title": item.commentCount
    });
    var span5i = $("<i>");
    span5i.prop("className", "glyphicon glyphicon-comment").text(
      item.commentCount);

    //article部分
    var article = $("<article>");
    article.prop("className", "article-content");

    var p1 = $("<p>");
    var p1img = $("<img>");
    p1img.prop({
      "src": item.imgPath,
      "alt": ""
    });
    p1img.data({
      "original": item.imgName
    });
    var p2 = $("<p>");
    p2.html(item.content);        //使用html输出可以显示便签特效

    //关键字标识
    var keyworddiv = $("<div>");
    keyworddiv.prop("className", "article-tags");
    keyworddiv.text("关键字：");
    var keywords = item.keywords.split(",");
    for (index in keywords) {
      var diva = $("<a>");
      diva.prop({
        // "href": "#",
        "rel": "tag"
      });
      diva.text(keywords[index]);
      keyworddiv.append(diva);
    }

    //推荐relates
    var relatesdiv = $("<div>");
    relatesdiv.prop("className", "relates");
    var rddiv = $("<div>");
    rddiv.prop("className", "title");
    var rddivh3 = $("<h3>");
    rddivh3.text("相关推荐");
    var rdul = $("<ul>");
    $.getJSON("/rest/v1" + useradmin + "recommendArticle", function (date) {
      $.each(date, function (id, item) {
        rdul.append(articleinforecommend(id, item));
      });
    });

    //评论
    var comment1div = $("<div>");
    comment1div.prop({
      "className": "title",
      "id": "comment"
    });
    var comment1divh3 = $("<h3>");
    comment1divh3.text("评论");

    var responddiv = $("<div>");
    responddiv.prop("id", "respond");

    var respondcommentdiv = $("<div>");
    respondcommentdiv.prop("className", "comment");

    var commentinput1 = $("<input>");
    commentinput1.prop({
      "name": "commentName",
      "id": "commentName",
      "className": "form-control",
      "size": 22,
      "placeholder": "您的昵称（必填）",
      "maxlength": 15,
      "autocomplete": "off",
      "tabindex": 1,
      "type": "text"
    });
    var commentinput2 = $("<input>");
    commentinput2.prop({
      "name": "commentEmail",
      "id": "commentEmail",
      "className": "form-control",
      "size": 22,
      "placeholder": "您的网址或邮箱（非必填）",
      "maxlength": 58,
      "autocomplete": "off",
      "tabindex": 2,
      "type": "text"
    });
    var commentboxdiv = $("<div>");
    commentboxdiv.prop("className", "comment-box");
    var commentboxdivtextarea = $("<textarea>");
    commentboxdivtextarea.prop({
      "placeholder": "您的评论或留言（必填）",
      "name": "comment-textarea",
      "id": "comment-textarea",
      "cols": "100%",
      "rows": "3",
      "tabindex": "3"
    });

    var commentctrldiv = $("<div>");
    commentctrldiv.prop("className", "comment-ctrl");
    var commentctrlbutton = $("<button>");
    commentctrlbutton.prop({
      "type": "submit",
      "name": "comment-submit",
      "id": "comment-submit",
      "tabindex": "4"
    });

    commentctrlbutton.text("评论");
    //评论按钮事件
    commentctrlbutton.on("click", function () {
      var commentEmail = $("#commentEmail").val();
      var commentName = $("#commentName").val();
      var commentTextarea = $("#comment-textarea").val();

      var comment = '{'
        + '"commentName":"' + commentName + '",'
        + '"commentEmail":"' + commentEmail + '",'
        + '"content":"' + commentTextarea + '"'
        + '}';
      //使用button触发提交事件
      var usernameval = commentinput1.val();
      $.getJSON("/rest/v1/checkusername?username=" + usernameval,
        function (isExits) {
          if (isExits == false) {
            if (commentboxdivtextarea.val() != "") {
              $.ajax({
                "url": "/rest/v1" + useradmin + "comments/" + item.id,
                "type": "PUT",
                "contentType": "application/json",
                "dataType": "json",
                "data": comment,
                "success": function (aitem) {
                  alert("评论成功");

                  var olli = $("<li>");
                  olli.prop("className", "comment-content");

                  var ollispan = $("<span>");
                  ollispan.prop("className", "comment-f");
                  /*
                   ollispan.text("#2");
                   */
                  var ollidiv = $("<div>");
                  ollidiv.prop("className", "comment-main");
                  var ollidivp = $("<p>");
                  var ollidivpa = $("<a>");
                  ollidivpa.prop({
                    "className": "address",
                    // "href": "#",
                    "rel": "nofollow",
                    // "target":"_blank"
                  });
                  //显示评论内容部分
                  ollidivpa.text(aitem.commentName);
                  var ollidivpspan1 = $("<span>");
                  ollidivpspan1.prop({
                    "className": "time",
                  });
                  ollidivpspan1.text(aitem.date);
                  var br = $("<br>");
                  var ollidivpspan2 = $("<span>");
                  ollidivpspan2.html(aitem.content);
                  postcommentsol.prepend(olli);
                  olli.append(olli);
                  olli.append(ollispan);
                  ollispan.after(ollidiv);
                  ollidiv.append(ollidivp);
                  ollidivp.append(ollidivpa);
                  ollidivpa.after(ollidivpspan1);
                  ollidivpspan1.after(br);
                  br.after(ollidivpspan2);

                },
                "error": function () {
                  alert("评论失败，请联系管理员");
                }
              });
            } else {
              alert("请输入评论内容");
            }
          } else {
            if (confirm("用户名不存在，请先注册后评论，是否进行注册？")) {
              window.location.href = "../register.jsp";
            }
          }
        });

    });
    //评论内容
    var postcommentsdiv = $("<div>");
    var postcommentsol = $("<ol>");
    postcommentsol.prop({
      "className": "commentlist",
      "id": "comment_list"
    });

    //查找文章评论，并进行更新（现在只是显示了一个）
    $.getJSON("/rest/v1" + useradmin + "comment/" + item.id, function (date) {
      $.each(date, function (aid, aitem) {
        var olli = $("<li>");
        olli.prop("className", "comment-content");

        var ollispan = $("<span>");
        ollispan.prop("className", "comment-f");
        /*
         ollispan.text("#" + aid);
         */
        var ollidiv = $("<div>");
        ollidiv.prop("className", "comment-main");
        var ollidivp = $("<p>");
        var ollidivpa = $("<a>");
        ollidivpa.prop({
          "className": "address",
          "href": "#",
          "rel": "nofollow",
        });
        //显示评论内容部分
        ollidivpa.text(aitem.commentName);
        var ollidivpspan1 = $("<span>");
        ollidivpspan1.prop({
          "className": "time",
        });
        ollidivpspan1.text(aitem.date);
        var br = $("<br>");
        var ollidivpspan2 = $("<span>");
        ollidivpspan2.html(aitem.content);
        postcommentsol.append(olli);
        olli.append(olli);
        olli.append(ollispan);
        ollispan.after(ollidiv);
        ollidiv.append(ollidivp);
        ollidivp.append(ollidivpa);
        ollidivpa.after(ollidivpspan1);
        ollidivpspan1.after(br);
        br.after(ollidivpspan2);
      });
    });

    //全部拼接

//新修改内容(判断articles是否存在)
    if ($("#articles").length > 0) {
    } else {
      articles = $("<div>");
      articles.prop("id", "articles");
      content.append(articles);
    }

    articles.append(header);
    header.after(article);
    article.after(keyworddiv);
    keyworddiv.after(relatesdiv);
    relatesdiv.after(comment1div);
    comment1div.after(postcommentsdiv);

//新修改内容结束

    //header拼接
    header.append(headerh1);
    headerh1.append(h1a);

    //div拼接
    headerh1.after(headerdiv);
    headerdiv.append(divspan);
    divspan.append(spantime);
    spantime.append(spani);

    spantime.after(span2);
    span2.append(span2i);

    span2.after(span3);
    span3.append(span3i);
    span3i.after(span3a);

    span3.after(span4);
    span4.append(span4i);

    span4.after(span5);
    span5.append(span5i);

    //article拼接

    article.append(p1);
    p1.append(p1img);
    p1.after(p2);
    /*   p2.after(adiv);
     adiv.append(a1);
     a1.after(a2);
     a2.after(a3);
     a3.after(a4);
     a4.after(a5);
     a5.after(a6);
     a6.after(a7);
     adiv.after(script);*/

    //推荐加载
    relatesdiv.append(rddiv);
    rddiv.append(rddivh3);
    rddiv.after(rdul);

    //评论
    comment1div.append(comment1divh3);
    comment1div.after(responddiv);
    responddiv.append(respondcommentdiv);
    respondcommentdiv.append(commentinput1);
    commentinput1.after(commentinput2);
    commentinput2.after(commentboxdiv);
    commentboxdiv.append(commentboxdivtextarea);
    commentboxdivtextarea.after(commentctrldiv);
    commentctrldiv.append(commentctrlbutton);

    //评论内容
    postcommentsdiv.append(postcommentsol);

  }

  /**
   * 栏目监听事件
   */

//添加文章点击事件(方法有待完善)
  $(document).on("click", "article", function (e) {
    var $this = $(this);
    if ($this.children("p:first-child").is("p")) {
      e.stopPropagation();        //阻止事件冒泡到article
    } else {
      $(".content").empty();
      var cateid = $this.find(".cat");
      var cid = cateid.attr("id");
      var articleid = $this.find("h2").find("a");
      var aid = articleid.attr("id");
      if (cid == null || cid == undefined) {
        cid = $this.attr("id");
      }
      $.getJSON("/rest/v1" + useradmin + "article",
        {cateid: cid, articleid: aid},
        function (date) {
          articleinfo(date);
        });
    }
  });

//文章查询（根据设置的查询关键字）
  $("button[name='search'],input[name='keyword']").on("keydown click",
    function () {
      var keyword = $(this).parents("div").find("input[name='keyword']");
      $.getJSON("/rest/v1" + useradmin + "articles/",
        "keyword=" + keyword.val(),
        function (date) {
          if (date.length > 0) {
            jPagesdiv.remove();
            $("article").remove();
            articles.empty();
            $("#focusslide").empty();
            $(".title").empty();
            $.each(date, function (id, item) {
              articleload(id, item);
            });
            jPages();
          } else {
            jPagesdiv.remove();
            $("article").remove();
            $("#focusslide").empty();
            $(".title").empty();
          }
        })
    });

  //分类列表查询文章
  function catearticlelist(tid, cid) {
    $.getJSON("/rest/v1" + useradmin + "articleListByCateOrTanxonomy", {
        "tid": tid,
        "cid": cid
      },
      function (date) {
        $("article").remove();
        $("#focusslide").empty();
        $(".title").empty();
        $("#articles").empty();
        $.each(date, function (id, item) {
          articleload(id, item);
        });
        jPages();
      }
    )
  }

//格式化时间
  function datetimeformat(datetime) {
    var time = new Date(datetime);
    var y = time.getFullYear();//年
    var m = time.getMonth() + 1;//月
    var d = time.getDate();//日
    var h = time.getHours();//时
    var mm = time.getMinutes();//分
    var s = time.getSeconds();//秒
    // alert(y+"-"+m+"-"+d+" "+h+":"+mm+":"+s);
    return y + "-" + m + "-" + d + " " + h + ":" + mm + ":" + s;
  }
});
