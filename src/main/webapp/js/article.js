/**
 * Created by Lancer on 2017/5/7.
 */
$(function () {
  $("#aimg").change(function () {
    var file = $("#aimg").val();
    var strFileName=file.replace(/^.+?\\([^\\]+?)(\.[^\.\\]*?)?$/gi,"$1");  //正则表达式获取文件名，不带后缀
    var FileExt=file.replace(/.+\./,"");   //正则表达式获取后缀
    $("#imgName").attr("value", strFileName+"."+FileExt);
  });
});
function onOpenFile() {
  document.getElementById("aimg").click();
}