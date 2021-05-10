<%--
  Created by IntelliJ IDEA.
  User: 不冷
  Date: 2021/2/17
  Time: 18:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>时光日记1.0-微生活记录站</title>
    <style>
        *{
            margin: 0;
            padding: 0;
        }
        body{
            /*background: rgb(244, 244, 244);*/
            color: #333333;
        }
    </style>
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.2/dist/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/index.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/write.css">
</head>
<body>
<div class="themes1">
    <div class="themes red">
        <span>红色</span>
    </div>
    <div class="themes black">
        <span>黑色</span>
    </div>
    <div class="themes blue">
        <span>蓝色</span>
    </div>
    <div class="themes green">
        <span>绿色</span>
    </div>
    <div class="themes yellow">
        <span>黄色</span>
    </div>
    <div class="themes default">
        <span>默认</span>
    </div>
</div>
<div class="theme">
    <span>更换配色</span>
</div>
<div class="login">
    <span>登录</span>
</div>
<div class="write" style="display: none">
    <span>撰写</span>
</div>


<div class="wrap">
    <div  class="loginform" style="display: none;">
        <div class="main_div">
            <form class="form" method="post" action="login">
                <div class="form_div">
                    <input type="text" name="username"  required="required" class="form-control" id="inputEmail3" placeholder="用户名">
                    <i class="fa fa-user"></i>
                </div>
                <div class="form_div">
                    <input type="password" name="password"  required="required" class="form-control" id="inputPassword3" placeholder="密 码">
                </div>
                <div class="form_sub">
                    <button type="reset"  class="btn btn-default">重置</button>
                    <button type="submit" class="btn btn-default">登录</button>
                </div>
            </form>
        </div>
    </div>
    <div  class="writeform" style="display: none">
        <div class="main_div">
            <form class="form" method="post" action="write">
                <div class="write_form_div">
                    <input type="text" name="title" class="form-control"  required="required" placeholder="日 记 标 题">
                </div>
                <div class="write_form_div2">
                    <textarea class="form_wirte" type="text" name="content"  required="required" placeholder="想 写 什 么 内 容"></textarea>
                    <input type="text" name="date" style="display: none" class="new_date">
                </div>
                <div class="form_sub">
                    <button type="submit" class="btn btn-default">提交</button>
                </div>
            </form>
        </div>
    </div>
    <div class="content">
        <!-- <p class="title">时光日记</p> -->
        <div class="diray">
            <p class="title">时光日记</p>
            <a href="logout"><p class="logout" style="display: none;">退出登录</p></a>
            <div class="diray_div">
                <%--<c:forEach begin="1" end="${fn:length(query)}" varStatus="query_info">
                    <div class="faq">
                        <p class="faq-p">${query[fn:length(query)-query_info.index].getDate()}</p>
                        <div class="answer-box">
                            <p class="p1">${query[fn:length(query)-query_info.index].getTitle()}</p>
                            <div class="p2">${query[fn:length(query)-query_info.index].getContent()}</div>
                        </div>
                    </div>
                </c:forEach>--%>
                <c:forEach items="${query}" varStatus="v" var="query_info">
                    <div class="faq">
                        <p class="faq-p">${query_info.getDate()}</p>
                        <div class="answer-box">
                            <p class="p1">${query_info.getTitle()}</p>
                            <div class="p2">
                                ${query_info.getContent()}
                                <a href="" onclick="de_click(${query_info.id})" style="display: none" class="delete">删除</a>
                            </div>

                        </div>

                    </div>
                </c:forEach>
            </div>
            <div class="page">
                <p class="page_left"><a href="#" onclick="page_Left()"  class="more2">&lt; 上一页</a></p>
                <p class="page_right"><a href="#" onclick="page_Right()" class="more2">下一页 &gt;</a></p>
            </div>
        </div>
    </div>
    <div class="copyright">
    </div>
    <footer class="footer">
        <div class="links">
            <p class="">© 2021 Copyright 不冷</p>
            <span>友情链接:</span>
            <a href="https://www.buleng.xyz" target="_blank">不冷博客</a>
            <a href="https://www.baidu.com" target="_blank">百度</a>
            <a href="https://www.qq.com" target="_blank">QQ</a>
            <a href="https://wpa.qq.com/msgrd?v=3&uin=2085886325&site=qq&menu=yes" target="_blank">联系作者</a>
        </div>
    </footer>
</div>
<script>
    // 登录设置
    $(".login").click(function () {
        // $(".loginform").css("display","block");
        var successful = "<%=session.getAttribute("user")%>";
        // console.log(successful);
        if(successful != "1"||successful==null){
            // window.location.href = "${ctx}/jsp/Login.jsp";
            $(".loginform").slideToggle();
        }else {
            // $(".loginform").slideToggle()
            alert("你已经登录！");
            $(".write").css("display","block");
            $(".logout").css("display","block");
            $(".delete").css("display","block");
        }
        //    if (user!=null&&pwd!=null) {
        //         $.ajax({
        //             url: "/write",//注意：这里实际情况应该是根据点击的不同a标签加载不同页面
        //             data: {key: 'ajax'},//注意：这是必须的参数标识
        //             success: function(html){
        //             //将请求回来的内容添加到下面的内容div
        //                 $("#con").html(html);
        //             }
        //         });
        //    } else {
        //       alert("账号或者密码不能为空")
        //    }
        //  return false; // 阻止 A 链接跳转
    })
</script>
<script>
    // 撰写设置
    var successful = "<%=session.getAttribute("user")%>";
    if(successful != "1"||successful==null){
        console.log("未登录");
    }else {
        $(".write").css("display","block");
        $(".logout").css("display","block");
        $(".delete").css("display","block");
    }
    $(".write").click(function () {
        var successful = "<%=session.getAttribute("user")%>";
        if(successful != "1"||successful==null){
            alert("不登录你想干啥？")
        }else {
            $(".writeform").slideToggle();
        }
    })
    // $.get("show");
</script>
<script>
    //删除函数嵌套
    function de_click(value) {
        var successful = "<%=session.getAttribute("user")%>";
        if(successful != "1"||successful==null){
            alert("不登录你想干啥？")
        }else {
            $(".delete").css("display","block");
            $(".logout").css("display","block");
            Delete(value)
        }
    }

    //删除函数
    function Delete(value){
        var txt;
        if (confirm("确定删除？")) {
            txt = "删除成功";
            $.ajax({
                type: "get",
                url: "delete",
                data: "id="+value,
                success: function(){
                    location.reload();
                },
                error:function () {
                    alert("发生错误，删除失败，请联系网站管理员")
                }
            });
        } else {
            txt = "取消删除";
        }
        alert(txt);
    }
</script>

<script>
    // 最前面一页跟最后面一页设置提示
    function page_Left() {
        var page = "${page}";
        if(page-1 == 0){
            alert("已到第一页");
        }else {
            window.location.href="show?page=${page-1}";
        }
    }
    function page_Right() {
        var page = "${page}";
        var right_last = "${page_info}";
        if(page+1 == right_last+1){
            alert("已到最后一页");
        }else {
            window.location.href="show?page=${page+1}";
        }
    }
</script>
</body>
</html>

