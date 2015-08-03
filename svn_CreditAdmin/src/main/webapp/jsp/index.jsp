<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>		
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link rel="shortcut icon" HREF="images/zing_admin/icon.ico" />
        <link href="<%=getServletContext().getContextPath()%>/css/zing_admin/page.css" rel="stylesheet" type="text/css"/>
        <link href="<%=getServletContext().getContextPath()%>/css/zing_admin/formapi.css" rel="stylesheet" type="text/css"/>
        <link href="<%=getServletContext().getContextPath()%>/css/zing_admin/screen.css" rel="stylesheet" type="text/css"/>
        <link href="<%=getServletContext().getContextPath()%>/css/zing_admin/jquery-calendar.css" rel="stylesheet" type="text/css"/>
        <title>Payment Administrator</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <script type="text/javascript" src="<%=getServletContext().getContextPath()%>/js/jquery-1.4.2.js"></script>
        <script type="text/javascript">
            var zme_avatar_width = 50;
            var zme_avatar_heigth = 50;
            var zme_avatar_tag = "span";
            var zme_avatar_size = "50";         
        </script>
        <link href="<%=getServletContext().getContextPath()%>/css/date_range_picker/jquery-ui-1.7.1.custom.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="<%=getServletContext().getContextPath()%>/js/date_range_picker/jquery-ui-1.7.1.custom.min.js"></script>
        <script type="text/javascript" src="<%=getServletContext().getContextPath()%>/js/jquery-calendar.js"></script>

<!--        <?php if ($this->logAction == true) {
            ?>
            <link href="<?= CSS_URL ?>/date_range_picker/ui.daterangepicker.css" rel="stylesheet" type="text/css"/>

            <script type="text/javascript" src="/js/date_range_picker/jquery-ui-1.7.1.custom.min.js"></script>
            <script type="text/javascript" src="/js/date_range_picker/daterangepicker.jQuery.js"></script>
        <?php }
        ?>-->

        <script type="text/javascript" src="<%=getServletContext().getContextPath()%>/js/md5/jquery.md5.js"></script>
    </head>	
    <body>
        <div id="wrapper">
            <div id="header">		
                <div style="text-align: left;">
                    <a title="Content Filter System">
                        <img id="logo" src="<%=getServletContext().getContextPath()%>/images/zing_admin/logo_zingcredits.png" alt="Content Filter System"/>
                    </a>
                </div>
                <div style="width: 100%;" id="header-status" class="clear-block">
                    Hi {{name}} !
<!--                    <?php if (!empty($this->name)) echo "Hi " . $this->name . "!  "; ?>
                    <?php
                    if ($this->showSignOut == true) {
                        ?>-->
                        <a href="/index/signout">[Logout]</a>
                        <a href="/index/changepass">[Change Password]</a>
<!--                        <?php
                    }
                    ?>-->
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
                                <li class="expanded"><a href="/app/index">Quản lý Ứng Dụng</a></li>
                                <li class="expanded"><a href="/app/appsort">Sắp Xếp Ứng Dụng</a></li>	
                                <li class="expanded"><a href="/app/registerapp">Thêm Ứng Dụng</a></li>
                            </ul>
                            </sec:authorize>
                           <sec:authorize ifAllGranted="ROLE_CS"> <li class="expanded"><a>Người Dùng</a></li> </sec:authorize>
                            <ul>
	                          <sec:authorize ifAllGranted="ROLE_CS">
	                           <li class="expanded"><a href="userbalance">Truy Vấn ZingXu</a></li></sec:authorize>
	                          <sec:authorize ifAllGranted="ROLE_CS">   
	                          <li class="expanded"><a href="latesttranx">Giao Dịch Gần Nhất</a></li></sec:authorize>
	                           <sec:authorize ifAllGranted="ROLE_CS">
	                               <li class="expanded"><a href="tranxbyuser">TK Giao Dịch</a></li></sec:authorize>
                            </ul>
                   <sec:authorize ifAllGranted="ADMIN">
                            <li class="expanded"><a>Thống kê App</a></li>
                            <ul>
                                <!--<li class="expanded"><a href="/app/complaint">Quản lý Khiếu Nại</a></li>-->
                                    
                                <li class="expanded"><a href="/app/tranxbyappsummary">Summary</a></li>
                                <!--	      <?php /*<li class="expanded"><a href="/app/tranxbyapp">Chi Tiết</a></li>*/?>-->
                                <li class="expanded"><a href="/app/tranxbyapp">Chi Tiết</a></li>
                                <li class="expanded"><a href="/app/tranxfilter">GD đang xử lý</a></li>
                                <li class="expanded"><a href="/app/autoreport">Auto Report</a></li>
                            </ul>
                            <li class="expanded"><a>Tài khoản Report</a></li>
                            <ul>
                                <li class="expanded"><a href="/app/appuser">Quản lý Tài Khoản</a></li>
                                <li class="expanded"><a href="/app/appnewuser">Thêm Tài Khoản</a></li>
                            </ul>
                            <li class="expanded"><a>Tài khoản Mod Admin</a></li>
                            <ul>
                                <li class="expanded"><a href="/app/adminuser">Quản lý Tài Khoản</a></li>
                                <li class="expanded"><a href="/app/adminnewuser">Thêm Tài Khoản</a></li>
                            </ul>
                            </sec:authorize>
                            
                            <!--              <?php
                                          /*<?php if($this->userrole<=2) {?><li class="leaf"><a href="log">Log</a></li><?php }?>
                                          <?php if($this->userrole==1) {?><li class="leaf last"><a href="user">User</a></li><?php }?>
                                          <? */?>-->
                        </ul>
                    </div>
                </div>
                <!-- end left menu -->
                <div id="content">
                    <div id="centerCont">                  
                    
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>