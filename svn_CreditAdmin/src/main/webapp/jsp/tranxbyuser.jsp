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
<%--  <script type="text/javascript"
	src="<%=getServletContext().getContextPath()%>/js/jquery-1.10.2.js"></script> 
 --%><script type="text/javascript"
	src="<%=getServletContext().getContextPath()%>/js/jquery.validate.min.js"></script>
Thống kê giao dịch người dùng trên hệ thống
<br>
<c:set var="contextPath"
	value="<%=getServletContext().getContextPath()%>"></c:set>
<form:form action="${contextPath}/app/tranxbyuser" method="post"
	id="frmtxbyuser" commandName="user">
	<table>
		<tr>
			<td>Nhập Mã User <span class='require'>*</span></td>
			<td><form:input id="userName" path="userName" /></td>
				<td><form:errors path="userName" /></td>
			<td><span
				style='background: #EFEFEF; color: #009DDC; padding-bottom: 2px; padding-right: 6px; padding-top: 4px;'>
					<form:checkbox id="userId" path="userId" value="1" /> <strong>là
						UserID</strong>
			</span></td>
		</tr>
		<tr>
			<td>hay Mã giao dịch</td>
			<td><form:input id="txId" path="txId" /></td>
		</tr>
		<tr>
			<td>From</td>
			<td><form:input id="startTime" path="startTime" value="${fromDate }" /></td>
			<td>To</td>
			<td><form:input id="endTime" path="endTime" value="${toDate }" /></td>
		</tr>
		<tr>
			<td>Trạng Thái</td>
			<td><form:select path="txStatus">
					<form:option value="0" label="--Tất cả--" />
					<form:option value="101" label="Game/app trả lời mã thành công" />
					<form:option value="102"
						label="Đã gửi request REST cho game/app, nhưng không nhận được trả lời" />
					<form:option value="103" label="Game/app trả lời mã thất bại" />
					<form:option value="104"
						label="Lỗi mạng khi gửi request REST cho game/app" />
				</form:select></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="Thống kê chi tiết"></td>
		</tr>
	</table>
</form:form>
<c:if test="${userName !=null}">
giao dịch của: <b> ${userName}</b>
</c:if>
<table id="table1" width="100%" border="0" cellspacing="0"
	cellpadding="0" class="mainTab">
	<tbody>
		<tr height="30px" class="tabTit">
			<td width="10%">Thời gian</td>
			<td width="8%">Loại</td>
			<td width="5%">Mã GD</td>
			<td width="10%">$ Trước GD</td>
			<td width="9%">Giá trị GD</td>
			<td width="10%">Vật Phẩm</td>
			<td width="5%">Giá VP</td>
			<td width="10%">Số lượng</td>
			<td width="10%">refId</td>
			<td width="10%">Ứng dụng</td>
			<td width="10%">Tình trạng</td>
		</tr>

		<c:forEach var="tx" items="${tranxUserList}" varStatus="count">
			<tr id="row_${tx.txId}" count="0" class="hligh${count.index%2+1}">
				<td align="center">${tx.txTime}</td>	
				<td align="center">${tx.txType}</td>
				<td align="center">${tx.txId}</td>
				<td align="center">${tx.currentBalance}</td>
				<td align="center">${tx.amount}</td>
				<td align="center">${tx.itemName}</td>
				<td align="center">${tx.itemPrices}</td>
				<td align="center">${tx.itemQuantities}</td>
				<td align="center">${tx.refId}</td>
				<td align="center">${tx.agentId}</td>
				<td align='center'><a href="#"
					onclick="getTranxDetail(${tx.txId},'${tx.txTime}')">Chi Tiết</a></td>
			</tr>
		</c:forEach>


	</tbody>
</table>

<script>
$(function(){
	$("#frmtxbyuser").validate(
			{
				rules : {
					userName : {
						required : true,
						number:	false
					} 			
				},
				messages : {
					userName : {
						required: "Please enter your userName",
						number: "userId must be a number"
					}
				},
				submitHandler : function(form) {
					form.submit();
				}
			});
	$('#startTime').datetimepicker({
		dateFormat : 'yy-mm-dd'
	});
	$('#endTime').datetimepicker({
		dateFormat : 'yy-mm-dd'
	});
});
var a;
	function getTranxDetail(id, time ) {
		var rowId = "row_"+id;
		var divId = "div_"+id;
	
		var a = $(document.getElementsByTagName("tr")[rowId]).attr("count");
		a++;
		$.ajax({
			type : "POST",
			url : "<%=getServletContext().getContextPath()%>/ajax/tranxbyuserdetail",
			data :  "txId=" + id + "&txTime=" + time,
			success : function(resp) {
				if ((a%2)!=0){
					$('#row_'+id).after("<tr id ='tableId'> <td colspan='9'><div id = "+ divId + ">" + resp + "</div></td></tr>" );
					$(document.getElementsByTagName("tr")[rowId]).attr("count", a );
				}
				else {
					$('#div_'+id).remove();
					$(document.getElementsByTagName("tr")[rowId]).attr("count", a );
				}
			}
		}); 
		return a; 
	}

</script>