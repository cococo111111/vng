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
Thống kê đối soát với ứng dụng trên Ví Zing Me:
<br>
 <c:set	var="contextPath" value="<%=getServletContext().getContextPath()%>"></c:set>
<form:form action="${contextPath}/report/reportzingxu" method="POST"
	id="reportForm" commandName="report">
<table>
	<tr>
		<td>Tên Ứng Dụng <span class="require">*</span></td>
		<td><form:select path="appIdGetList" id="appId" multiple="true" size="8">
				<form:option value="0">Tất Cả</form:option>
				<c:forEach var="app" items="${apps}">
					<form:option  value="${app.appId}">${app.appName}-
						(${app.appId})</form:option>
				</c:forEach>
			</form:select></td>
	</tr>
	<tr>
		<td>Từ Ngày <span class='require'>*</span></td>
		<td><form:input path="fromDate" id="fromDate" value="${fromDate}" /></td>
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
			<td>Tổng Zingxu nạp</td>
			<td colspan="${count}">ZingXu đã thanh toán theo ứng dụng</td>
		</tr>

		<tr class="bo">
			<td></td>
			<td>(Từ ZingPlay)</td>
			<c:forEach var="app" items="${appposts}">
				<td>${app}</td>
			</c:forEach>
		</tr>
		<c:forEach var="report" items="${reportList}">
			<tr class = "bo2">
				<td>${report.date}</td>
				<td>${report.income}</td>
				<c:forEach var="appzingxu" items="${report.apps}">
					<td>${appzingxu.amount}</td>
				</c:forEach>
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


<script>
	$(function() {
		$('#fromDate').datepicker({
			dateFormat : 'yy-mm-dd'
		});
		$('#toDate').datepicker({
			dateFormat : 'yy-mm-dd'
		});
	});
	function reportSubmit() {
		var appId = $('#appId').val();
		$('#loading').css('display', 'block');
		var startDate = $('#fromDate').val();
		var toDate = $('#toDate').val();
		$.ajax({
			type : "POST",
			url : "<%=getServletContext().getContextPath()%>/ajax/reportzingxu",
			data : "appId=" + appId + "&startDate=" + startDate + "&toDate="
					+ toDate,
			success : function(resp) {
				$('#loading').css('display', 'none');
				$('#resultReport').html(resp);
			}
		});
	}
	function f1() {
		var appId = $('#appId').val();
		var startDate = $('#fromDate').val();
		var toDate = $('#toDate').val();
		var type = $('#type').val();
	 	window.location.href = "<%=getServletContext().getContextPath()%>/ajax/reportzingxu?startDate="
				+ startDate + "&toDate=" + toDate + "&type=" + type +"&appId="+appId;
	}
</script>
<style>
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
</style>