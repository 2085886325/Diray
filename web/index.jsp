<%--
  Created by IntelliJ IDEA.
  User: 不冷
  Date: 2021/2/15
  Time: 12:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>时光日记启动页</title>
  <style>
    *{
      margin: 0;
      padding: 0;
    }
    .main{
      width: 100%;
      height: 100vh;
      background-image: url(https://www.buleng.xyz/code/get_img.php?_=1813);
      background-repeat: no-repeat;
      background-size: cover;
    }
    .circle{
      padding-top: 200px;
      margin: 0 auto;
    }
    .circle_a{
      width: 200px;
      height: 200px;
      text-align: center;
      margin: 0 auto;
      background: #4e76e4;
      /* background: linear-gradient(to bottom, #5433FF, #20BDFF, #4fdd8d); */
      animation: light 4s ease-in-out infinite;
      border-radius: 50%;
      line-height: 200px;
    }
    @keyframes light {
      0% {
        box-shadow: 0 0 8px #5433FF;
        background: #5433FF;
      }

      25% {
        box-shadow: 0 0 20px #20BDFF;
        background: #20BDFF;
      }

      50% {
        box-shadow: 0 0 8px #4fdd8d;
        background: #4fdd8d;
      }

      75% {
        box-shadow: 0 0 20px #20BDFF;
        background: #20BDFF;
      }

      100% {
        box-shadow: 0 0 8px #5433FF;
        background: #5433FF;
      }
    }
    .circle_a:hover{
      animation:stop 1s;
      opacity:0.8;
      transform: scale(1.15) rotate(720deg);
    }

    @keyframes stop
    {
      0% {background:#4e76e4;}
      50% {background:#000000;}
      100%{background: #4e76e4;}
    }

    a{
      text-decoration: none;
      color: #ffffff;
      font-weight: bold;
      font-size: 20px;
      display: contents;
    }
  </style>
</head>
<body>
<div class="main">
  <div class="circle">
    <a href="show?page=1">
      <div class="circle_a">
        启动日记
      </div>
    </a>
  </div>
</div>
</body>
</html>