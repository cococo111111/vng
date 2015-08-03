<?php
$this->publiclayoutPath = STATIC_PATH . "/layout";
$tranx = $_REQUEST['tranx'];
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><?= $this->title ?></title>
        <meta http-equiv="description" content="" />
        <meta http-equiv="keywords" content="" />
        <link href="<?= $this->publiclayoutPath ?>/css/passport_screen_1.14.css" rel="stylesheet" type="text/css" />
        <link href="<?= $this->publiclayoutPath ?>/css/mexu.css" rel="stylesheet" type="text/css" />
        <?php /*
 <link type="text/css" href="<?= $this->publiclayoutPath ?>/css/redmond/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script src="<?= $this->publiclayoutPath ?>/js/jquery-1.3.2.min.js"></script>
        <script src="<?= $this->publiclayoutPath ?>/js/jquery-ui-1.7.2.custom.min.js"></script>
        <script src="<?= $this->publiclayoutPath ?>/js/game.js"></script>
	*/?>
        <script>
            var zme_avatar_width=75;
            var zme_avatar_heigth = 75;
            var zme_avatar_tag = "span";
            var zme_avatar_size = "160";

            //var zme_ex_en = 0

        </script>
        <script src="http://widget.me.zing.vn/js/zingme_widget_ex_1.3.js"></script>

        <!--[if lte IE 7]>
        <style>
        #menuwrapper, #p7menubar ul a {height: 1%;}
        a:active {width: auto;}
        </style>
        <![endif]-->
    </head>
    <body>
<?php /**
        <div id="mainpage">
*/?>
            <form name="frm" id="frm" method="post" action="<?= $this->formaction ?>">
                <input type="hidden" name="tranx">
                    <input type="hidden" name="userID" value="<?php echo $userID ?>">
                        <input type="hidden" name="userNo" value="<?php echo $userNo ?>">
                            <input type="hidden" name="submited" value="<?php echo $submited ?>">
                             <div id="container">
                                
                                           
 <div><div class="blog_login bgblogin">
                                                <div class="toplogin">
                                                    <p class="fl_left"><img src="http://img.me.zdn.vn/images/space.gif" alt=" " width="1" height="1" /></p>
                                                    <p class="fl_right"><img src="http://img.me.zdn.vn/images/space.gif" alt=" " width="1" height="1" /></p>
                                                </div>

<div class="infologin">                                                

                                                    <div class="loadframe">
                                                        <div class="msgnote"><strong>Quản lý Ví Zing Me</strong></div>

                                                        <div class="blog_photo">
                                                            <div class="cntPhoto mgl">

                                                                <div class="heightLD">
                                                                    <!--Start MeXu-->
                                                                    <div id="layout_mexu">


<table><tr><td width='15%'>



                    <div class="desc">
                                                                            <p class="name"><a href="#"><span class="name" rel="ZMED_<?= $userID ?>?l=3&id=1"></span></a></p>

                                                                                <p class="gohome"><font color='blackl'>Số dư tài khoản:</font><br>&nbsp;<span class='yel zc-iconzc2'><?php if($this->balance) echo  Zing_Util_DataFormat::formatMoney($this->balance); else echo 0; ?> </span></p>
                                                                            </div>                                                         
</td>
<td valign='top'>
<div>
<b>Ví Zing Me</b> hỗ trợ thanh toán cho các ứng dụng trên Zing Me. <br><b>Ví Zing Me</b> bảo đảm an toàn, tiện lợi, bảo mật thanh toán và nhanh chóng giúp bạn thỏa sức khám phá các ứng dụng tuyệt vời trên Zing Me. [<a href='http://me.zing.vn/apps/group?params=groupwall/299062' target='_blank'> Xem thêm ...</a>]
<br>
<span class='zc-arrowright'>Bạn có thể: </span>  <a  style="color:#466DA4; background: url(<?=$this->publiclayoutPath ?>/images/zxtozc.gif) no-repeat scroll 0 50% transparent;padding-left:17px;padding-top:2px" target='_blank' href='https://pay.zing.vn/zingxu/doizingxu.zingcredit.html'>      Chuyển Zing Xu về Ví Zing Me</a>     |     <a style="color: #466DA4; background: url(<?=$this->publiclayoutPath ?>/images/add-zx.png) no-repeat scroll 0 50% transparent;padding-top:2px;padding-left:20px" target='_blank' href='https://pay.zing.vn/zingxu/index.html'>  Nạp Zing Xu</a>    |    <a style="color:#466DA4;" href='http://me.zing.vn/apps/group?params=groupwall/299062' target='_blank'>Xem trợ giúp tại đây</a></div>
</td>
</tr>
</table>



<?php /*
                                                                        <div class="left_mexu">
                                                                            <div class="avatar">
                                                                                <span rel="ZMEA_<?= $userID ?>?width=50&height=50&l=1" id="1"></span>
                                                                            </div>
                                                                            <div class="desc">
                                                                                <p class="name"><a href="#"><span class="name" rel="ZMED_<?= $userID ?>?l=3&id=1"></span></a></p>
                                                                                <p class="gohome"><font color='blackl'>Số dư khả dụng:</font><br><font color='#7AA0D2'><?php echo Zing_Util_DataFormat::formatNumber($this->balance, ",") ?> ZingCoin</font></p>
                                                                            </div>
                                                                            <br class="clear" />
                                                                            <ul class="subcat_mexu">
                                                                               <li <?php
                                    if ($tranx == 'BALANCE') {
                                        echo 'class="active"';
                                    }
                                    ?>><a href="/index/index?tranx=BALANCE">ZingCoin của tôi</a></li>
                                                                                <li <?php
                                    if ($tranx == 'LATEST') {
                                        echo 'class="active"';
                                    } ?>><a href="/index/latest?tranx=LATEST">Xem giao dịch gần nhất</a></li>
 <li <?php if ($tranx == 'HELP') {
                                        echo 'class="active"';
                                    }
                                    ?>><a href="/index/index?tranx=BALANCE">Trợ giúp</a></li>
<?php /**                                                                                
<li <?php
                                                                                    if ($tranx == 'TOPUP') {
                                                                                        echo 'class="active"';
                                                                                    }
                                    ?>><a href="/index/tranxdetail?tranx=TOPUP&userID=<?= $userID ?>">Chuyển ZingXu sang ZingMe</a></li>

                                                                                    <li <?php
                                                                                    if ($tranx == 'GIFT') {
                                                                                        echo 'class="active"';
                                                                                    } ?>><a href="/index/tranxdetail?tranx=GIFT&userID=<?= $userID ?>">Tặng XU cho người khác</a></li>

                                                                                <li <?php
                                                                                    if ($tranx == 'HISTORY') {
                                                                                        echo 'class="active"';
                                                                                    }
                                    ?>><a href="/billing/history?userID=<?php echo $userID ?>">Kiểm tra ZingXu tren Payletter</a></li>

                                <li <?php
                                    if ($tranx == 'ALLTRANX') {
                                        echo 'class="active"';
                                    }?>><a href="/index/alltranx?tranx=ALLTRANX&userID=<?php echo $userID ?>">Tất cả giao dịch</a></li>
                                                                           </ul>
                                                                        </div>
*/?>

