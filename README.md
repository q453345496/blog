使用mybatis-plus遇到的问题
1.数据库表名
问题:
	表名带有前缀问题
解决方法:
	在全局配置中添加
	    <bean id="globalConfig" class="com.baomidou.mybatisplus.entity.GlobalConfiguration">
	        <property name="tablePrefix" value="t_" />
	    </bean>
	或者在每个实体加上@TableName(value="t_journal_category")
2.驼峰命名
问题:	
	字段sourceURL,自动生成的sql是source_u_r_l
解决方法:	
	需要使用@TableField(value = "file_md5")

3.boolean型字段
问题:
	数据库应该为is_root,pojo字段不能带有is,则为root
解决:
	通过映射文件以及@TableField进行映射