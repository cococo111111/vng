
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="<%=getServletContext().getContextPath()%>/js/jquery-1.4.2.js"></script>

<form:form action="/CreditAdmin/admin/accountupdate/userId=${user.userName}" modelAttribute="user" method="POST">
	<table id="editAdminTable">
		<tr>
			<td class="hligh2" width="20%"><label for="viewname">ID
					Đăng nhập</label></td>
			<td class="hligh2"><form:input path="userName" disabled="true"></form:input> </td>
		</tr>
		<tr>
			<td class="hligh2" width="20%"><label for="viewname">Tên
					Admin</label><span class="require">*</span></td>
			<td class="hligh2"><form:input path="adminName" /></td>
		</tr>
		<tr>
			<td class="hligh1"></td>
			<td class="hligh1"><input type="submit" class="button"></td>
		</tr>
</form:form>