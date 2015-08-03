<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="<%=getServletContext().getContextPath()%>/js/jquery-1.10.2.js"></script>
<!-- <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.js"
	type="text/javascript"></script> -->
<script type="text/javascript"
	src="<%=getServletContext().getContextPath()%>/js/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="<%=getServletContext().getContextPath()%>/js/date_range_picker/jquery-ui-1.7.1.custom.min.js"></script>
<script type="text/javascript"
	src="<%=getServletContext().getContextPath()%>/js/date_range_picker/daterangepicker.jQuery.js"></script>
<script type="text/javascript"
	src="<%=getServletContext().getContextPath()%>/js/jquery-calendar.js"></script>
<title>Payment Administrator</title>

</head>
<strong>Payment Admin&gt;&gt; </strong>
Account Adjustion
<br>
Nhập thông tin mã user name, số tiền thay đổi(Ví dụ 100 hay -100) và
nguyên nhân thay đổi.
<br>
Có thể
<a href='<%=getServletContext().getContextPath()%>/app/userbalance'><u>Click
		vào đây</u></a>
để kiểm tra số dư tài khoản của User
<br>
<br>
<c:if test="${successStatus !=''}">
	<ul style='color:blue;'><c:out value='status: ${user.status}' /></ul>
</c:if>

<form:form action="useradjust" method="post" modelAttribute="user"
	id="useradjustForm">
	<table>
		<tr>
			<td>Nhập Mã User <span class='require'>*</span></td>
			<td><form:input id="userName" path="userName"
					class="validate[required]" /></td>
			<td><span
				style='background: #EFEFEF; color: #009DDC; padding-bottom: 2px; padding-right: 6px; padding-top: 4px;'>
					<form:checkbox path="userId" value="1" /> <strong>là
						UserID</strong>
			</span></td>
				<td><form:errors path="userName"/></td>
		</tr>
		<tr>
			<td>Tăng(Giảm) số Zing Xu: <span class="require">*</span></td>
			<td><form:input id="zingXu" path="zingXu"
					class="validate[required]" />Zing Xu</td>
		</tr>
		<tr>
			<td colspan=2><i>(Ví dụ nhập 100 để tăng 100 XingXu, nhập
					-100 để giảm 100 ZingXu)</i></td>
		</tr>
		<tr>
			<td style='height: 20px'></td>
		</tr>
		<tr>
			<td colspan='2'><input type='radio' id='type1' name='radio'
				 onclick='disabledForm()'> Nguyên nhân thay đổi do
				khiếu nại giao dịch:</td>
		</tr>
		<tr>
			<td colspan='2' style='border: 1px dashed #E2D7CE'>
				<table id='nnkngd'>
					<tr>
						<td>Mã giao dịch: <span class="require">*</span></td>
						<td><form:input path='tranxId' id='tranxId' size='14'
								class="validate[required]" /></td>
						<td>Ngày giao dịch <span class='require'>*</span></td>
						<td><form:input id='tranxDate' path='tranxDate'
								class="validate[required]" /></td>
					</tr>
					<tr>
						<td />
						<td colspan='3'><input type='button' onclick='getTranx()'
							value='Lấy Thông Tin'></td>
					</tr>
					<tr>
						<td>Nội dung:</td>
						<td colspan='3'><form:input type='text' path='reason1'
								id='reason1' size='43' /></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td style='height: 20px'></td>
		</tr>

		<tr>
			<td colspan='2'><input type='radio' id='type2' name='radio'
				checked="checked"	onclick='disabledForm()'>Nguyên nhân thay
				đổi khác:</td>
		</tr>
		<tr>
			<td colspan='2' style='border: 1px dashed #E2D7CE'>
				<table id='nnk'>
					<tr>
						<td>Nội dung: <span class="require">*</span></td>
						<td colspan='3'><form:input id='reason2' path='reason2'
								size='43' class="validate[required]" /></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td></td>
			<td><input type='submit' value='Submit' onclick='submitCheck()'></td>
		</tr>

	</table>

</form:form>
<script>
	$(document).ready(function() {
		$("#nnkngd").css('display', 'none'); 
		$("#tranxDate").datepicker({
			dateFormat : 'yy-mm-dd'
		});
		$("#useradjustForm").validate({
			rules : {
				userName : "required",
				zingXu : "required",
				tranxid : "required",
				fromdate : "required",
				reason2 : "required"
			},
			messages : {
				userName : "Please enter your userName",
				zingXu : "Please enter your zingXu",
				tranxid : "Please enter your tranxid",
				fromdate : "Please enter your fromdate",
				reason2 : "Please enter your reason2"
			},
			submitHandler : function(form) {
				form.submit();
			}
		});
	});

	function disabledForm() {
		if ($('#type2').attr('checked')) {
		 	$("#nnkngd").css('display', 'none'); 
			$("#nnk").css('display', '');
		}
		else if($("#type1").attr('checked')){
			$("#nnkngd").css('display', '');
			$("#nnk").css('display', 'none');
		}
	}
	function submitCheck() {
		if ($('#type1').attr('checked')) {
			$('#reason').val($('#reasonseen').val());
		} else {
			$('#reason').val($('#reason2').val());
		}
	}
	function getTranx() {
		var tranxID = $('#tranxId').val();
		var tranxDate = $('#tranxDate').val();
		$.ajax({
			type : "POST",
			url :"<%=getServletContext().getContextPath()%>/superadmin/tranxinfo" ,
			data :  "txId=" + tranxID + "&txTime=" + tranxDate,
		 	success : function(resp) {
				if(resp!=""){
					var data = jQuery.parseJSON( resp);
					$('#userName').val(data.userName);
					$('#zingXu').val(data.zingXu);
					$('#reason1').val(data.reason1);
				}
			}
		}); 
	}
</script>