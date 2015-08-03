<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <title>Gallery with Filter | BlueWhale Admin</title>
    <link rel="stylesheet" type="text/css" href="css/reset.css" media="screen" />
    <link rel="stylesheet" type="text/css" href="css/text.css" media="screen" />
    <link rel="stylesheet" type="text/css" href="css/grid.css" media="screen" />
    <link rel="stylesheet" type="text/css" href="css/layout.css" media="screen" />
    <link rel="stylesheet" type="text/css" href="css/nav.css" media="screen" />
    <link href="css/gallery.css" rel="stylesheet" type="text/css" />
    <link href="css/facebox.css" rel="stylesheet" type="text/css" />
    <link href="css/tabs.css" rel="stylesheet" type="text/css" />
    <!--[if IE 6]><link rel="stylesheet" type="text/css" href="css/ie6.css" media="screen" /><![endif]-->
    <!--[if IE 7]><link rel="stylesheet" type="text/css" href="css/ie.css" media="screen" /><![endif]-->
    <!-- BEGIN: load jquery -->
    <script src="js/jquery-1.6.4.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="js/jquery-ui/jquery.ui.core.min.js"></script>
    <script src="js/jquery-ui/jquery.ui.widget.min.js" type="text/javascript"></script>
    <script src="js/jquery-ui/jquery.ui.accordion.min.js" type="text/javascript"></script>
    <script src="js/jquery-ui/jquery.effects.core.min.js" type="text/javascript"></script>
    <script src="js/jquery-ui/jquery.effects.slide.min.js" type="text/javascript"></script>
    <!-- END: load jquery -->
    <script src="js/popup/jquery.facebox.js" type="text/javascript"></script>
    <script src="js/quick-sand/jquery.quicksand.js" type="text/javascript"></script>
    <script src="js/setup.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            setupGallery();
            setupLeftMenu();
			setSidebarHeight();
        });
        
        function loadBodyContent(url, subMenu) {
        	$.ajax({
        	    type: "GET",
        	    contentType: "application/text; charset=utf-8",
        	    url: url,
        	    data: { "subMenu": subMenu },
        	    success: function (msg) {
        	        $('#bodyContentId').html(msg);
        	    }
        	});appList
        }
    </script>
</head>
<body>
    <div class="container_12">
        <div class="grid_12 header-repeat">
            <div id="branding">
                <div class="floatleft">
                    <img src="img/logo.png" alt="Logo" /></div>
                <div class="floatright">
                    <div class="floatleft">
                        <img src="img/img-profile.jpg" alt="Profile Pic" /></div>
                    <div class="floatleft marginleft10">
                        <ul class="inline-ul floatleft">
                            <li>Hello <sec:authentication property="principal.username"/>
                            <li><a href="#">Config</a></li>
                            <li><a href="#">Logout</a></li>
                        </ul>
                        <br />
                        <span class="small grey">Last Login: 3 hours ago</span>
                    </div>
                </div>
                <div class="clear">
                </div>
            </div>
        </div>
        <div class="clear">
        </div>
        <div class="grid_12">
            <ul class="nav main">
                <sec:authorize ifAllGranted="ROLE_DASHBOARD"><li class="ic-dashboard"><a href="dashboard.html"><span>Dashboard</span></a> </li>
                </sec:authorize>
                
                <sec:authorize ifAllGranted="ROLE_ZINGME"><li class="ic-form-style"><a href="javascript:"><span>ZingMe</span></a> </li>
                </sec:authorize>
                
                <sec:authorize ifAllGranted="ROLE_APPS">
                <li class="ic-typography"><a href="typography.html"><span>Apps</span></a></li>
				</sec:authorize>
				
				<sec:authorize ifAllGranted="ROLE_COREAPPS">
				<li class="ic-charts"><a href="charts.html"><span>CoreApps</span></a></li>
				</sec:authorize>
				<sec:authorize ifAllGranted="ROLE_ZINGMOBILE">
                <li class="ic-grid-tables"><a href="table.html"><span>ZingMobile</span></a></li>
                </sec:authorize>
                <sec:authorize ifAllGranted="ROLE_PASSPORT">
                <li class="ic-gallery dd"><a href="javascript:"><span>Passport</span></a></li>
                </sec:authorize>
                <sec:authorize ifAllGranted="ROLE_ZTRACKING">
                <li class="ic-notifications"><a href="notifications.html"><span>ZTracking</span></a></li>
                </sec:authorize>
                <sec:authorize ifAllGranted="ROLE_FRIENDS">
                <li class="ic-notifications"><a href="notifications.html"><span>Friends</span></a></li>
                </sec:authorize>
                <sec:authorize ifAllGranted="ROLE_SETTINGS"><li class="ic-notifications"><a href="notifications.html"><span>Settings</span></a></li>
                </sec:authorize>
            </ul>
		
        </div>
        <div class="clear">
        </div>
        <div class="grid_2">
            <div class="box sidemenu">
                <div class="block" id="section-menu">
                    <ul class="section menu">
                    	<c:forEach var="menu" items="${menuList}">
				            <li> <a class="menuitem">${menu.menuName}</a>
				            	<ul class="submenu">
				            	<c:forEach var="subMenu" items="${menu.subMenu}">
						            <li><a onclick="javaScript:loadBodyContent('<%=getServletContext().getContextPath()%>/loadSubMenu','${menu.subMenu}');">${subMenu}</a> </li>
						        </c:forEach>
						        </ul>
				            </li>
				        </c:forEach>
                        <!--  li><a class="menuitem">Menu 1</a>
                            <ul class="submenu">
                                <li><a>Submenu 1</a> </li>
                                <li><a>Submenu 2</a> </li>
                                <li><a class="active">Submenu 3</a> </li>
                                <li><a>Submenu 4</a> </li>
                                <li><a>Submenu 5</a> </li>
                            </ul>
                        </li -->
                    </ul>
                </div>
            </div>
        </div>
        <div class="grid_10">
            <div class="box round first">
                
                    <h2>Gallery</h2>
                    <div class="block">
                    <div class="gallery-sand" id="bodyContentId">
                        
                    </div>
                </div>
            </div>
        </div>
        <div class="clear">
        </div>
    </div>
    <div class="clear">
    </div>
    <div id="site_info">
        <p>
            Copyright <a href="#">BlueWhale Admin</a>. All Rights Reserved.
        </p>
    </div>
</body>
</html>
