<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="cpath" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>Forty by HTML5 UP</title>
<meta charset="UTF-8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="resources/assets/css/main.css" />
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<style>
</style>
<body style="text-align: center;">
	<!-- Wrapper -->
	<div id="wrapper">
		<!-- Menu -->
		<nav id="Update">
		<h2>회원관리페이지</h2>
		<form class="form-inline" onsubmit="return false">
			<!-- onsubmit -> js로 처리하기위해 엔터 또는새로고침 이후 서버로 전송x -->
			<div class="dropdown">
				<table>
					<tr>
						<td><label for="search">검색</label></td>
						<td><input type="text" id="search" class="form-control"
							placeholder="검색어입력">
							<ul class="dropdown-menu">
								<li><a href=""></a></li>
							</ul></td>
					</tr>
				</table>
			</div>
		</form>
		<table>
			<tr>
				<td>Email</td>
				<td>Tel</td>
				<td>Address</td>
			</tr>
			<c:forEach var="list" items="${list}">
				<tr>
					<td>${list.email}</td>
					<td>${list.tel}</td>
					<td>${list.address}</td>
				</tr>
			</c:forEach>
		</table>
		</nav>
		<a href="./" class="button next scrolly">되돌아가기</a>
	</div>
	<!-- Scripts -->
	<script src="resources/assets/js/jquery.min.js"></script>
	<script src="resources/assets/js/jquery.scrolly.min.js"></script>
	<script src="resources/assets/js/jquery.scrollex.min.js"></script>
	<script src="resources/assets/js/skel.min.js"></script>
	<script src="resources/assets/js/util.js"></script>
	<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
	<script src="resources/assets/js/main.js"></script>

	<script>
				// 1. input태그를 DOM으로 가져오기
				// * DOM : 문서객체 Document Object Model : html 문서구조를 객체로 표현한것
				$("#search").on("keyup", ()=>{
				// 2. input태그에 keyup이벤트 등록
				// 3. 이벤트가 발생할때마다 input태그 안쪽에 있는 글자 가져오기
				let search =$("#search").val();
				// 4. console에 출력하기
				console.log("가져온 데이터 :" , search);
					
					let data = {
							search :search
					};
					
					$.ajax({
						url : "${cpath}/searchMember",
				      	data : data,
				      	success : (res) => {
				       		// res -> response
				         	console.log("성공!!", res);
				         	// .dropdown-menu 안을 비워준다. (초기화)
				         	$(".dropdown-menu").empty();
				         	$(".dropdown-menu").css("display","block");
				         	$.each(res, (i,mvo)=>{
				            	$(".dropdown-menu").append(`<li>\${mvo.email}\</li>`);
			         		});
				        // 응답 받아온 데이터의 개수만큼 반복문을 통해 li태그 붙여주기
				      	},
				      	error : () => {
				         
				      	} 
				   });
				});

			
			
			</script>
</body>
</html>

