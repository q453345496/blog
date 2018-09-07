<%@ page language="java" pageEncoding="UTF-8"%>
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
	      <li class="nav-item">
	        <a class="nav-link" href="#">前端开发</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="#">后台开发</a>
	      </li>
	      <li class="nav-item dropdown">
	        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	          其他
	        </a>
	        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
	          <a class="dropdown-item" href="#">Action</a>
	          <a class="dropdown-item" href="#">Another action</a>
	          <div class="dropdown-divider"></div>
	          <a class="dropdown-item" href="#">Something else here</a>
	        </div>
	      </li>
	    </ul>
	    <form class="form-inline my-2 my-lg-0">
	      <input class="form-control mr-sm-2" type="search" placeholder="请输入关键字" aria-label="Search">
	      <button class="btn btn-primary my-2 my-sm-0" type="submit">搜索</button>
	    </form>
	  </div>
	</nav>
</header>