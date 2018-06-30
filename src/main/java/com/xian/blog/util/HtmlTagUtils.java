package com.xian.blog.util;

public class HtmlTagUtils {
	private static final String EMPTY = "";
	private static final String DISABLED = "disabled";
	private static final String ACTIVE = "active";

	public static String createPage(final Integer currentPage, final Integer pageSize, final Long total, String param) {
		if (total == 0) {
			return "";
		}
		long totalPage = total % pageSize == 0 ? (total / pageSize) : (total / pageSize) + 1;
		if (currentPage == null || currentPage > totalPage) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		final String otherParam = param == null ? "" : param;

		createPageLi(sb, "首页", 1, otherParam, EMPTY);
		//上一页
		if (currentPage > 1) {
			createPageLi(sb, "上一页", currentPage - 1, otherParam, EMPTY);
		} else {
			createPageLi(sb, "上一页", currentPage - 1, otherParam, DISABLED);
		}
		//当前页
		for (int i = currentPage - 2; i <= currentPage + 2; i++) {
			if (i < 1 || i > totalPage) {
				continue;
			}
			if (currentPage == i) {
				createPageLi(sb, i, i, otherParam, ACTIVE);
			} else {
				createPageLi(sb, i, i, otherParam, EMPTY);
			}
		}

		//下一页
		if (currentPage == totalPage) {
			createPageLi(sb, "下一页", totalPage, otherParam, DISABLED);
		} else {
			createPageLi(sb, "下" + "一页", currentPage + 1, otherParam, EMPTY);
		}
		createPageLi(sb, "尾页", totalPage, otherParam, EMPTY);
		return sb.toString();
	}

	private static StringBuilder createPageLi(StringBuilder sb, Object name, long pageNum, String param,
			String className) {
		if (EMPTY.equals(className)) {
			sb.append("<li>");
		} else {
			sb.append("<li class=\"").append(className).append("\">");
		}
		if (DISABLED.equals(className)) {
			sb.append("<a href=\"#\">").append(name).append("</a></li>").append("\n");
		} else {
			sb.append("<a href=\"?").append(param).append("&page=").append(pageNum).append("\">")//
					.append(name).append("</a></li>").append("\n");
		}
		return sb;
	}

	public static void main(String[] args) {
		System.out.println(createPage(11, 10, 100l, "&typeID=1"));
	}
}
