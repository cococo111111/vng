<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<head>
<script type="text/javascript">
	var jq = jQuery.noConflict();
</script>
<link
	href="<%=getServletContext().getContextPath()%>/css/zing_admin/page.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=getServletContext().getContextPath()%>/css/zing_admin/formapi.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=getServletContext().getContextPath()%>/css/zing_admin/screen.css"
	rel="stylesheet" type="text/css" />

<script
	src="<%=getServletContext().getContextPath()%>/js/zingme_widget_ex_1.3.min.js"></script>

<script type="text/javascript"
	src="<%=getServletContext().getContextPath()%>/js/jquery-1.4.2.js"></script>
</head>

<strong>Payment Admin&gt;&gt; </strong> 

<form:form method="POST" modelAttribute="app" action="/CreditAdmin/admin/appmanage/appId=${appId}"  >
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="mainTab">
        <tbody>
            <tr>
                <td width="20%" class="hligh2"><label for="viewname">AppId</label><span class="require">*</span></td>
                <td class="hligh2"><form:input path="appId" disabled="true"></form:input> </td>
            </tr>
            <tr>
                <td width="20%" class="hligh1"><label for="viewname">AppName</label><span class="require">*</span></td>
                <td class="hligh1"><form:input path="appName" class="required" maxlength="1000" style="width: 200px;"/></td>
            </tr>
            <tr>
                <td width="20%" class="hligh2"><label for="viewname">App Description</label><span class="require">*</span></td>
               <td class="hligh1"><form:input path="appDesc" class="required" maxlength="1000" style="width: 200px;"/></td>
            </tr>
            <tr>
                <td width="20%" class="hligh1"><label for="viewname">App Logo</label><span class="require">*</span></td>
                   <td class="hligh1"><form:input path="appLogo" class="required" maxlength="1000" style="width: 200px;"/></td>
            </tr>		
            <tr>
                <td width="20%" class="hligh2"><label for="viewname">App URL</label><span class="require">*</span></td>
                   <td class="hligh1"><form:input path="appUrl" class="required" maxlength="1000" style="width: 200px;"/></td>
            </tr>
            <tr>
                <td width="20%" class="hligh1"><label for="viewname">App Rest URL</label><span class="require">*</span></td>
                    <td class="hligh1"><form:input path="appRestUrl" class="required" maxlength="1000" style="width: 200px;"/></td>
            </tr>
            
            <tr>
                <td width="20%" class="hligh2"><label for="viewname">Is Generate New Key:</label></td>
                <td class="hligh2"><form:checkbox id = "status" path="status" value ="1" onclick= "f1();" /> </td>
                
            </tr>
               <tr>
                <td width="20%" class="hligh1"><label for="viewname">Is New Cypher:</label></td>
                <td class="hligh1">
                
                <form:checkbox id="isnew" path="isNewCypher" checked="${isNewCypher}" value ="1" onclick= "f1();" /> </td>
            </tr>
            <tr>
                <td class="hligh2"></td>
                <td class="hligh2"><input type="submit" class="button"></td>
            </tr> 
        </tbody>
    </table>
</form:form>

