<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
</style>
<div class="post-list">
	<div class="post-item">
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
				<span><i class="fa fa-list-alt"></i>html</span>
				<span><i class="fa fa-eye"></i>阅读</span>
			</div>
			<div class="summary">
				&nbsp;&nbsp;&nbsp;&nbsp;前段时间写鼠标悬停元素上移效果时，当鼠标恰好放在元素边缘时，chrome出现一直上下移动的问题，其他浏览器表现正常。原因尚不知，可能是实现方式不对吧（PS：使用top实现），虽然不知道原因，但...		</div>
			<div class="readmore">
				<a href="#">阅读全文&gt;&gt;</a>
			</div>
		</div>
	</div>
	<div class="post-item">
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
				<span><i class="fa fa-list-alt"></i>html</span>
				<span><i class="fa fa-eye"></i>阅读</span>
			</div>
			<div class="summary">
				&nbsp; &nbsp; 距HTML5标准规范制定完成并公开发布已经有好些年了，面试时也少不了要问对HTML5语义化得理解。但是在实际运用时，真正使用HTML5标签来开发的似乎不是很多（ps：查看了几个巨头公司网站推论），可能一部分原因是仍有部分用户使用在使...
			</div>
			<div class="readmore">
				<a href="#">阅读全文&gt;&gt;</a>
			</div>
		</div>
	</div>
	<div id="pagination" class="pagination">
	</div>
</div>
<script type="text/javascript">
layui.use('laypage', function(){
	var laypage = layui.laypage;
	laypage.render({
	    elem: 'pagination',
	    count: 100,
	    theme: '#1E9FFF',
	    first: "首页",
	    last: "尾页",
	    jump: function(obj, first){
	    	if(!first){
				var href = location.pathname + '?';
				href += '&page=' + obj.curr;
				console.log(href)
				location.href = href;
	    	}
	    }
	});
});
</script>