/**
 * Created by Lancer on 2017/5/14.
 */
$(function () {

  //文章关键字搜索
  var keywords = $("input[name='keywords']");
  keywords.on("change", function () {
    var keywordval = keywords.val();
    $("#search").prop("href",
      "article_getArticleList.action?keywords=" + keywordval);
  });

  //设置推荐的数值
  $("input[name='isvouch']").each(function () {
      var $this = $(this);
    if ($this.val()=="true"){
        $this.prop("checked",$this.val());
      }
    }
  );

//  isvouch.prop("checked","${article.isvouch}");
});