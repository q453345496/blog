<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
.category-first .title{
	line-height: 30px;
	padding: 0 20px;
	white-space: nowrap;
	word-wrap: normal;
	text-overflow: ellipsis;
	overflow: hidden;
}
.first-info{
	display: flex;
	padding: 5px 20px;
}
.first-info .thumb{
	margin-right: 12px;
}
.first-info .thumb img{
	width: 186px;
	height: 140px;
}
.first-info .summary{
	color:#999;
}
.category-other{
	padding: 5px 20px;
}
.other-info{
	display: flex;
	justify-content: space-between;
}
.other-info a:hover{
	color: #45B6F7;
}
.other-info .fa-angle-right{
	width: 12px;
}
</style>
<div class="layui-row index index-category">
	<h3 class="cat-title">推荐阅读</h3>
	<div>
		<div class="category-first">
			<h2 class="title">
				<a href="#" title="HTML5-语义化" target="_blank">算法基础：BFS和DFS的直观解释</a>
			</h2>
			<div class="first-info">
				<div class="thumb">
					<a class="focus" href="#">
						<img src="../resources/images/koala.jpg" alt="chrome  transition闪烁BUG-大前端">
					</a>
				</div>
				<div class="summary">
					&nbsp;&nbsp;&nbsp;&nbsp;前段时间写鼠标悬停元素上移效果时，当鼠标恰好放在元素边缘时，chrome出现一直上下移动的问题，其他浏览器表现正常。原因尚不知，可能是实现方式不对吧（PS：使用top实现），虽然不知道原因，但...	
				</div>
			</div>
		</div>
		<div class="category-other">
			<ul>
				<li>
					<div class="other-info">
						<a href="#"><i class="fa fa-angle-right fa-lg"></i>机器学习实战教程（十二）：线性回归提高篇之乐高玩具套件二手价预测</a>
						<span>11-06</span>
					</div>
				</li>
				<li>
					<div class="other-info">
						<a href="#"><i class="fa fa-angle-right fa-lg"></i>机器学习实战教程（十一）：线性回归基础篇之预测鲍鱼年龄</a>
						<span>11-06</span>
					</div>
				</li>
				<li>
					<div class="other-info">
						<a href="#"><i class="fa fa-angle-right fa-lg"></i>排序（8）：基数排序</a>
						<span>11-06</span>
					</div>
				</li>
				<li>
					<div class="other-info">
						<a href="#"><i class="fa fa-angle-right fa-lg"></i>canvas小tip–下载二维码、图片加水印</a>
						<span>11-06</span>
					</div>
				</li>
				<li>
					<div class="other-info">
						<a href="#"><i class="fa fa-angle-right fa-lg"></i>分享一个生成二维码的插件–QRCode.js</a>
						<span>11-06</span>
					</div>
				</li>
			</ul>
		</div>
	</div>
</div>