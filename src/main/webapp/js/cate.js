/**
 * Created by Lancer on 2017/5/1.
 */
$(function () {
  /**
   * 修改分类列表
   */
  $(".button.border-main").on("click", function () {
    $("strong span:last-child").html("修改分类");
    var catelist = $(this).parents("tr").find("td:not(:last-child)");
    var editCate = $("#saveOrUpdateCate input");
    var cateselect = $("#saveOrUpdateCate option:first-child");
    for (var i = 0; i < catelist.length; i++) {
      if (i != 1 && i != 2) {
        if (i > 2) {
          var j = i - 2;
          editCate.eq(j).val(catelist.eq(i).text());
          console.log(editCate.eq(j));
        } else {
          editCate.eq(i).val(catelist.eq(i).text());
          console.log(editCate.eq(i));
        }

      }
    }
    cateselect.prop({
      value: catelist.eq(1).text(),
      text: catelist.eq(2).text(),
      selected: true
    });
    $(".panel.admin-panel").hide();
    $(".panel.admin-panel.margin-top").show();
  });
  /**
   * 增加分类
   */
  $(".button.border-yellow").click(function () {
    $(".panel.admin-panel").hide();
    $(".panel.admin-panel.margin-top").show();
  });
  var keywords = $("#keywords");
  keywords.on("change", function () {
    var keywordval = keywords.val();
    $("#search").prop("href",
      "cate_searchCateList.action?keywords=" + keywordval);
  });
});
