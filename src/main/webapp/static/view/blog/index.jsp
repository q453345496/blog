<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
.index-content{
	background-color: #fff;
}
.cat-title{
	border-bottom: 1px solid #e8e8e8;
	font-size: 16px;
	padding-bottom: 10px;
}

.index-content .title h2{
	font-size: 1.1rem;
	font-weight: 300;
}
.index-content .title h2 a{
	color: #555;
}
.index-content .title h2 a:hover{
	color: #45B6F7;
}
.index-content .meta{
	margin-bottom: 10px;
	font-size:12px;
	color: #aaa;
}
.index-content .meta span{
	margin-right: 20px;
}
.index-content .meta i{
	margin-right: 4px;
}

.first-post .thumb{
	margin-bottom: 10px;
}
.first-post .thumb img{
	width: 100%;
	height: 200px;
}
.first-post .summary{
	margin-bottom: 10px;
	font-size:12px;
	text-align: justify;
}
.other-post{
	display: flex;
	margin-bottom: 10px;
}
.other-post .thumb{
	margin-right: 12px;
}
.other-post .thumb a {
	margin-right: 12px;
}
.other-post .thumb img{
	width: 100px;
	height: 100px;
}

</style>
<div class="layui-row index-content layui-col-space20" >
	<h3 class="cat-title">推荐阅读</h3>
	<div class="layui-col-md6">
		<div class="first-post">
			<div class="thumb">
				<a class="focus" href="#">
					<img src="../resources/images/koala.jpg" alt="chrome  transition闪烁BUG-大前端">
				</a>
			</div>
			<div class="title">
				<h2>
					<a href="#" title="HTML5-语义化" target="_blank">chrome  transition闪烁BUG</a>
				</h2>
			</div>
			<div class="meta">
				<span><i class="fa fa-clock-o"></i>2018-09-10</span>
				<span><i class="fa fa-eye"></i>阅读</span>
			</div>
			<div class="summary">
				&nbsp;&nbsp;&nbsp;&nbsp;前段时间写鼠标悬停元素上移效果时，当鼠标恰好放在元素边缘时，chrome出现一直上下移动的问题，其他浏览器表现正常。原因尚不知，可能是实现方式不对吧（PS：使用top实现），虽然不知道原因，但...		</div>
			<div class="readmore">
				<a href="#">阅读全文&gt;&gt;</a>
			</div>
		</div>
	</div>
	<div class="layui-col-md6">
		<div class="last-post-list">
			<div class="other-post">
				<div class="thumb">
					<a class="focus" href="#">
						<img src="../resources/images/koala.jpg" alt="chrome  transition闪烁BUG-大前端">
					</a>
				</div>
				<div class="info">
					<div class="title">
						<h2>
							<a href="#" title="HTML5-语义化" target="_blank">chrome  transition闪烁BUG</a>
						</h2>
					</div>
					<div class="meta">
						<span><i class="fa fa-clock-o"></i>2018-09-10</span>
						<span><i class="fa fa-eye"></i>阅读</span>
					</div>
				</div>
			</div>
			<div class="other-post">
				<div class="thumb">
					<a class="focus" href="#">
						<img src="../resources/images/koala.jpg" alt="chrome  transition闪烁BUG-大前端">
					</a>
				</div>
				<div class="info">
					<div class="title">
						<h2>
							<a href="#" title="HTML5-语义化" target="_blank">HTML5-语义化</a>
						</h2>
					</div>
					<div class="meta">
						<span><i class="fa fa-clock-o"></i>2018-09-10</span>
						<span><i class="fa fa-eye"></i>阅读</span>
					</div>
				</div>
			</div>
			<div class="other-post">
				<div class="thumb">
					<a class="focus" href="#">
						<img src="../resources/images/koala.jpg" alt="chrome  transition闪烁BUG-大前端">
					</a>
				</div>
				<div class="info">
					<div class="title">
						<h2>
							<a href="#" title="HTML5-语义化" target="_blank">HTML5-语义化</a>
						</h2>
					</div>
					<div class="meta">
						<span><i class="fa fa-clock-o"></i>2018-09-10</span>
						<span><i class="fa fa-eye"></i>阅读</span>
					</div>
				</div>
			</div>
			<div class="other-post">
				<div class="thumb">
					<a class="focus" href="#">
						<img src="../resources/images/koala.jpg" alt="chrome  transition闪烁BUG-大前端">
					</a>
				</div>
				<div class="info">
					<div class="title">
						<h2>
							<a href="#" title="HTML5-语义化" target="_blank">HTML5-语义化</a>
						</h2>
					</div>
					<div class="meta">
						<span><i class="fa fa-clock-o"></i>2018-09-10</span>
						<span><i class="fa fa-eye"></i>阅读</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</div>
<script type="text/javascript">
</script>