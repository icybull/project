<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>IndexPage</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
</head>
<body>
	<div class="ml-5 mt-3">
		<h2>◎파일 확장자 차단</h2>
		<hr>
		<h4>파일 확장자에 따라 특정 형식의 파일을 첨부하거나 전송하지 못하도록 제한</h4>
		<form action="index" method="post">
			<div>
				<label for="file">고정 확장자 <c:choose>
						<c:when test="${state}">
							<c:forEach var="ff" items="${fixedArr}" varStatus="vs">
								<c:choose>
									<c:when test="${ff.checked==1}">
										<input style="margin-left: 15px;" type="checkbox" name="file"
											value="${ff.nick}" checked>${ff.nick}
									</c:when>
									<c:otherwise>
										<input style="margin-left: 15px;" type="checkbox" name="file"
											value="${ff.nick}">${ff.nick}
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<input style="margin-left: 15px;" type="checkbox" name="file"
								value="bat">bat
							<input style="margin-left: 15px;" type="checkbox" name="file"
								value="cmd">cmd
							<input style="margin-left: 15px;" type="checkbox" name="file"
								value="com">com 
							<input style="margin-left: 15px;" type="checkbox" name="file"
								value="cpl">cpl 
							<input style="margin-left: 15px;" type="checkbox" name="file"
								value="exe">exe
							<input style="margin-left: 15px;" type="checkbox" name="file"
								value="scr">scr 
							<input style="margin-left: 15px;" type="checkbox" name="file"
								value="js">js
						</c:otherwise>
					</c:choose>
				</label>
			</div>
			<label for="">커스텀 확장자<input type="text" class="ml-3"
				placeholder="확장자 입력" style="width: 300px;" name="add" id="a">
				<button class="btn btn-dark ml-3" type="submit" id="s">+추가</button>
				<br> <c:choose>
					<c:when test="${state}">
						<div class="mt-3" name="content"
							style="margin-left: 110px; border: 1px solid; width: 400px; height: 250px;">
							<div>${count}/200</div>
							<div class="p-2 wrap flex-wrap d-flex">
								<c:forEach var="uf" items="${unfixedArr}" varStatus="vs">
									<div class="d-flex justify-content-center align-items-center"
										style="margin-right: 10px; border: 1px solid; width: 70px; height: 20px; border-radius: 5px;">
										${uf.nick}<a href="delete?name=${uf.nick}"
											class="ml-2 text-dark">X</a>
									</div>
								</c:forEach>
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<div class="mt-3" name="content"
							style="margin-left: 110px; border: 1px solid; width: 400px; height: 250px;">
							<div>0/200</div>
						</div>
					</c:otherwise>
				</c:choose>
			</label>
		</form>
	</div>
	<script>
		$(document).ready(function() {
			$("#s").on("click", function() {
				var str = $("#a").val();
				if(str.length>20){
	                alert("확장자 길이는 최대 20입니다.");
	                return false;
				}
			});
		});
	</script>
</body>

</html>