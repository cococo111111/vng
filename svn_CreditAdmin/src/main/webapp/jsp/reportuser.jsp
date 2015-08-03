<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link
	href="<%=getServletContext().getContextPath()%>/css/zing_admin/page.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=getServletContext().getContextPath()%>/css/zing_admin/formapi.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=getServletContext().getContextPath()%>/css/zing_admin/screen.css"
	rel="stylesheet" type="text/css" />

    <table class="mainTab" width="100%" border="0" cellpadding="0"
           cellspacing="0">
        <tbody>
            <tr>
                <td class="tabTit" width="5%">#</td>
                <td class="tabTit" width="20%">ID đăng nhập</td>
                <td class="tabTit" width="20%">Tên Admin</td>					
                <td class="tabTit" width="20%">Tên App</td>					
                <td class="tabTit" width="10%">Action</td>
            </tr>
                	<%
			int a = 0;
		%>
		<c:forEach var="user" items="${userList}">
			<%
				a++;
					int b = (a % 2) + 1;
			%>
			<tr class="hligh<%=b%>">
				<td align="center"><%=a%></td>
				<td align="center">${user.userName}</td>
				<td align="center">${user.adminName}</td>
				<td><c:forEach var="app" items="${user.appList}" varStatus="loop">
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
			</tr>
		</c:forEach>
        </tbody>
    </table>