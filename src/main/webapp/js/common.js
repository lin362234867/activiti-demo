// 将form转为AJAX提交
function ajaxSubmit(frm, fn) {
   var dataPara = getFormJson(frm);
   $.ajax({
      url : frm.action,
      type : frm.method,
      data : dataPara,//可能会出现后台接收到的参数值为null的情况，原因是form.js的源码不全，没有data这个参数，需要重新下载官网的源码。
      dataType : "json",
      async: false,//异步
      success : fn
   });
}

// 将form中的值转换为键值对。
function getFormJson(frm) {
   var o = {};
   var a = $(frm).serializeArray();
   $.each(a, function() {
      if (this.name == "password") {
         this.value = $.md5(this.value)
      }
      if (o[this.name] !== undefined) {
         if (!o[this.name].push) {
            o[this.name] = [ o[this.name] ];
         }
         o[this.name].push(this.value || '');
      } else {
         o[this.name] = this.value || '';
      }
   });
   return o;
}