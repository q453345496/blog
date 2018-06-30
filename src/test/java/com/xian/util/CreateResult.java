package com.xian.util;

import java.lang.reflect.Field;

import org.apache.commons.lang3.StringUtils;

import com.baomidou.mybatisplus.annotations.TableField;
import com.xian.blog.model.Param;

public class CreateResult {

	public static void main(String[] args) {
		Class<Param> clazz = Param.class;
		StringBuilder sb = new StringBuilder();
		for (Field field : clazz.getDeclaredFields()) {
			System.out.println("字段：" + field.getName());
			String columnName = getColumnName(field);
			if (StringUtils.isNotBlank(columnName)) {
				sb.append(columnName);
				sb.append(",");
			}
		}
		System.out.println(sb.toString());
	}

	private static String getColumnName(Field field) {
		TableField annotation = field.getAnnotation(TableField.class);
		if (annotation == null) {
			System.out.println(field.getName() + "无注解");
			return as(field);
		} else {
			if (!annotation.exist()) {
				return "";
			}
			if (StringUtils.isBlank(annotation.value())) {
				System.out.println(field.getName() + "注解无默认值");
				return as(field);
			} else {
				System.out.println(field.getName() + "使用默认值");
				return field.getName() + " AS " + annotation.value();
			}
		}
	}

	private static String as(Field field) {
		String name = field.getName();
		StringBuilder sb = new StringBuilder();
		boolean find = false;
		for (int i = 0; i < name.length(); i++) {
			char charAt = name.charAt(i);
			if (Character.isLowerCase(charAt)) {
				sb.append(charAt);
			} else {
				sb.append("_").append(Character.toLowerCase(charAt));
				find = true;
			}
		}
		return find ? name + " AS " + sb.toString() : name;
	}

}
