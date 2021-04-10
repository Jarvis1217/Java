<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品管理系统</title>
    <link href="./css/login_css.css" rel="stylesheet" type="text/css" />
    <script src="./js/jquery.js"></script>
</head>

<body>
<div id="login_center">
    <div id="login_area">
        <div id="login_box">
            <div id="login_form">
                <form id="submitForm" action="LoginServlet" method="post">
                    <div id="login_tip">
                        <span id="login_err" class="sty_txt2"></span>
                    </div>
                    <div>
                        用户名：<input type="text" name="username" class="username" id="name">
                    </div>
                    <div>
                        密&nbsp;&nbsp;&nbsp;&nbsp;码：<input type="password" name="password" class="pwd" id="pwd">
                    </div>
                    <div id="btn_area">

                        <div style="color: red;margin-left: 50px;">
                            <span>${ sessionScope.errCode }</span>
                        </div>

                        <br>

                        <input type="button" class="login_btn" id="login_sub"  value="登  录">
                        <input type="reset" class="login_btn" id="login_ret" value="重 置">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    $("#login_sub").bind("click",() => {
        if($("#name").val().trim() === "" || $("#pwd").val().trim() === "") {
            alert("请输入完整信息");
        }else {
            $("#submitForm").submit();
        }
    })
</script>
</body>
</html>
