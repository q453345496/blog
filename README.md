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

4. 条件查询
使用Wrapper时，eq需要自己判断值为空，否则条件会添加sql中，除了`like`，`in`

5. 单个查询selectOne
**为防止使用该方法查询出问题，实体对象不应该有默认值**

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