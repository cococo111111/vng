<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<strong>Payment Admin>> </strong>
Apps Management
<br>

<form id="frmSearch" name="frmSearch" method="get" action="registerapp">
	<input type="submit" value="Đăng Ký Ứng Dụng">
</form>
<br />
<script src="http://widget.me.zing.vnjs/zingme_widget_ex_1.3.min.js"></script>

<c:if test="${genKey !=''}">
	<c:out value='key: ${genKey}' />
</c:if>
<table class="mainTab" width="100%" border="0" cellpadding="0"
	cellspacing="0">
	<tbody>
		<tr>
			<td class="tabTit" width="3%">#</td>
			<td class="tabTit" width="17%">AppId</td>
			<td class="tabTit" width="10%">App Name</td>
			<td class="tabTit" width="20%">App RestURL</td>
			<td class="tabTit" width="40%">Key</td>
			<td class="tabTit" width="10%">Action</td>
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
				<td align="center"><a href='#'
					onclick="appInfo('${app.appID}','${app.appName}','${app.appDes}','${app.appURL}','${app.key1}','${app.key2}','${app.restURL}')">${app.appID}</a></td>
				<td align="center">${app.appName}</td>
				<td align="center">${app.restURL}</td>
				<td align="center">key1: ${app.key1} key2: ${app.key2}
				</td>
				<td>
					<a href="<%=getServletContext().getContextPath()%>/admin/whitelist?appId=${app.appID}&appName=${app.appName}">[WL]</a> 
						<c:choose>
						<c:when test="${app.isEnabled==0 }">
							<a href="#" onclick="f1(this, '${app.appID}');return false;" >[Enable]</a></c:when>
							<c:otherwise><a href="#" onclick="f1(this, '${app.appID}');return false;" >[Disable]</a></c:otherwise>
						</c:choose>
					<a	href="<%=getServletContext().getContextPath()%>/admin/change/appId=${app.appID}">[Update]</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>



<script>
	function appInfo(appid, appname, appdesc, appurl, appkey1, appkey2,
			appresturl) {

		var s = "Ví Zing Me - Registered Application:\n";
		s += "Application ID: " + appid + "\n";
		s += "Application Name: " + appname + "\n";
		s += "Application Description: " + appdesc + "\n";
		s += "Application URL: " + appurl + "\n";
		s += "Application Secret Key 1: " + appkey1 + "\n";
		s += "Application Secret Key 2: " + appkey2 + "\n";
		s += "Application Result Update URL: " + appresturl + "\n";
		alert(s);
	}
	function f1(obj, id) {
		if (obj.text == "[Disable]") {
			$
					.ajax({
						type : "GET",
						url : "<%=getServletContext().getContextPath()%>/admin/change/isEnable=0&appId="+ id,
						data : "appId=" + id,
						success : obj.innerHTML = '[Enable]'
					});
		} else if (obj.text == "[Enable]") {
			$
					.ajax({
						type : "GET",
						url : "<%=getServletContext().getContextPath()%>/admin/change/isEnable=1&appId="+ id,
						data : "appId=" + id,
						success : obj.innerHTML = '[Disable]'
					});
		}
	}
</script>