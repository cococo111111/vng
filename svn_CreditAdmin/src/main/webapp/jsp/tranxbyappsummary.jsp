<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<head>
<link
	href="<%=getServletContext().getContextPath()%>/css/zing_admin/jquery-calendar.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=getServletContext().getContextPath()%>/css/date_range_picker/jquery-ui-1.7.1.custom.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<%=getServletContext().getContextPath()%>/js/date_range_picker/jquery-ui-1.7.1.custom.min.js"></script>
<script type="text/javascript"
	src="<%=getServletContext().getContextPath()%>/js/date_range_picker/daterangepicker.jQuery.js"></script>
<script type="text/javascript"
	src="<%=getServletContext().getContextPath()%>/js/jquery-calendar.js"></script>
</head>
Thống kê giao dịch người dùng trên hệ thống
<br>
<c:set var="contextPath"
	value="<%=getServletContext().getContextPath()%>"></c:set>
<form:form method="POST"
	action="${contextPath}/reportsum/tranxbyappsummary"
	commandName="tranxbyapp">
	<table>
		<tr>
			<td>Tên Ứng Dụng <span class="require">*</span></td>
			<td><form:select path="appId">
					<form:option value="0" label="--All Apps--"></form:option>
					<c:forEach var="a" items="${apps}">
						<form:option value="${a.appId}" label="${a.appName} (${a.appId})">
						</form:option>
					</c:forEach>
				</form:select></td>
		</tr>

		<tr>
			<td>Từ Ngày <span class='require'>*</span></td>
			<td><form:input type='text' id='fromDate' path="fromDate"
					value="${fromDate}" /></td>
			<td>Đến Ngày <span class='require'>*</span></td>
			<td><form:input type='text' id='toDate' path="toDate"
					value="${toDate}" /></td>
		</tr>
		<tr>
			<td>Thống kê theo nhóm:</td>
			<td><form:radiobutton path="status" value="1" checked="checked" />
				Ngày <form:radiobutton path="status" value="0" /> Khoảng thời gian</td>
		</tr>
		<tr>
			<td></td>
			<td colspan='3'><input type='submit' name='summary'
				value='Thống Kê Summary'></td>
		</tr>
	</table>
</form:form>


<table class="mainTab" width="100%" border="0" cellpadding="0"
	cellspacing="0">
	<tbody>
		<tr class="tabTit">
			<td>AppID</td>
			<td>Ngày</td>
			<td>Tổng số Tranx</td>
			<td>Thành công</td>
			<td>Thất bại</td>
			<td>TimeOut</td>
			<td>NetworkError</td>
			<td>Đang giao dịch</td>
			<td>ZingXu giao dịch</td>
			<td>Users</td>
		</tr>
		<c:forEach var="result" items="${subResult}" varStatus="count">
			<tr class="hligh${count.index%2+1}">
				<td align="center">${result.agentID}</td>
				<td align="center">${result.startTime}</td>
				<td align="center">${result.totalTransaction}</td>
				<td align="center">${result.totalTransactionSuccess}</td>
				<td align="center">${result.totalTransactionFail}</td>
				<td align="center">${result.totalTransactionTimeOut}</td>
				<td align="center">${result.totalTransactionNetError}</td>
				<td align="center">-</td>
				<td align="center">${result.totalMoney}</td>
				<td align="center">${result.totalDiffUser}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>


<script>
	$(function() {

		$('#fromDate').datepicker({
			dateFormat : 'yy-mm-dd'
		});
		$('#toDate').datepicker({
			dateFormat : 'yy-mm-dd'
		});
	});
</script>