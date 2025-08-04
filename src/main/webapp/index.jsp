<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	Object userid = request.getAttribute("userid");
	Object username = request.getAttribute("username");
	Object email = request.getAttribute("email");
	Object gender = request.getAttribute("gender");
	Object birthday = request.getAttribute("birthday");
	Object regdate = request.getAttribute("regdate");
%>


<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="css/reset.css">
	<link rel="stylesheet" href="css/login.css">
	<link rel="stylesheet" href="css/content.css">
<title>나만의 가계부</title>
<style>
.index-main {
	display: flex;
	margin-left: 200px;
	padding-left: 30px;
	padding-top: 50px;
	height: 100vh;
	background-color: whitesmoke;
}
.index-user {
	display: inline-block;
	white-space: nowrap;
	padding: 10px;
	margin-right: 5px;
	width: 40%;
	max-width: 350px;
	min-width: 350px;
	height: 50%;
	max-height: 600px;
	min-height: 600px;
	background-color: cornflowerblue;
	border-radius: 12px;
}
.index-user hr {
	margin-bottom: 10px;
	width: 330px;
	height: 5px;
	background-color: rgba(0, 0, 0, 0.5);
	border-style: none;
	border-radius: 8px;
}
.user-box {
	display: flex;
	align-items: center;
	width: 100%;
	max-width: 350px;
	height: 50px;
	margin-bottom: 10px;
	background-color: rgba(255,255,255,0.4);
	border-radius: 16px;
}
.img-box {
	display: flex;
	justify-content: center;
	width: 100%;
	height: 100px;
	margin-bottom: 5px;
}
.user-box h2 {
	font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
	padding: 0px 10px 0px 10px;
	display: block;
	font-size: 15px;
}
.finance-main {
	display: inline-block;
	white-space: nowrap;
	padding: 30px;
	margin-right: 30px;
	width: 70%;
	max-width: 1500px;
	min-width: 1000px;
	height: 90%;
	min-height: 700px;
	max-height: 1250px;
	background-color: cornsilk;
	border-radius: 12px;
}
.setting-bar {
	background-color: burlywood;
	border-radius: 12px;
	width: 150px;
	height: 600px;
}
</style>
</head>
<body>
	<header>
        <div class="side-bar">
            <a href="#">가계부 홈</a>
            <a href="#">가계부 검색</a>
            <a href="#">가계부 작성</a>
        </div>
    </header>
<div class="index-main">
	<div class="finance-main">
		<h2>여기부터 달력느낌으로다가</h2>
	</div>
	<div class="index-user">
		<div class="img-box">
			<img src="images/logo.png" id="login-logo"/>
		</div>
		<div class="user-box">
			<h2><!--<%=username %> -->이름: name01님</h2>
		</div>
		<div class="user-box">
			<h2><!--<%=email %> -->이메일: user01@naver.com</h2>
		</div>
		<div class="user-box">
			<h2><!--<%=gender %>-->성별: gender</h2>
		</div>
		<div class="user-box">
			<h2><!--<%=birthday %>-->생일: 2000-01-01</h2>
		</div>
		<div class="user-box">
			<h2><!--<%=regdate %>-->가입일: 2020-05-03</h2>
		</div>
		<hr>
		<div class="user-box">
			<h2>총 자산 내역</h2>
		</div>
		<div class="user-box">
			<h2>전월 지출액</h2>
		</div>
	</div>
	<div class="setting-bar">
		이렇게 하거나 파란넘밑으로 내리거나..
	</div>
</div>
</body>
</html>