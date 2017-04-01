<%@ page language="java" pageEncoding="UTF-8"%>
<jsp:include page="/common/baseInclude.jsp" />

<title>登录页面</title>
<link href="./login/style_log.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="./login/style.css">
<link rel="stylesheet" type="text/css" href="./login/userpanel.css">
<link rel="stylesheet" type="text/css" href="./login/jquery.ui.all.css">
<script type="text/javascript" src="./login/google_jquery.min.js"></script>
<script type="text/javascript" src="./login/google_jquery-ui.min.js"></script>
<script type="text/javascript" src="./js/jquery-form.js"></script>

</head>

<body class="login" mycollectionplug="bind">
<form  id="loginForm" action="" method="post" enctype="application/x-www-form-urlencoded">
<div class="login_m">
<div class="login_logo"><img src="./login/logo.png" width="196" height="46"></div>
<div class="login_boder">
<div class="login_padding" id="login_model">

  <h2>用户名:</h2>
  <label>
    <input type="text" id="userName" name="userName" class="txt_input txt_input2" onfocus="if (value=='请输入用户名'||value=='用户名或密码错误'){value =''}" onblur="if (value ==''){value='请输入用户名'}" value="请输入用户名">
  </label>
  <h2>密码  :</h2>
  <label>
    <input type="password" name="passwd"  id="userpwd" class="txt_input">
  </label>

  <p class="forgot"><a id="iforget" href="javascript:void(0);">忘记密码?</a></p>
  <div class="rem_sub">
  <div class="rem_sub_l">
  <input type="checkbox" name="checkbox" id="save_me">
   <label for="checkbox">记住密码</label>
   </div>
    <label>
      <input type="button" class="sub_button" id="button" value="登录" style="opacity: 0.7;" onClick="login();">
    </label>
  </div>
</div>

<div id="forget_model" class="login_padding" style="display:none">
<br>

   <h1>忘记密码</h1>
   <br>
   <div class="forget_model_h2">请填写您的注册邮箱</div>
    <label>
    <input type="text" id="usrmail" class="txt_input txt_input2">
   </label>

 
  <div class="rem_sub">
  <div class="rem_sub_l">
   </div>
    <label>
     <input type="submit" class="sub_buttons" name="button" id="Retrievenow" value="Retrieve now" style="opacity: 0.7;">
    
     <input type="submit" class="sub_button" name="button" id="denglou" value="Return" style="opacity: 0.7;">
    
    </label>
  </div>
</div>
<!--login_padding  Sign up end-->
</div><!--login_boder end-->
</div><!--login_m end-->
</form>
</body>
<script type="text/javascript">
	function login(){
		$("#loginForm").ajaxSubmit({
			url:"${path}/index/login",
			type:'post',
			dataType:'json',
			success: function(data) {
				if(data){
		            location.href = "${path}/adminPages/index.html";
				}else{
					$("#userName").val("用户名或密码错误");
				}
	         }
		});
	}
</script>
</html>