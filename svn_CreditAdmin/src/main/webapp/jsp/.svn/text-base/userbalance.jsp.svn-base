<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="<%=getServletContext().getContextPath()%>/js/jquery-1.10.2.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="<%=getServletContext().getContextPath()%>/js/jquery.validate.min.js"></script>

<strong>Payment Admin&gt;&gt; </strong>
Account Balance
<br>
Nhập thông tin mã user để kiểm tra số ZingXu trong tài khoản.
<br>
Mặc định là nhập username.Check UserID để kiểm tra theo userID
<br>
 <c:set	var="contextPath" value="<%=getServletContext().getContextPath()%>"></c:set>
<form:form method="POST" action="${contextPath}/app/userbalance" commandName="user" id="userbalanceForm">
	<table>
		<tr>
			<td>Nhập Mã User <span class='require'>*</span></td>
			<td><form:input id="userName" path="userName" /></td>
			<td><form:errors path="userName"/></td>
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
</form:form>
<div id='balancediv' style='padding-left: 50px;'>
	<br> <br>
	<c:if test="${userB !=null}">
		<div
			style='background: #EFE8CE; width: 300px; border: 1px dashed red; text-align: center;'>
			Tài khoản : <b> 	${userB.userName} </b><br>
			UserId    : <b> ${userB.userId} </b> <br>
			Số Dư     : <b> ${userB.userBalance} </b>
				ZingXu 
		</div>
	</c:if>
</div>
<script type="text/javascript">
function checkF() {
	$("#userbalanceForm").validate(
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
}
</script>