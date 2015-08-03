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

<c:set var="contextPath"
	value="<%=getServletContext().getContextPath()%>"></c:set>
Thống kê nạp tiền vào hệ thống (Đơn vị tính: ZingXu)
<form:form method="POST" action="${contextPath}/admin/paysummary"
	commandName="paysummary">
	<table>
		<tr>
			<td>Từ Ngày <span class='require'>*</span></td>
			<td><form:input type='text' id='fromDate' path='fromDate'
					value="${fromDateView }" /></td>
			<td>Đến Ngày <span class='require'>*</span></td>
			<td><form:input type='text' id='toDate' path='toDate'
					value="${toDateView }" /></td>
		</tr>

		<tr>
			<td></td>
			<td colspan='3'><input type='submit' name='summary'
				onclick="getPaySummary()" value='thống kê Summary'></td>
		</tr>
	</table>
</form:form>
<table class="mainTab" width="100%" border="0" cellpadding="0"
	cellspacing="0">
	<tbody>
		<tr class="tabTit">
			<td>Ngày</td>
			<td>Thẻ cào điện thoại</td>
			<td>ZingPay</td>
			<td>SMS</td>
			<td>Admin</td>
			<td>Reconcile</td>
			<td>Tất cả</td>
		</tr>
		<c:forEach var="payS" items="${paySummaryList}" varStatus="count">
			<tr class="hligh${count.index%2+1}">
				<td align="center">${payS.date}</td>
				<td align="center">${payS.telcoCard} (${payS.telcoPercent}%)</td>
				<td align="center">${payS.zingPay}  (${payS.zingPayPercent}%)</td>
				<td align="center">${payS.sms}  (${payS.smsPercent}%)</td>
				<td align="center">${payS.admin}  (${payS.adminPercent}%)</td>
				<td align="center">${payS.reconcile}  (${payS.reconcilePercent}%)</td>
				<td align="center">${payS.sum}</td>
			</tr>
		</c:forEach>
		<tr>
			<td align="center">Tổng Cộng</td>
			<td align="center">${telcoSum}  (${telcoSumPercent}%)</td>
			<td align="center">${zingpaySum}  (${zingpaySumPercent}%)</td>
			<td align="center">${smsSum}  (${smsSumPercent}%)</td>
			<td align="center">${adminSum}  (${adminSumPercent}%)</td>
			<td align="center">${reconcileSum}  (${reconcileSumPercent}%)</td>
			<td align="center">${totalSum}</td>
		</tr>
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