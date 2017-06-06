/**
 * Created by Lancer on 2017/5/7.
 */
/*
 javascripe代码
 */
$(function () {
  /*$("#bt").on("click", function (e) {
    e.preventDefault();
    $.ajaxFileUpload
    (
      {
        url: 'uploadimage_logoUplaod.action', //用于文件上传的服务器端请求地址
        secureuri: false, //是否需要安全协议，一般设置为false
        fileElementId: 'logo', //文件上传域的ID
        dataType: 'json', //返回值类型 一般设置为json
        success: function (data, status)  //服务器成功响应处理函数
        {
          $("#img1").attr("src", data.imgurl);
          if (typeof (data.error) != 'undefined') {
            if (data.error != '') {
              alert(data.error);
            } else {
              alert(data.msg);
            }
          }
        },
        error: function (data, status, e)//服务器响应失败处理函数
        {
          alert(e);
        }
      })
    return false;
  });*/
  $("#logo").change(function () {
    var file = $("#logo").val();
    var strFileName=file.replace(/^.+?\\([^\\]+?)(\.[^\.\\]*?)?$/gi,"$1");  //正则表达式获取文件名，不带后缀
    var FileExt=file.replace(/.+\./,"");   //正则表达式获取后缀
    $("#logoName").attr("value", strFileName+"."+FileExt);
  });
});
function onOpenFile() {
  document.getElementById("logo").click();
}