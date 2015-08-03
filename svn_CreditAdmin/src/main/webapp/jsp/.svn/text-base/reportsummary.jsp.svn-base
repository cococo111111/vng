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
<form:form action="${contextPath}/report/reportsummary" method="POST"
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
			<td>ngày</td>
			<td>Tồn đầu kỳ</td>
			<td>Nhập</td>
			<td>Xuất</td>
			<td>Tồn cuối kỳ</td>
		</tr>
		<%
			int a = 0;
		%>
		<c:forEach var="report" items="${reportSummaryList}">
			<%
				a++;
					int b = (a % 2) + 1;
			%>
			<tr class="hligh<%=b%>">
				<td align="center">${report.date }</td>
				<td align="center">${report.openingBalance }</td>
				<td align="center">${report.income }</td>
				<td align="center">${report.payroll }</td>
				<td align="center">${report.closingBalance }</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
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
<div id="selectDownload" class="download">
	<select id="type" onclick="f1()">
		<option value="csv">csv</option>
		<option value="pdf">pdf</option>
		<option value="xls">xls</option>
	</select>
</div>


<!-- loading image -->
<div id="loading" class="modal">
	<!-- Place at bottom of page -->
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
		window.location.href = "<%=getServletContext().getContextPath()%>/ajax/reportsummaryjasper?startDate="
				+ startDate + "&toDate=" + toDate + "&type=" + type;
	}
</script>
<style>
.download {
	display: '';
}
/* Start by setting display:none to make this hidden.
   Then we position it in relation to the viewport window
   with position:fixed. Width, height, top and left speak
   speak for themselves. Background we set to 80% white with
   our animation centered, and no-repeating */
.modal {
	display: none;
	margin: auto;
	height: 200px;
	width: 200px;
	background: rgba(255, 255, 255, .8)
		url("<%=getServletContext().getContextPath()%>/images/pIkfp.gif") 50%
		50% no-repeat;
}

/* When the body has the loading class, we turn
   the scrollbar off with overflow:hidden */
body.loading {
	overflow: hidden;
}

/* Anytime the body has the loading class, our
   modal element will be visible */
body.loading .modal {
	display: block;
}

h1.hidden {
	visibility: hidden;
}
</style>