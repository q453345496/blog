<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<%@include file="common/link.jsp"%>
<title>The Flower Blog</title>
<style>
.footer{
	padding: 2.5rem 0;
	background-color: #f9f9f9;
	text-align: center;
}
</style>
</head>
<body>
	<div class="container">
		<header>
			<h1>The Blog</h1>
		</header>
		<div class="">
			<ul class="nav justify-content-start">
				<li class="nav-item">
					<a class="nav-link" href="#">Link</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#">Link</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#">Link</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#">Disabled</a>
				</li>
			</ul>
		</div>
	</div>
	<div role="main" class="container">
		<div class="row">
        	<div class="col-md-8 blog-main">
		
				<div class="blog-post">
					<h2 class="blog-post-title">Sample blog post</h2>
					<p class="blog-post-meta">January 1, 2014 by <a href="#">Mark</a></p>
					
				</div><!-- postend -->
				
        	</div><!-- main-end -->
        	
        	<aside class="col-md-4 blog-sidebar">
			  <div class="p-3 mb-3 bg-light rounded">
			    <h4 class="font-italic">About</h4>
			    <p class="mb-0">Etiam porta <em>sem malesuada magna</em> mollis euismod. Cras mattis consectetur purus sit amet fermentum. Aenean lacinia bibendum nulla sed consectetur.</p>
			  </div>
			
			  <div class="p-3">
			    <h4 class="font-italic">Archives</h4>
			    <ol class="list-unstyled mb-0">
			      <li><a href="#">March 2014</a></li>
			      <li><a href="#">February 2014</a></li>
			      <li><a href="#">January 2014</a></li>
			      <li><a href="#">December 2013</a></li>
			      <li><a href="#">November 2013</a></li>
			      <li><a href="#">October 2013</a></li>
			      <li><a href="#">September 2013</a></li>
			      <li><a href="#">August 2013</a></li>
			      <li><a href="#">July 2013</a></li>
			      <li><a href="#">June 2013</a></li>
			      <li><a href="#">May 2013</a></li>
			      <li><a href="#">April 2013</a></li>
			    </ol>
			  </div>
			
			  <div class="p-3">
			    <h4 class="font-italic">Elsewhere</h4>
			    <ol class="list-unstyled">
			      <li><a href="#">GitHub</a></li>
			      <li><a href="#">Twitter</a></li>
			      <li><a href="#">Facebook</a></li>
			    </ol>
			  </div>
			</aside>
        
        </div>
	</div>
	<footer class="footer">
		<p>
			Blog template built for <a href="https://getbootstrap.com/">Bootstrap</a>
			by <a href="https://twitter.com/mdo">@mdo</a>.
		</p>
		<p>
			<a href="#">Back to top</a>
		</p>
	</footer>
</body>
</html>