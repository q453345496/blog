<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<style>
</style>
<jsp:include page="index_today.jsp"></jsp:include>
<jsp:include page="index_type.jsp"></jsp:include>
<jsp:include page="index_list.jsp"></jsp:include>
<script type="text/javascript">
</script>