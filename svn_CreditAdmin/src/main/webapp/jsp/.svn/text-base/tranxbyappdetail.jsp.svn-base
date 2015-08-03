<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<head>
<script type="text/javascript"
	src="<%=getServletContext().getContextPath()%>/js/jquery-1.4.2.js"></script>
</head>
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
			<tr id="row_${tranx.tranxId}" count="0" class="hligh${count.index%2+1}">
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
		
<%-- 		<tr>
			 For displaying Previous link except for the 1st page
		    <c:if test="${currentPage != 1}">
		        <td><a href="<%=getServletContext().getContextPath()%>/ajax/tranxbyapp/page=${currentPage - 1}">Previous</a></td>
		    </c:if>
				    For displaying Page numbers. The when condition does not display a link for the current page
		    <table border="1" cellpadding="5" cellspacing="5">
		        <tr>
		            <c:forEach begin="1" end="${noOfPages}" var="i">
		                <c:choose>
		                    <c:when test="${currentPage eq i}">
		                        <td>${i}</td>
		                    </c:when>
		                    <c:otherwise>
		                        <td><a href="<%=getServletContext().getContextPath()%>/ajax/tranxbyapp/page=${i}">${i}</a></td>
		                    </c:otherwise>
		                </c:choose>
		            </c:forEach>
		        </tr>
		    </table>
			For displaying Next link
		    <c:if test="${currentPage lt noOfPages}">
		        <td><a href="<%=getServletContext().getContextPath()%>/ajax/tranxbyapp/page=${currentPage + 1}">Next</a></td>
		    </c:if>
		</tr> --%>
		page: ${page}
	</tbody>
</table>

<script>

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
</script>