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
<form:form method="POST" action="changepass" modelAttribute="user" id = "changePassForm">
	<b> ContentFilter >> </b>  Change Password
	<table id="changePassTable">
		<tr>
			<td class="hligh2" width="20%"><label for="viewname">Current
					Password</label><span class="require">*</span></td>
			<td class="hligh2"><form:input id="currentPass"
					class="validate[required]" path="currentPass" /></td>
					<td><form:errors path="currentPass" /></td></td> 
		</tr>
		<tr>
			<td class="hligh1" width="20%"><label for="viewname">New
					PassWord </label><span class="require">*</span></td>
			<td class="hligh1"><form:password id="passWord"
					class="validate[required]" path="passWord" /></td>
		</tr>
		<tr>
			<td class="hligh2" width="20%"><label for="viewname">New
					PassWord [Confirm]</label><span class="require">*</span></td>
			<td class="hligh1"><form:password id="passWordConfirm"
					class="validate[required]" path="passWordConfirm" /></td>
		</tr>
		<tr>
			<td><input type="submit" value="Submit" onclick="checkForm()"></td>
		</tr>
	</table>
</form:form>
<script>
	$(document).ready(function() {

		$("#changePassForm").validate({
			rules : {
				currentPass : "required",
				passWord : "required",
				passWordConfirm : {
					required : true,
					equalTo : "#passWord"
				},
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
</script>
