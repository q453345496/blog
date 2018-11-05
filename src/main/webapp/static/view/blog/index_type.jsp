<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
.first-info{
display: flex;
}
</style>
<div class="layui-row index index-category">
	<h3 class="cat-title">推荐阅读</h3>
	<div>
		<div class="first-post">
			<h2>
				<a href="#" title="HTML5-语义化" target="_blank">chrome  transition闪烁BUG</a>
			</h2>
			<div class="first-info">
				<div class="thumb">
					<a class="focus" href="#">
						<img src="../resources/images/koala.jpg" alt="chrome  transition闪烁BUG-大前端">
					</a>
				</div>
				<div>
					&nbsp;&nbsp;&nbsp;&nbsp;前段时间写鼠标悬停元素上移效果时，当鼠标恰好放在元素边缘时，chrome出现一直上下移动的问题，其他浏览器表现正常。原因尚不知，可能是实现方式不对吧（PS：使用top实现），虽然不知道原因，但...	
				</div>
			</div>
		</div>
		<div>
			<ul>
				<li><a href="#">中国首届React开发者大会 8月18日 广州举行</a></li>
				<li><a href="#">HTML5-语义化</a></li>
				<li><a href="#">Windows node版本管理器–nvm</a></li>
				<li><a href="#">canvas小tip–下载二维码、图片加水印</a></li>
				<li><a href="#">分享一个生成二维码的插件–QRCode.js</a></li>
			</ul>
		</div>
	</div>
</div>