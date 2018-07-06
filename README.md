#使用mybatis-plus遇到的问题
1. 数据库表名
问题:
	表名带有前缀问题
解决方法:
	在全局配置中添加
	    <bean id="globalConfig" class="com.baomidou.mybatisplus.entity.GlobalConfiguration">
	        <property name="tablePrefix" value="t_" />
	    </bean>
	或者在每个实体加上@TableName(value="t_journal_category")
2. 驼峰命名
问题:	
	字段sourceURL,自动生成的sql是source_u_r_l
解决方法:	
	需要使用@TableField(value = "file_md5")

3. boolean型字段
问题:
	数据库应该为is_root,pojo字段不能带有is,则为root
解决:
	通过映射文件以及@TableField进行映射
	
#集成editormd.js
1. 数学公式开启需要修改js中的 
	```
	editormd.katexURL  = {
	        css : "//cdn.bootcss.com/KaTeX/0.9.0/katex.min",
	        js  : "//cdn.bootcss.com/KaTeX/0.9.0/katex.min"
	};
	```
2. 显示html需要引入一下js,否则会报错
	```
	<script src="<%=path%>/resources/editor/lib/marked.min.js"></script>
	<script src="<%=path%>/resources/editor/lib/prettify.min.js"></script>
	<script src="<%=path%>/resources/editor/lib/flowchart.min.js"></script>
	<script src="<%=path%>/resources/editor/lib/raphael.min.js"></script>
	<script src="<%=path%>/resources/editor/lib/underscore.min.js"></script>
	<script src="<%=path%>/resources/editor/lib/sequence-diagram.min.js"></script>
	<script src="<%=path%>/resources/editor/lib/flowchart.min.js"></script>
	<script src="<%=path%>/resources/editor/lib/jquery.flowchart.min.js"></script>
	```
3. 显示html时在div下需要有一个textarea

4. 显示html时
htmlDecode : "style,script,sub,sup|on*",//表示这些标签不进行解析,|后为属性
htmlDecode : true,则不过滤
注意，优酷视频复制出来的连接有问题,allowfullscreen带有引号导致报错
<iframe height=498 width=510 src='http://player.youku.com/embed/XMzY3NTIxMjgwOA==' frameborder=0 'allowfullscreen'></iframe>
5.图片上传表单参数为editormd-image-file