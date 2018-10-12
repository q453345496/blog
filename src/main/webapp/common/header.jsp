<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
.header-nav{
	background-color: #fff;
	margin-bottom: 15px;
	border-bottom: 1px solid #EAEAEA;
	font-size: 18px;
}
</style>
<header class="header-nav">
	<nav class="container navbar navbar-expand-lg navbar-light">
	  <a class="navbar-brand" href="#">
	  	 <img src="../resources/images/logo.png" width="120" height="40" class="d-inline-block align-top" alt="">
	  </a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	
		  <div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active">
				  <a class="nav-link" href="#">首页</a>
				</li>
				<c:forEach var="type" items="${typeList }">
					<c:choose>
						<c:when test="${empty type.subs }">
							<li class="nav-item">
							<a class="nav-link" href="<%=basePath%>/t/${type.code }">后台开发</a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="nav-item dropdown">
								<a class="nav-link dropdown-toggle" href="<%=basePath%>/t/${type.code }" data-toggle="dropdown">
								  ${type.name }
								</a>
								<div class="dropdown-menu">
									<c:forEach var="sub" items="${type.subs }">
										<a class="dropdown-item" href="<%=basePath%>/t/${sub.code }">${sub.name }</a>
									</c:forEach>
								</div>
							</li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</ul>
		    <form class="form-inline my-2 my-lg-0" action="<%=basePath %>/search">
				<input name="kw" class="form-control mr-sm-2" type="search" placeholder="请输入关键字" aria-label="Search" value="${kw }">
				<button class="btn btn-primary my-2 my-sm-0" type="submit">搜索</button>
		    </form>
		</div>
	</nav>
</header>