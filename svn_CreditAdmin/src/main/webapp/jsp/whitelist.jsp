<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="<%=getServletContext().getContextPath()%>/js/jquery-1.10.2.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="<%=getServletContext().getContextPath()%>/js/jquery.validate.min.js"></script>

Bạn đang xem WhiteList cho ứng dụng:
<b>${appName}(${appId})</b>
<br>

<form:form method="post"
	action="/CreditAdmin/admin/whitelist?appId=${appId}&appName=${appName}"
	commandName="usert" id="whiteListForm">

	<table>
		<tr>
			<td>Nhập Mã User <span class='require'>*</span></td>
			<td><form:input id="userName" class="validate[required]"
					path="userName" /></td>
			<td><form:errors path="userName" /></td>
			<td><span
				style='background: #EFEFEF; color: #009DDC; padding-bottom: 2px; padding-right: 6px; padding-top: 4px;'>
					<form:checkbox id="userId" path="userId" value="1" /> <strong>là
						UserID</strong>
			</span></td>

		</tr>
		<tr>
			</br>
			<td><input type="submit" value="Submit" onclick="checkF()"></td>
		</tr>
	</table>
</form:form>
</br>
<table class="mainTab" border="0" cellpadding="0" cellspacing="0">
	<tbody>
		<tr>
			<td class="tabTit" width="5%">#</td>
			<td class="tabTit" width="20%">UserID</td>
			<td class="tabTit" width="20%">UserName</td>
			<td class="tabTit" width="10%">Action</td>
		</tr>
		<%
			int a = 0;
		%>
		<c:forEach var="a" items="${user}">
			<tr>
				<%
					a++;
				%>
				<td style="text-align: center"><%=a%></td>
				<td style="text-align: center">${a.userId }</td>
				<td style="text-align: center">${a.userName}</td>
				<td style="text-align: center"><a href="#"> <img
						src="<%=getServletContext().getContextPath()%>/images/zing_admin/delete.png"
						alt="Delete" title="Delete"
						onclick="deleteUser('${appId}','${a.userId}')">
				</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<script type="text/javascript">
function checkF() {
	$("#whiteListForm").validate(
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
 	function deleteUser(appid, userid, appName) {
 		if (confirm("Delete User "+ userid+" của ứng dụng " +appid +"?") == true) {
	 		window.location = "<%=getServletContext().getContextPath()%>/admin/removewhitelist?uId="+ userid + "&appid=" + appid+"&appName=" + appName;
 		}
	}
</script>
