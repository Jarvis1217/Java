<%@ page import="com.myself.shop.pojo.Goods" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>商品管理系统</title>
    <link rel="stylesheet" href="./layui/css/layui.css">
    <link href="./css/main_css.css" rel="stylesheet"/>
    <link href="./css/zTreeStyle.css" rel="stylesheet"/>
    <script src="./js/jquery.js"></script>
    <script src="./js/jquery.ztree.core-3.2.js"></script>
    <script src="./js/commonAll.js"></script>
    <script src="./layui/layui.js"></script>
</head>

<body>
    <form class="layui-form" action="" id="pop" style="display: none;margin-top: 12px">
        <div class="layui-form-item" style="width: 350px">
            <label class="layui-form-label">商品名称</label>
            <div class="layui-input-block">
                <input id="pname" type="text" required  lay-verify="required"
                       placeholder="请输入商品名称" autocomplete="off" class="layui-input" value="">
            </div>
        </div>

        <div class="layui-form-item" style="width: 350px">
            <label class="layui-form-label">商品类型</label>
            <div class="layui-input-block">
                <select id="ptype" name="type" lay-verify="required">
                    <option value=""></option>
                    <option value="食品饮料">食品饮料</option>
                    <option value="家居清洁">家居清洁</option>
                    <option value="粮油副食">粮油副食</option>
                    <option value="护肤美妆">护肤美妆</option>
                </select>
            </div>
        </div>

        <div class="layui-form-item" style="width: 350px">
            <label class="layui-form-label">商品单价</label>
            <div class="layui-input-block">
                <input id="pprice" type="text" name="title" required  lay-verify="required"
                       placeholder="请输入商品单价" autocomplete="off" class="layui-input" value="">
            </div>
        </div>

        <div class="layui-form-item" style="width: 350px">
            <label class="layui-form-label">库存余量</label>
            <div class="layui-input-block">
                <input id="pstock" type="number" name="title" required  lay-verify="required"
                       placeholder="请输入库存余量" autocomplete="off" class="layui-input" value="">
            </div>
        </div>

        <div class="layui-form-item" style="margin-left: 50px;margin-top: 20px;">
            <div class="layui-input-block">
                <button id="commonBtn" type="button" class="layui-btn">立即提交</button>
            </div>
        </div>
    </form>

    <div id="top">
        <div id="top_logo" style="margin-top: 10px">
            <img alt="logo" src="./images/common/logo.jpg" width="274" height="49" style="vertical-align:middle;">
        </div>
        <div id="top_links">
            <div id="top_op">
                <ul>
                    <li>
                        <img alt="当前用户" src="./images/common/user.jpg">：
                        <span>${ sessionScope.user.uname }</span>
                    </li>
                    <li>
                        <img alt="当前时间" src="./images/common/date.jpg">：
                        <span id="clock"></span>
                    </li>
                </ul>

                <script>
                    function setTime() {
                        let date = new Date();
                        let month = date.getMonth() < 10? "0"+(date.getMonth()+1) : date.getMonth()+1;
                        let day = date.getDate() < 10? "0"+date.getDate() : date.getDate();
                        let hour = date.getHours() < 10? "0"+date.getHours() : date.getHours();
                        let minutes = date.getMinutes() < 10? "0"+date.getMinutes() : date.getMinutes();
                        let seconds = date.getSeconds() < 10? "0"+date.getSeconds() : date.getSeconds();

                        $("#clock").text(
                            date.getFullYear() + "-" +
                            month + "-" +
                            day + " " +
                            hour+ ":" +
                            minutes+ ":" +
                            seconds
                        );
                    }
                    //设置时间间隔
                    setInterval(setTime, 1000);
                </script>

            </div>
            <div id="top_close">
                <a href="javascript:;" onclick="logout()" target="_parent">
                    <img alt="退出系统" title="退出系统" src="./images/common/close.jpg"
                         style="position: relative; top: 20px; left: 25px;">
                </a>
            </div>
        </div>
    </div>

    <div style="margin-top: 80px;float: left;margin-left: 30px">
        <span>当前所在分类 > ${sessionScope.type}</span>
    </div>

    <div id="operate" style="margin-top: 70px;margin-right: 30px;text-align: right;float: right">
        <form class="layui-form" action="">
            <div class="layui-form-item">

                <div class="layui-inline">
                    <label class="layui-form-label">商品分类</label>
                    <div class="layui-input-inline" style="width: 200px;text-align: left">
                        <select id="fnType" lay-filter="findType" name="type">
                            <option value="" selected></option>
                            <option value="全部分类">全部分类</option>
                            <option value="食品饮料">食品饮料</option>
                            <option value="家居清洁">家居清洁</option>
                            <option value="粮油副食">粮油副食</option>
                            <option value="护肤美妆">护肤美妆</option>
                        </select>
                    </div>
                </div>

                <div class="layui-inline">
                    <button type="button" class="layui-btn layui-btn-normal" onclick="addProduct()">新增商品</button>
                </div>
            </div>
        </form>
    </div>

    <script>
        function addProduct() {
            $("#pname").val("");
            $("#ptype").val("");

            layui.use('form', function(){
                const form = layui.form;
                form.render();
            });

            $("#pprice").val("");
            $("#pstock").val("");

            $("#commonBtn").unbind('click')
            $("#commonBtn").on('click',() => {
                $.ajax({
                    url: "/shop/InsertServlet",
                    type: "post",
                    data: {
                        "name": $("#pname").val(),
                        "type": $("#ptype").val(),
                        "price": $("#pprice").val(),
                        "stock": $("#pstock").val(),
                        "operator": '${ sessionScope.user.uname }'
                    },
                    success: resp => {
                        $.ajax({
                            url: "/shop/GoodsList",
                            type: "get",
                            success:resp => {
                                window.location.reload();
                            }
                        });
                    }
                });
            })

            layui.use('layer', function(){
                const layer = layui.layer;

                layer.open({
                    type: 1,
                    title: "新增商品",
                    area: ['420px', '380px'],
                    content: $("#pop")
                });
            });
        }

        layui.use(['form'], function() {
            const form = layui.form;
            form.on('select(findType)', function(data){

                if(data.value === "全部分类") {
                    $.ajax({
                        url: "/shop/GoodsList",
                        type: "get",
                        success:resp => {
                            window.location.reload()
                        }
                    });
                }else {
                    $.ajax({
                        url: "/shop/GoodsListWithType",
                        type: "post",
                        data:{
                            "type": data.value
                        },
                        success:resp => {
                            window.location.reload()
                        }
                    });
                }
            });
        });
    </script>

    <div id="content" style="margin-top: 12px;text-align: center">
        <table class="layui-table">
            <thead>
            <tr>
                <th>商品名称</th>
                <th>商品类型</th>
                <th>商品单价</th>
                <th>库存余量</th>
                <th>操作员</th>
                <th>更新时间</th>
                <th>操作</th>
            </tr>
            </thead>

            <tbody>
            <%
                List<Goods> goods = (List<Goods>) request.getSession().getAttribute("allGoods");
                if (goods.size() == 0) {
            %>
            <tr>
                <td style="text-align: center" colspan="7">
                    <h2 style="color: gray">暂无库存信息</h2>
                </td>
            </tr>
            <%}%>

            <%
                if (goods.size() != 0) {
                    for (Goods gd : goods) {
            %>

            <tr>
                <td><%= gd.getName() %></td>
                <td><%= gd.getType() %></td>
                <td><%= gd.getPrice() %></td>
                <td><%= gd.getStock() %></td>
                <td><%= gd.getOperator() %></td>
                <td><%= gd.getOperationTime() %></td>
                <td>
                    <button class="layui-btn layui-btn-warm layui-btn layui-btn-sm"
                            onclick="changePro('<%= gd.getId() %>','<%= gd.getName() %>','<%= gd.getType() %>','<%= gd.getPrice() %>','<%= gd.getStock() %>')">
                        修改
                    </button>

                    <button class="layui-btn layui-btn-danger layui-btn layui-btn-sm"
                            onclick="delProduct(<%= gd.getId() %>)">
                        删除
                    </button>
                </td>
            </tr>

            <%
                    }
                }
            %>
            </tbody>
        </table>
    </div>

    <script>
        function logout() {
            if (confirm("确定退出系统？")) {
                $.ajax({
                    url: "/shop/LogoutServlet",
                    type: "post"
                });

                window.location.reload();
            }
        }
        function delProduct(id) {
            $.ajax({
                url: "/shop/DelGoodsServlet",
                type: "post",
                data: {
                    "id": id
                },
                success:resp => {
                    $.ajax({
                        url: "/shop/GoodsList",
                        type: "get",
                        success:resp => {
                            window.location.reload();
                        }
                    });
                }
            });
        }
        function changePro(id,name,type,price,stock) {
            $("#pname").val(name)
            $("#ptype").val(type)

            layui.use('form', function(){
                const form = layui.form;
                form.render();
            });

            $("#pprice").val(price)
            $("#pstock").val(stock)

            $("#commonBtn").unbind('click')
            $("#commonBtn").on('click',() => {
                $.ajax({
                    url: "/shop/UpdateServlet",
                    type: "post",
                    data: {
                        "id": id,
                        "name": $("#pname").val(),
                        "type": $("#ptype").val(),
                        "price": $("#pprice").val(),
                        "stock": $("#pstock").val(),
                        "operator": '${ sessionScope.user.uname }'
                    },
                    success: resp => {
                        $.ajax({
                            url: "/shop/GoodsList",
                            type: "get",
                            success:resp => {
                                window.location.reload();
                            }
                        });
                    }
                });
            })

            layui.use('layer', function(){
                const layer = layui.layer;

                layer.open({
                    type: 1,
                    title: "库存信息修改",
                    area: ['420px', '380px'],
                    content: $("#pop")
                });
            });
        }
    </script>
</body>
</html>
