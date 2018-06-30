package com.xian.blog.util;

import java.util.Date;

import org.apache.ibatis.reflection.MetaObject;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;

public class MyMetaObjectHandler extends MetaObjectHandler {

	public MyMetaObjectHandler() {
	}

	@Override
	public void insertFill(MetaObject metaObject) {
		Date now = new Date();
		Object createTime = getFieldValByName("createTime", metaObject);
		if (createTime == null) {
			setFieldValByName("createTime", now, metaObject);
		}
		Object modifyTime = getFieldValByName("modifyTime", metaObject);
		if (modifyTime == null) {
			setFieldValByName("modifyTime", now, metaObject);
		}

	}

	@Override
	public void updateFill(MetaObject metaObject) {
		setFieldValByName("modifyTime", new Date(), metaObject);
	}

}
