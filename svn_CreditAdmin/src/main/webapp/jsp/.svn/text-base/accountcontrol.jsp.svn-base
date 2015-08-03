<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<head>
<script type="text/javascript"
	src="<%=getServletContext().getContextPath()%>/js/jquery.boxy.js"></script>
<script type="text/javascript"
	src="<%=getServletContext().getContextPath()%>/js/jquery-1.10.2.js"></script>
<script type="text/javascript"
	src="<%=getServletContext().getContextPath()%>/js/date_range_picker/jquery-ui-1.7.1.custom.min.js"></script>
</head>

<table id="adminTable" class="mainTab" width="100%" border="0"
	cellpadding="0" cellspacing="0">
	<tr>
		<button id="admin" onclick="adminfunc()">ADMIN</button>
	</tr>
	<tbody>
		<tr>
			<td class="tabTit" width="5%">#</td>
			<td class="tabTit" width="20%">ID đăng nhập</td>
			<td class="tabTit" width="20%">Tên Admin</td>
			<td class="tabTit" width="10%">Action</td>
		</tr>
		<%
			int admin = 0;
		%>
		<c:forEach var="user" items="${userList}">
			<c:if test="${user.role == 'ADMIN'}">
				<%
					admin++;
							int a = (admin % 2) + 1;
				%>
				<tr class="hligh<%=a%>">
					<td align="center"><%=admin%></td>
					<td align="center">${user.userName}</td>
					<td align="center">${user.adminName}</td>
					<td align="center"><a nowrap=""
						href="<%=getServletContext().getContextPath()%>/admin/accountupdate/userId=${user.userName}">
							<img
							src="<%=getServletContext().getContextPath()%>/images/zing_admin/edit.png"
							alt="Edit" title="Edit">
					</a> <a href="#"> <img
							src="<%=getServletContext().getContextPath()%>/images/zing_admin/delete.png"
							alt="Delete" title="Delete"
							onclick="deleteUser('${user.userName}')">
					</a> <a href="#"> <img
							src="<%=getServletContext().getContextPath()%>/images/zing_admin/reset.png"
							alt="Reset Password" title="Reset Password"
							onclick="resetUserPass('${user.userName}')">
					</a></td>
				</tr>
			</c:if>
		</c:forEach>
	</tbody>
</table>
</br>
<table id="csTable" class="mainTab" width="100%" border="0"
	cellpadding="0" cellspacing="0">
	<tr>
		<button id="cs" onclick="csfunc()">CS</button>
	</tr>
	<tbody>
		<tr>
			<td class="tabTit" width="5%">#</td>
			<td class="tabTit" width="20%">ID đăng nhập</td>
			<td class="tabTit" width="20%">Tên Admin</td>
			<td class="tabTit" width="10%">Action</td>
		</tr>
		<%
			int cs = 0;
		%>
		<c:forEach var="user" items="${userList}">
			<c:if test="${user.role == 'ROLE_CS'}">
				<%
					cs++;
							int b = (cs % 2) + 1;
				%>
				<tr class="hligh<%=b%>">
					<td align="center"><%=cs%></td>
					<td align="center">${user.userName}</td>
					<td align="center">${user.adminName}</td>

					<td align="center"><a nowrap=""
						href="<%=getServletContext().getContextPath()%>/admin/accountupdate/userId=${user.userName}">
							<img
							src="<%=getServletContext().getContextPath()%>/images/zing_admin/edit.png"
							alt="Edit" title="Edit">
					</a> <a href="#"> <img
							src="<%=getServletContext().getContextPath()%>/images/zing_admin/delete.png"
							alt="Delete" title="Delete"
							onclick="deleteUser('${user.userName}')">
					</a> <a href="#"> <img
							src="<%=getServletContext().getContextPath()%>/images/zing_admin/reset.png"
							alt="Reset Password" title="Reset Password"
							onclick="resetUserPass('${user.userName}')">
					</a></td>
				</tr>
			</c:if>
		</c:forEach>
	</tbody>
</table>
</br>
<table id="reportTable" class="mainTab" width="100%" border="0"
	cellpadding="0" cellspacing="0">
	<tr>
		<button id="report" onclick="reportfunc()">REPORT</button>
	</tr>
	<tbody>
		<tr>
			<td class="tabTit" width="5%">#</td>
			<td class="tabTit" width="20%">ID đăng nhập</td>
			<td class="tabTit" width="20%">Tên Admin</td>
			<td class="tabTit" width="20%">Tên App</td>
			<td class="tabTit" width="10%">Action</td>
		</tr>
		<%
			int report = 0;
		%>
		<c:forEach var="user" items="${userList}">
			<c:if test="${user.role == 'ROLE_REPORT'}">
				<%
					report++;
							int c = (report % 2) + 1;
				%>
				<tr class="hligh<%=c%>">
					<td align="center"><%=report%></td>
					<td align="center">${user.userName}</td>
					<td align="center">${user.adminName}</td>
					<td><c:forEach var="app" items="${user.appList}"
							varStatus="loop">
							<c:choose>
								<c:when test="${app =='1'}">
									<c:out value="all app" />
								</c:when>
								<c:when test="${app =='2'}">
									<c:out value="all app - [zingpay & admin]" />
								</c:when>
								<c:otherwise>
									<c:out value="${app}" />
								</c:otherwise>
							</c:choose>
							<c:if test="${!loop.last}">,</c:if>
						</c:forEach></td>
					<td align="center"><a nowrap=""
						href="<%=getServletContext().getContextPath()%>/admin/accountupdate/userId=${user.userName}">
							<img
							src="<%=getServletContext().getContextPath()%>/images/zing_admin/edit.png"
							alt="Edit" title="Edit">
					</a> <a href="#"> <img
							src="<%=getServletContext().getContextPath()%>/images/zing_admin/delete.png"
							alt="Delete" title="Delete"
							onclick="deleteUser('${user.userName}')">
					</a> <a href="#"> <img
							src="<%=getServletContext().getContextPath()%>/images/zing_admin/reset.png"
							alt="Reset Password" title="Reset Password"
							onclick="resetUserPass('${user.userName}')">
					</a></td>
				</tr>
			</c:if>
		</c:forEach>
	</tbody>
</table>

<script>
	function adminfunc() {
		if (document.getElementById("adminTable").style.display == 'none') {
			$('#adminTable').css('display', '');
		} else {
			$('#adminTable').css('display', 'none');
		}
	}
	function csfunc() {
		if (document.getElementById("csTable").style.display == 'none') {
			$('#csTable').css('display', '');
		} else {
			$('#csTable').css('display', 'none');
		}
	}
	function reportfunc() {
		if (document.getElementById("reportTable").style.display == 'none') {
			$('#reportTable').css('display', '');
		} else {
			$('#reportTable').css('display', 'none');
		}
	}

	function deleteUser(userName) {
	
		if (confirm("Are you sure to delete account " + userName + "?") == true) {
			$
					.ajax({
						type : "GET",
						url : "<%=getServletContext().getContextPath()%>/admin/deleteaccount/userId="
								+ userName,
						data : "userId=" + userName,
						success : function() {
							window.location.href = "<%=getServletContext().getContextPath()%>/admin/accountcontrol";
						}
					});
		}
	}
	function resetUserPass(userName) {
		alert(userName);
		if (confirm("Are you sure to reset password for this account?\nDefault password: 1234") == true) {
			$.ajax({
				type : "POST",
				url : "<%=getServletContext().getContextPath()%>/admin/resetpass",
				data : "userName=" + userName,
				error : function() {
					alert("There's an unknown error occured!");
				},
				success : function() {
					alert("success");
				}
			});
			//window.location = '/app/appuser';
		}
	}
</script>