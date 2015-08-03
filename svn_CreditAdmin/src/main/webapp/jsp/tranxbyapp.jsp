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
Thống kê giao dịch người dùng trên hệ thống
<br>
<c:set var="contextPath"
	value="<%=getServletContext().getContextPath()%>"></c:set>

<form:form action="${contextPath}/reportdetail/tranxbyapp" method="POST"
	modelAttribute="trxbyapp">
	<table>
		<tr>
			<td>Tên Ứng Dụng <span class="require">*</span></td>
			<td><form:select path="appId" onchange="disablefn()">
					<c:forEach var="a" items="${apps}">
						<form:option value="${a.appId}">${a.appName}-(${a.appId}) </form:option>
					</c:forEach>
				</form:select></td>
		</tr>
		<tr>
			<td>Từ Ngày <span class='require'>*</span></td>
			<td><form:input path="fromDate" id="fromDate"
					value="${fromDate}" /></td>
			<td>Đến Ngày <span class='require'>*</span></td>
			<td><form:input path="toDate" id="toDate" value="${toDate}" /></td>
		</tr>

		<tr>
			<td>Trạng Thái</td>
			<td><form:select path="status" id="status">
					<form:option value="0" selected="selected">--Tất cả--</form:option>
					<form:option value="101">Game/app trả lời mã thành công</form:option>
					<form:option value="102">Đã gửi request REST cho game/app, nhưng
					không nhận được trả lời</form:option>
					<form:option value="-103">Game/app trả lời mã thất bại</form:option>
					<form:option value="-104">Lỗi mạng khi gửi request REST cho
					game/app</form:option>
				</form:select></td>
		</tr>
		<tr>
			<td id="tranxTypeTr">Loại giao dịch</td>
			<td><form:select path="tranxType" id="tranxType">
					<form:option value="0" selected="selected">--Tất cả--</form:option>
					<form:option value="101">Nạp tiền</form:option>
					<form:option value="1014">Sms</form:option>
					<form:option value="1016">Nạp tiền thẻ cào</form:option>
					<form:option value="1017">Hoàn tiền cho thẻ cào</form:option>
				</form:select></td>
			 	<td>
			 		Record Per Page: 
			 			<br>
						20 <form:radiobutton path="recordPerPage" value="20"  />&nbsp;&nbsp; 
						50 <form:radiobutton path="recordPerPage" value="50"/>&nbsp;&nbsp;
						 100 <form:radiobutton path="recordPerPage" value="100" checked="checked"/>
				</td>
		</tr>
		<tr>
			<td><input type="submit" value="Submit"></td>
		</tr>
		<tr>
		<form:hidden path="page" id="page" value="${page}" />
			<c:if test="${not empty tranxbyapp}">
				<c:choose>
				 	<c:when test="${page eq 0}"> 
				 		<c:if test="${recordCount eq recordperpage }">
				 		<td><input type="submit" value="Next" onclick="setNextPage()"></td></c:if>
					</c:when>
					<c:otherwise>
					<td><input type="submit" value="Previous" onclick="setPreviousPage()"></td>
					<c:if test="${recordCount eq recordperpage }">
				 		<td><input type="submit" value="Next" onclick="setNextPage()"></td></c:if>
					 </c:otherwise>
				</c:choose>
			</c:if>
		</tr>
	</table>
</form:form>

<div id="tranxDetailDiv"></div>
<table width="100%" border="0" cellspacing="0" cellpadding="0"
	class="mainTab">
	<tbody>
		<tr class="tabTit">
			<td width="10%">Ứng Dụng</td>
			<td width="13%">Thời gian</td>
			<td width="8%">User</td>
			<td width="8%">Loại</td>
			<td width="5%">Mã GD</td>
			<td width="9%">Giá trị GD</td>
			<td width="10%">Vật Phẩm</td>
			<td width="5%">Giá VP</td>
			<td width="15%">Số lượng</td>
			<td width="5%">RefId</td>
			<td width="10%">Tình trạng</td>
		</tr>

		<c:forEach var="tranx" items="${tranxbyapp}" varStatus="count">
			<tr id="row_${tranx.tranxId}" count="0"
				class="hligh${count.index%2+1}">
				<td width="10%" align="center">${tranx.appId}</td>
				<td width="13%" align="center">${tranx.tranxTime }</td>
				<td width="8%" align="center">${tranx.userName }</td>
				<td width="8%" align="center">${tranx.tranxType }</td>
				<td width="5%" align="center">${tranx.tranxId }</td>
				<td width="9%" align="center">${tranx.amount }</td>
				<td width="10%" align="center">${tranx.itemName }</td>
				<td width="5%" align="center">${tranx.itemPrice }</td>
				<td width="15%" align="center">${tranx.itemQuatities }</td>
				<td width="5%" align="center">${tranx.refId }</td>
				<td align='center'><a href="#"
					onclick="getTranxDetail('${tranx.tranxId}','${tranx.tranxTime}' )">Chi
						Tiết</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<script>
function setNextPage(){
	document.getElementById("page").value++;
}
function setPreviousPage(){
	document.getElementById("page").value -- ;
}
 	<%-- function getTranx(page) {
 		alert(page);
		var id = $('#appId').val();
		var fromDate = $('#fromDate').val();
		var toDate = $('#toDate').val();
		var status = $('#status').val();
		var tranxType = $('#tranxType').val();
		
		$.ajax({
			type : "POST",
			url : "<%=getServletContext().getContextPath()%>/ajax/tranxbyapp/page=" + page,
					data : "appId=" + id + "&fromDate=" + fromDate + "&toDate="
							+ toDate + "&status=" + status + "&tranxType="
							+ tranxType,
					success : function(resp) {
						$('#tranxDetailDiv').html(resp);
					}
				});
		return a; 
		window.location.href = "<%=getServletContext().getContextPath()%>/reportdetail/tranxbyapp?appId=" + id + "&fromDate=" + fromDate + "&toDate="
		+ toDate + "&status=" + status + "&tranxType="+ tranxType +"&page=" page ;
	} 
 --%>
	function disablefn() {
		var value = $('#appId').val();
		if (value == 'zing' || value == 'admin') {
			$('#status').val('0').attr('disabled', 'disabled');
			//$('#status').removeAttr('disabled');
			$('#tranxType').removeAttr('disabled');
		} else {
			$('#status').removeAttr('disabled');
			$('#tranxType').val('0').attr('disabled', 'disabled');
		}
	}
	
	function getTranxDetail(id, time) {
		var rowId = "row_"+id;
		var divId = "div_"+id;
		
		var a = $(document.getElementsByTagName("tr")[rowId]).attr("count");
		a++;
		$.ajax({
			type : "POST",
			url : "<%=getServletContext().getContextPath()%>/ajax/tranxbyappdetail",
					data : "tranxId=" + id + "&tranxTime=" + time,
					success : function(resp) {
						if ((a % 2) != 0) {
							$('#row_' + id).after(
									"<tr id ='tableId'> <td colspan='9'><div id = "+ divId + ">"
											+ resp + "</div></td></tr>");
							$(document.getElementsByTagName("tr")[rowId]).attr(
									"count", a);
						} else {
							$('#div_' + id).remove();
							$(document.getElementsByTagName("tr")[rowId]).attr(
									"count", a);
						}
					}
				});
	}
	
	$(function() {
		$('#fromDate').datetimepicker({
			dateFormat : 'yy-mm-dd'
		});
		$('#toDate').datetimepicker({
			dateFormat : 'yy-mm-dd'
		});

	});
	disablefn();
</script>