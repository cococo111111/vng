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

<form:form method="POST" action="registerapp" commandName="app" id="registerappForm">
	<table width="100%" border="0" cellspacing="0" cellpadding="0"
		class="mainTab">
		<tbody>
			<tr>
				<td width="20%" class="hligh2"><label for="viewname">AppId</label><span
					class="require">*</span></td>
				<td class="hligh2"><form:input id="appId"
						class="validate[required]" path="appId" /></td>

			</tr>
			<tr>
				<td width="20%" class="hligh1"><label for="viewname">AppName</label><span
					class="require">*</span></td>
				<td class="hligh1"><form:input id="appName"
						class="validate[required]" path="appName" /></td>
			</tr>
			<tr>
				<td width="20%" class="hligh2"><label for="viewname">App
						Description</label><span class="require">*</span></td>
				<td class="hligh2"><form:input id="appDesc"
						class="validate[required]" path="appDesc" /></td>
			</tr>
			<tr>
				<td width="20%" class="hligh1"><label for="viewname">App
						Logo</label><span class="require">*</span></td>
				<td class="hligh1"><form:input id="appLogo"
						class="validate[required]" path="appLogo" /></td>
			</tr>
			<tr>
				<td width="20%" class="hligh2"><label for="viewname">App
						URL</label><span class="require">*</span></td>
				<td class="hligh2"><form:input id="appUrl"
						class="validate[required]" path="appUrl" /></td>
			</tr>
			<tr>
				<td width="20%" class="hligh1"><label for="viewname">App
						Rest URL</label><span class="require">*</span></td>
				<td class="hligh1"><form:input id="appRestUrl"
						class="validate[required]" path="appRestUrl" /></td>
			</tr>
			<tr>
				<td width="20%" class="hligh2"><label for="viewname">Is
						New Cypher:</label></td>
				<td class="hligh2"><form:checkbox path="isNewCypher" value="1" /></td>
			</tr>
			<%-- 		<tr>
				<form:errors path="appId" />
			</tr> --%>
			<tr>
				<td class="hligh1">
				<input type="submit" value="Submit" onclick="checkForm()"></td>
			</tr>

		</tbody>
	</table>
</form:form>

<script>
	$(document).ready(function() {
		$("#registerappForm").validate({
			rules : {
				appId : "required",
				appName : "required",
				appDesc : "required",
				appLogo : "required",
				appUrl : "required",
				appRestUrl : "required"
			},
			messages : {
				appId : "Please enter your appId",
				appName : "Please enter your appName",
				appDesc : "Please enter your appDesc",
				appLogo : "Please enter your appLogo",
				appUrl : "Please enter your appUrl",
				appRestUrl : "Please enter your appRestUrl"
			},
			submitHandler : function(form) {
				form.submit();
			}
		});
	});
</script>