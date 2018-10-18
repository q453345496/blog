<%@ page language="java" pageEncoding="UTF-8"%>
<style>
</style>
<div id="carousel" class="layui-carousel">
   <div carousel-item="">
    <div>
    	<img src="../resources/images/img_fjords_wide.jpg"/>
    </div>
    <div>
    	<img src="../resources/images/img_nature_wide.jpg"/>
    </div>
    <div>
    	<img src="../resources/images/img_mountains_wide.jpg"/>
    </div>
  </div>
</div>

<script>
layui.use(['carousel'], function(){
	var carousel = layui.carousel;
	carousel.render({
		elem: '#carousel',
		width: '100%', //设置容器宽度
		arrow: 'always', //始终显示箭头
		//,anim: 'updown' //切换动画方式
	});
});
</script>