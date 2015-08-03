<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	page contentType="text/html" pageEncoding="UTF-8"%>

<table class="mainTab" width="100%" border="0" cellpadding="0"
	cellspacing="0">
	<tbody>
		<tr height="30px" class="tabTit">
			<td align="center">Last Update</td>
			<td align="center">Result Code</td>
			<td align="center">Status</td>
			<td align="center">Message</td>
		</tr>
		<c:forEach var="txR" items="${tranxDetailList}">
			<tr>
				<td align="center">${txR.lastUpdate}</td>
				<td align="center">${txR.resultCode}</td>
				<td align="left">${txR.status}</td>
				<td align="center">${txR.message}</td> 
			</tr>
		</c:forEach>
	</tbody>
</table>