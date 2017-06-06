/**
 * Created by Lancer on 2017/5/1.
 */
$(function () {
  /**
   * 添加栏目
   */
  $(".button.border-yellow").click(function () {
    $(".panel.admin-panel").hide();
    $(".panel.admin-panel.margin-top").show();
  });

  /**
   * 修改栏目
   */
  $("table tr td div a[type='button']").click(function () {
    $("#add span:last-child").html("修改栏目");
    var lists = $(this).parents("tr").find("td");
    var inputList = $("#column").find("input[type!=radio]");
    for (var i = 0; i < lists.length - 1; i++) {
      inputList.eq(i+1).val(lists.eq(i).text());
    }
    $(".panel.admin-panel").hide();
    $(".panel.admin-panel.margin-top").show();
  });
});