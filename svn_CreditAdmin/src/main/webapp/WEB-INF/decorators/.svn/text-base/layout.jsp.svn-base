<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<c:set var="contextPath" value="<%=getServletContext().getContextPath()%>"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="shortcut icon" HREF="images/zing_admin/icon.ico" />
<link
	href="${contextPath}/css/zing_admin/page.css"
	rel="stylesheet" type="text/css" />
<link
	href="${contextPath}/css/zing_admin/formapi.css"
	rel="stylesheet" type="text/css" />
<link
	href="${contextPath}/css/zing_admin/screen.css"
	rel="stylesheet" type="text/css" />
<link
	href="${contextPath}/css/zing_admin/jquery-calendar.css"
	rel="stylesheet" type="text/css" />
<title>Payment Administrator</title>
<script type="text/javascript"
	src="${contextPath}/js/jquery-1.4.2.js"></script>
<%-- 	<script type="text/javascript"
	src="<%=getServletContext().getContextPath()%>/js/jquery-1.10.2.js"></script>  --%>
<link
	href="${contextPath}/css/date_range_picker/jquery-ui-1.7.1.custom.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<%= getServletContext().getContextPath()%>/js/date_range_picker/jquery-ui-1.7.1.custom.min.js"></script>
<script type="text/javascript"
	src="${contextPath}/js/jquery-calendar.js"></script>
<%-- <script
	src="<%=getServletContext().getContextPath()%>/js/zingme_widget_ex_1.3.min.js"></script> --%>
	
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<div style="text-align: left;">
				<a title="Content Filter System"> <img id="logo"
					src="${contextPath}/images/zing_admin/logo_zingcredits.png"
					alt="Content Filter System" />
				</a>
			</div>
			<div style="width: 100%;" id="header-status" class="clear-block">
				<b>Hello <sec:authentication property="principal.username" />
				</b> <a href="<c:url value="/j_spring_security_logout" />">
					[Logout]</a> <a
					href="${contextPath}/personal/changepass">[Change
					Password]</a>
			</div>
		</div>

		<div id="container">
			<!-- left menu -->
			<div id="sidebar-left">
				<h2>Payment Administrator</h2>
				<div id="admin_menu">
					<ul class="menu">
						<sec:authorize ifAllGranted="ADMIN">
							<li class="expanded"><a>Ứng Dụng</a></li>
							<ul>
								<li class="expanded"><a
									href="${contextPath}/admin/appmanage">Quản
										lý Ứng Dụng</a></li>
								<li class="expanded"><a
									href="${contextPath}/admin/appsort">Sắp
										Xếp Ứng Dụng</a></li>
								<li class="expanded"><a
									href="${contextPath}/admin/registerapp">Thêm
										Ứng Dụng</a></li>
							</ul>
						</sec:authorize>
						<sec:authorize access="hasAnyRole('ADMIN', 'ROLE_CS')">
							<li class="expanded"><a>Người Dùng</a></li>
							<ul>
								<li class="expanded"><a
									href="${contextPath}/app/userbalance">Truy
										Vấn ZingXu</a></li>
								<sec:authorize access="hasAnyRole('SUPER_ADMIN')">
									<li class="expanded"><a
										href="${contextPath}/superadmin/useradjust">Thay
											Đổi ZingXu</a></li>
								</sec:authorize>
								<li class="expanded"><a
									href="${contextPath}/app/latesttranx">Giao Dịch Gần Nhất</a></li>
								<li class="expanded"><a
									href="${contextPath}/app/tranxbyuser">TK
										Giao Dịch</a></li>
							</ul>
						</sec:authorize>
						<sec:authorize access= "hasAnyRole('ADMIN', 'ROLE_REPORT')">
							<li class="expanded"><a>Thống kê App</a></li>
							<ul>
								<!--<li class="expanded"><a href="/app/complaint">Quản lý Khiếu Nại</a></li>-->

								<li class="expanded"><a
									href="${contextPath}/reportsum/tranxbyappsummary">Summary</a></li>
								<li class="expanded"><a
									href="${contextPath}/reportdetail/tranxbyapp">Chi
										Tiết</a></li>
								<sec:authorize ifAllGranted="ADMIN">
								<li class="expanded"><a href="${contextPath}/admin/tranxfilter">GD đang
										xử lý</a></li>
								<li class="expanded"><a href="${contextPath}/admin/autoreport">Auto
										Report</a></li>
								</sec:authorize>
							</ul>
							<sec:authorize ifAllGranted="ADMIN">
							<li class="expanded"><a>Thống kê nạp tiền</a></li>
							<ul>
								<li class="expanded"><a 
									href="${contextPath}/admin/paysummary">Thống kê nạp tiền</a></li>
							</ul>
							<li class="expanded"><a>Tài khoản</a></li>
							<ul>
								<li class="expanded"><a
									href="${contextPath}/admin/accountcontrol">Quản
										lý Tài Khoản</a></li>
								<li class="expanded"><a
									href="${contextPath}/admin/addnewuser">Thêm
										Tài Khoản</a></li>
							</ul>
							</sec:authorize>
						</sec:authorize>
						<!--   REPORT -->
						<sec:authorize ifAllGranted="CREDIT_REPORT">
							<li class="expanded"><a>Report</a></li>
							<ul>
								<li class="expanded"><a
									href="${contextPath}/report/reportzingxu">Report
										xuất Zingxu</a></li>
								<li class="expanded"><a
									href="${contextPath}/report/reportsummary">Tổng
										hợp nhập xuất ZingXu</a></li>
								<li class="expanded"><a
									href="${contextPath}/report/reportdetail">Chi
										tiết nhập xuất ZingXu</a></li>
							</ul>
						</sec:authorize>
					</ul>
				</div>
			</div>
			<!-- end left menu -->
			<div id="content">
				<div id="centerCont">
					<decorator:body />
				</div>
			</div>
		</div>
	</div>
</body>
</html>