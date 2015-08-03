<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<input type="submit" value="All Syn Memcache and Database"
	onclick="reloadAllApp()">
<table class="mainTab" width="100%" border="0" cellpadding="0"
	cellspacing="0">
	<tbody>
		<tr>
			<td class="tabTit" width="5%">#</td>
			<td class="tabTit" width="10%">AppId</td>
			<td class="tabTit" width="20%">App Name</td>
			<td class="tabTit" width="5%">Vị trí</td>
			<td class="tabTit" width="2%"></td>
			<td class="tabTit" width="5%">Action</td>
		</tr>
		<%
			int a = 0;
		%>
		<c:forEach var="app" items="${apps}">
			<%
				a++;
					int b = (a % 2) + 1;
			%>
			<tr class="hligh<%=b%>">
				<td align="center"><%=a%></td>
				<td align="center">${app.appId}</td>
				<td align="center">${app.appName}</td>
				<td align="center" id="${app.appId}">${app.position}</td>
				<td> <c:if
						test="${app.isEnabale ==0}">
						<c:out value="(disable)"></c:out>
					</c:if></td>
				<td>&nbsp;&nbsp; <a href='javascript:edit("${app.appId}")'><img
						src='<%=getServletContext().getContextPath()%>/images/zing_admin/sort_number.png'></a>&nbsp;&nbsp;
					<a href='javascript:reload("${app.appId}")'><img
						src='<%=getServletContext().getContextPath()%>/images/zing_admin/reload.png'></a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<script>
	function edit(appid) {
		var position =  $('#' + appid).html();
		var namea = prompt("Nhập vị trí mới cho ứng dụng " + appid, position);
		if (namea != null && namea != "") {
			$.ajax({
				type : "POST",
				url : "<%=getServletContext().getContextPath()%>/admin/appsort",
				data : "appId=" + appid +"&sort="+namea,
				success: function(resp){
					alert(appid + " has been changed successfully");
					$('#' + appid).html(resp);
				}
			});
		}
	}
	
 	function reload(appid){
		var position =  $('#' + appid).html();
		$.ajax({
			type : "POST",
			url : "<%=getServletContext().getContextPath()%>/admin/reloadappsort",
			data : "appId=" + appid +"&sort="+namea,
			success: function(resp){
				alert(appid + " has been changed successfully");
				$('#' + appid).html(resp);
			}
		});
	}
 	
 	function reloadAllApp(){
		window.location = "<%=getServletContext().getContextPath()%>/admin/resynallapp";
	}
</script>