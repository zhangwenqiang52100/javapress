/**
 * Created by Lancer on 2017/5/1.
 */
$(function () {
  $("#add div:first-child strong span:last-child").html("修改内容");
  $("#image").change(function () {
    var file = $("#image").val();
    var strFileName = file.replace(/^.+?\\([^\\]+?)(\.[^\.\\]*?)?$/gi, "$1");  //正则表达式获取文件名，不带后缀
    var FileExt = file.replace(/.+\./, "");   //正则表达式获取后缀
    $("#imageName").attr("value", strFileName + "." + FileExt);
  });
});
function onOpenFile() {
  document.getElementById("image").click();
}