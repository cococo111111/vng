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
<form:form method="POST" action="addnewuser" id="addnewuserForm"
	modelAttribute="user">
	<table>
		<tr>
			<td><label>Role: </label></td>
			<td><c:forEach var="r" items="${roleList}">
					<form:radiobutton onclick="refresh();" path="role"
						value="${r.name}" id="${r.name}" /> ${r.name}
			</c:forEach></td>
		</tr>
		<tr>
			<td><form:errors path="userName" /></td>
		</tr>
		<tr>
			<table id="adminTable">
				<tr>
					<td class="hligh2" width="20%"><label for="viewname">ID
							Đăng nhập</label><span class="require">*</span></td>
					<td class="hligh2"><form:input id="userName"
							class="validate[required]" path="userName" /></td>
				</tr>
				<tr>
					<td class="hligh1" width="20%"><label for="viewname">Mật
							khẩu</label><span class="require">*</span></td>
					<td class="hligh1"><form:password id="passWord"
							class="validate[required]" path="passWord" /></td>
				</tr>
				<tr>
					<td class="hligh2" width="20%"><label for="viewname">Mật
							khẩu [Confirm]</label><span class="require">*</span></td>
					<td class="hligh2"><form:password id="passWordConfirm"
							class="validate[required]" path="passWordConfirm" /></td>
				</tr>
				<tr>
					<td class="hligh1" width="20%"><label for="viewname">Tên
							Admin</label><span class="require">*</span></td>
					<td class="hligh1"><form:input id="adminName"
							class="validate[required]" path="adminName" /></td>
				</tr>
				<tr id="superAdmin">
					<sec:authorize access="hasAnyRole('SUPER_ADMIN')">
						<td class="hligh2" width="20%"><label for="viewname">Is Super Admin</label></td>
						<td class="hligh2"><form:checkbox path="superAdmin" value="1"/></td>
					</sec:authorize>
				</tr>
			</table>
		</tr>
		<tr>
			<table id="reportTable">
				<tr>
					<td class="hligh2" width="20%"><label for="viewname">quyền
							quản lí</label><span class="require">*</span></td>
					<td><form:radiobutton path="role" value="1" id="groupManage"
							onclick="choseRole()" /> quan li theo nhom:<form:radiobutton
							path="role" value="0" id="appManage" onclick="choseRole()" />
						quan li theo app:</td>
						<td><form:checkbox path="appList" value="credits_report"/>
									 <b> Xem Report(Xem thống kê đối soát các game quản lý)</b></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<table id='nhomTable'>
							<tr>
								<td><form:radiobutton path="role" value="1"
										onclick="choseRole()" /> Nhóm 1 : Xem tất cả</td>
								<td style='padding-left: 20px'><form:radiobutton
										path="role" value="2" onclick="choseRole()" /> Nhóm 2 : Xem
									tất cả - [ZingPay + Admin]</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<table id='udtb'>
						<%
							int i = 0;
						%>
						<c:forEach var="app" items="${appList}">
							<%
								if ((i % 3) == 0) {
							%>
							<tr>
								<%
									}
								%>
								<%
									i++;
								%>
								<td><form:checkbox path="appList" value="${app.appId}" />
									${app.appName} - (${app.appId})</td>
								<%
									if ((i % 3) == 0) {
								%>
							</tr>
							<%
								}
							%>
						</c:forEach>
					</table>
				</tr>
			</table>
		</tr>

		<tr>
			<td><input type="submit" value="Submit" onclick="checkForm()"></td>
		</tr>
	</table>
</form:form>
<script>
	$(document).ready(function() {
		$('#adminTable').css('display', 'none');
		$('#reportTable').css('display', 'none');
		$('#udtb').css('display', 'none');

		$("#addnewuserForm").validate({
			rules : {
				userName : "required",
				passWord : "required",
				passWordConfirm : {
					required : true,
					equalTo : "#passWord"
				},
				adminName : "required"
			},
			messages : {
				userName : "Please enter your userName",
				passWord : "Please enter your password",
				passWordConfirm : {
					required : "Please enter your password confirm",
					equalTo : "Please enter the same password as above"
				},
				adminName : "Please enter your userName"
			},
			submitHandler : function(form) {
				form.submit();
			}
		});
	});
	
	function refresh() {
		if ($('#ADMIN').attr('checked') || $('#ROLE_CS').attr('checked')) {
			$('#reportTable').css('display', 'none');
			$('#adminTable').css('display', '');
			$('#udtb').css('display', 'none');
			if ($('#ADMIN').attr('checked')) {
				$('#superAdmin').css('display', '');
			} else {
				$('#superAdmin').css('display', 'none');
			}			
		} else {
			$('#reportTable').css('display', '');
			$('#adminTable').css('display', '');
			$('#udtb').css('display', 'none');
		}
	}
	
	function choseRole() {
		/* 	if ($('input[name=manage]:checked').val() == "1") {
				$('#nhomTable').css('display', '');
				$('#udtb').css('display', 'none');
			}
			if ($('input[name=manage]:checked').val() == "2") {
				$('#nhomTable').css('display', 'none');
				$('#udtb').css('display', '');
			} */
		if ($('#groupManage').attr('checked')) {
			$('#nhomTable').css('display', '');
			$('#udtb').css('display', 'none');
		} else if ($('#appManage').attr('checked')) {
			$('#nhomTable').css('display', 'none');
			$('#udtb').css('display', '');
		}
	}
</script>