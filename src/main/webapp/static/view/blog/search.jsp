<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
</style>
<div class="search-result">
	<div class="result-status">
		为您找到相关记录<strong>24</strong>条
	</div>
	<div class="result-error">
		未查询到结果，请换个关键字试试看！
	</div>
	<div class="result-item">
		<h3><a href="#" title="HTML5-语义化" target="_blank">chrome  transition<span class="highlight">闪烁</span>BUG</a></h3>
		<p class="url">https://www.oschina.net/news/33201/java-cover</p>
		<p class="summary">前段时间写鼠标悬停元素上移效果时，当鼠标恰好放在元素边缘时，<span class="highlight">chrome</span>出现一现），虽然不知道原因，但...	</p>
		<p class="date">发布于&nbsp;2018-09-29</p>
	</div>
	<div class="result-item">
		<h3><a href="#" title="HTML5-语义化" target="_blank"><span class="highlight">HTML5</span>-语义化</a></h3>
		<p class="url">https://www.oschina.net/news/33201/java-cover</p>
		<p class="summary">距<span class="highlight">HTML5</span>标准规范制定完成并公开发布已经有好些年了。但是在实际运用时，</p>
		<p class="date">发布于&nbsp;2018-09-29</p>
	</div>
	<div class="result-item">
		<h3><a href="#" title="HTML5-语义化" target="_blank"><span class="highlight">HTML5</span>-语义化</a></h3>
		<p class="url">https://www.oschina.net/news/33201/java-cover</p>
		<p class="summary">距<span class="highlight">HTML5</span>标准规范制定完成并公开发布已经有好些年了。但是在实际运用时，</p>
		<p class="date">发布于&nbsp;2018-09-29</p>
	</div>
	<div class="result-item">
		<h3><a href="#" title="HTML5-语义化" target="_blank"><span class="highlight">HTML5</span>-语义化</a></h3>
		<p class="url">https://www.oschina.net/news/33201/java-cover</p>
		<p class="summary">距<span class="highlight">HTML5</span>标准规范制定完成并公开发布已经有好些年了。但是在实际运用时，</p>
		<p class="date">发布于&nbsp;2018-09-29</p>
	</div>
	<div class="result-item">
		<h3><a href="#" title="HTML5-语义化" target="_blank"><span class="highlight">HTML5</span>-语义化</a></h3>
		<p class="url">https://www.oschina.net/news/33201/java-cover</p>
		<p class="summary">距<span class="highlight">HTML5</span>标准规范制定完成并公开发布已经有好些年了。但是在实际运用时，</p>
		<p class="date">发布于&nbsp;2018-09-29</p>
	</div>
	<div id="search-page" class="pagination"></div>
</div>
<script type="text/javascript">
layui.use('laypage', function(){
	  var laypage = layui.laypage;
	  laypage.render({
		    elem: 'search-page',
		    count: 100,
		    curr: 1,
		    theme: '#1E9FFF',
		    first: "首页",
		    last: "尾页",
		    jump: function(obj, first){
		    	if(!first){
					var href = location.pathname +'?kw=${kw}';
					href += '&page=' + obj.curr;
					console.log(href)
					location.href = href;
		    	}
		    }
	  });
	});
</script>