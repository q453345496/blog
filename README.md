#使用mybatis-plus遇到的问题
1. 数据库表名
问题:表名带有前缀问题
解决方法:在全局配置中添加
```
<bean id="globalConfig" class="com.baomidou.mybatisplus.entity.GlobalConfiguration">
    <property name="tablePrefix" value="t_" />
</bean>
```
	或者在每个实体加上`@TableName(value="t_journal_category")`
2. 驼峰命名
问题:字段`sourceURL`,自动生成的sql是`source_u_r_l`
解决方法:需要使用`@TableField(value = "file_md5")`

3. boolean型字段
问题:数据库应该为`is_root`,`pojo`字段不能带有`is`,则为`root`
解决:通过映射文件以及`@TableField`进行映射

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
`htmlDecode : "style,script,sub,sup|on*"`,//表示这些标签不进行解析,|后为属性
`htmlDecode : true`,则不过滤
注意，优酷视频复制出来的连接有问题,allowfullscreen带有引号导致报错
```
<iframe height=498 width=510 src='http://player.youku.com/embed/XMzY3NTIxMjgwOA==' frameborder=0 'allowfullscreen'></iframe>
```
5.图片上传表单参数为`editormd-image-file`

#集成ueditor
1. 注意预览时，如果有代码，则需要ueditor.parse.js文件,或者修改preview.html里面的js引用

2. 修改原有的路径
```
UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
UE.Editor.prototype.getActionUrl = function(action) {
	var test = true;
	if (action == 'config') {
		if(test){
			return basePath + '/resources/config2.json';
		}
		return basePath + '/resources/config.json';
	} else if (action == 'uploadImage') {
		return basePath + '/ueditor/uploadImage';
	} else if (action == 'uploadVideo') {
		return basePath + '/ueditor/uploadVideo';
	} else if (action == 'uploadFile') {
		return basePath + '/ueditor/uploadFile';
	} else if (action == 'listFile') {
		return basePath + '/ueditor/listImage';
	} else if (action == 'listImage') {
		return basePath + '/ueditor/listFile';
	} else if (action == 'catchImage') {
		return basePath + '/ueditor/catchImage';
	} else {
		return this._bkGetActionUrl.call(this, action);
	}
}
```

3. 原代码中的ueditor.config.js中的**whiteList**拼写错误