<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<script type="text/javascript"
	src="<%=getServletContext().getContextPath()%>/js/jquery-1.10.2.js"></script>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.js"></script>
 <script type="text/javascript"
	src="<%=getServletContext().getContextPath()%>/js/jquery.validate.min.js"></script>

<strong>Payment Admin&gt;&gt; </strong> Giao Dịch Gần Nhất <br>
Nhập thông tin mã user name để in tất cả các giao dịch gần nhất.
 <c:set	var="contextPath" value="<%=getServletContext().getContextPath()%>"></c:set>
<c:if test="${userName !=null}">
	<c:set var="uName" value="${userName}"></c:set>
</c:if> <form:form action="${contextPath}/app/latesttranx" method="POST"
	id="frmlatest" commandName="user">
	<table>
		<tr>
			<td>Nhập Mã User <span class='require'>*</span></td>
			<td><form:input id="userName" path="userName" value="${uName}" /></td>
			<td><form:errors path="userName" /></td>
			<td><span
				style='background: #EFEFEF; color: #009DDC; padding-bottom: 2px; padding-right: 6px; padding-top: 4px;'>
					<form:checkbox id="userId" path="userId" value="1" /> <strong>là
						UserID</strong>
			</span></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="Submit" onclick="checkF()"></td>
		</tr>
	</table>
</form:form> </br>
<c:if test="${userName !=null}">
giao dịch của: ${userName}
</c:if>
<table class="mainTab" width="100%" border="0" cellpadding="0"
	cellspacing="0">
	<tbody>
		<tr height='30px' class="tabTit">
			<td width="13%">Thời gian</td>
			<td width="10%">Loại</td>
			<td width="5%">Mã GD</td>
			<td width="10%">$ Trước GD</td>
			<td width="10%">Giá trị GD</td>
			<td width="8%">Ứng dụng</td>
			<td width="10%">Tình trạng</td>
			<td width='35%'>Chi Tiết</td>
			<sec:authorize ifAllGranted="ADMIN">
				<td width='15%'>Action</td>
			</sec:authorize>
		</tr>
		<c:forEach var="tx" items="${latestTranx}" varStatus="count">
			<tr class="hligh${count.index%2+1}">
				<td align="center">${tx.tranxTime}</td>
				<td align="center">${tx.tranxType}</td>
				<td align="center">${tx.tranxId}</td>
				<td align="center">${tx.currentBalance}</td>
				<td align="center">${tx.amount}</td>
				<td align="center">${tx.agentId}</td>
				<td align="center">${tx.tranxStatus}</td>
				<td align="center">${tx.tranxDescription}</td>
				<sec:authorize ifAllGranted="ADMIN">
					<td><a href='#' onclick="f1('${tx.uId}', '${tx.tranxId}')">Delete</a></td>
				</sec:authorize>
			</tr>
		</c:forEach>

	</tbody>
</table>
<script>
	function checkF() {
		$("#frmlatest").validate({
			rules : {
				userName : {
					required : true,
					number : false
				}
			},
			messages : {
				userName : {
					required : "Please enter your userName",
					number : "userId must be a number"
				}
			},
			submitHandler : function(form) {
				form.submit();
			}
		});
	}
	function f1(uId, tranxId) {
		window.location = "/CreditAdmin/admin/deletelatesttranx?uId=" + uId
				+ "&tranxid=" + tranxId;
	}
</script>