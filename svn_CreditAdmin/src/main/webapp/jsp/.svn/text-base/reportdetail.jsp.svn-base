<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<head>
<link
	href="<%=getServletContext().getContextPath()%>/css/zing_admin/jquery-calendar.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<%=getServletContext().getContextPath()%>/js/date_range_picker/daterangepicker.jQuery.js"></script>
<script type="text/javascript"
	src="<%=getServletContext().getContextPath()%>/js/jquery-calendar.js"></script>
</head>
<h2 align="center">VÍ ZING ME - BÁO CÁO CHI TIẾT NHẬP XUẤT TỒN
	ZINGXU</h2>
<c:set var="contextPath"
	value="<%=getServletContext().getContextPath()%>"></c:set>
<form:form action="${contextPath}/report/reportdetail" method="POST"
	id="reportForm" commandName="report">
	<table>
		<tr>
			<td>Từ Ngày <span class='require'>*</span></td>
			<td><form:input path="fromDate" id="fromDate"
					value="${fromDate}" /></td>
			<td>Đến Ngày <span class='require'>*</span></td>
			<td><form:input path="toDate" id="toDate" value="${toDate}" /></td>
		</tr>
		<tr>
			<td></td>
			<td colspan='3'><input type='submit' name='report'
				onclick="reportSubmit()" value='Report'></td>
		</tr>
	</table>
</form:form>
<table width="100%" border="0" cellspacing="0" cellpadding="0"
	class="mainTab">
	<tbody>
		<tr class="tabTit bo">
			<td rowspan="2">ngày</td>
			<td rowspan="2">Tồn đầu kỳ</td>
			<td>Nhập Trong Kỳ</td>
			<td colspan="${count}">Xuất Trong Kỳ</td>
			<td rowspan="2">Tồn Cuối kỳ</td>
		</tr>
		<tr class="tabTit bo">
			<td>Tổng Nhập</td>
			<c:forEach var="app" items="${apps}">
				<td>${app.appId}</td>
			</c:forEach>
			<td>Tổng Xuất</td>
		</tr>
		<%
			int a = 0;
		%>
		<c:forEach var="report" items="${reportDetailList}">
			<%
				a++;
					int b = (a % 2) + 1;
			%>
			<tr class="hligh<%=b%> bo2">
				<td align="center">${report.date}</td>
				<td align="center">${report.openingBalance}</td>
				<td align="center">${report.income}</td>
				<%
					int payroll = 0;
				%>
				<c:forEach var="reportdetail" items="${report.apps}">

					<td align="center">${reportdetail.amount}</td>
				</c:forEach>
				<td align="center">${report.payroll}</td>
				<td align="center">${report.closingBalance}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div id="selectDownload" class="download">
	<select id="type" onclick="f1()">
		<option value="csv">csv</option>
<!-- 		<option value="pdf">pdf</option>
		<option value="xls">xls</option> -->
	</select>
</div>

<script>
	$(function() {
		$('#fromDate').datepicker({
			dateFormat : 'yy-mm-dd'
		});
		$('#toDate').datepicker({
			dateFormat : 'yy-mm-dd'
		});
	});
	function f1() {
		var startDate = $('#fromDate').val();
		var toDate = $('#toDate').val();
		var type = $('#type').val();
	 	window.location.href = "<%=getServletContext().getContextPath()%>/ajax/reportdetailjasper?startDate="
				+ startDate + "&toDate=" + toDate + "&type=" + type;
	<%-- 	$.ajax({
			type : "GET",
			url : "<%=getServletContext().getContextPath()%>/ajax/reportdetailjasper",
			data : "startDate=" + startDate + "&toDate="+ toDate+ "&type=" + type
			/* success : function() {
				alert("success");
			} */
		}); --%>
	}
</script>

<style>
.bo {
	background: #CCCCCC;
	color: black;
}

.bo td {
	border: 1px dotted white;
}

.bo2 { //
	background: #EFEFEF;
	color: black;
	text-align: right;
}

.bo2 td {
	border: 1px dotted #CCCCCC;
}
</style>