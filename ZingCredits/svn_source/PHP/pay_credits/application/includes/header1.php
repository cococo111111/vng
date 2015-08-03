<?php
$this->publiclayoutPath = STATIC_PATH . "/layout";
$config = Zend_Registry::get('appconf');
$appcredit_url = $config->apps->credit->url;
$payment_url = $config->web->payment->url;
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><?= $this->title ?></title>
        <meta http-equiv="description" content="" />
        <meta http-equiv="keywords" content="" />
        <link href="<?=$payment_url?>/css/passport_screen_1.14.css" rel="stylesheet" type="text/css" />
        <link href="http://css.me.zdn.vn/zmjs/zm.ui-1.08.css" rel="stylesheet" type="text/css" />
        <link href="<?=$payment_url?>/css/mexu.css" rel="stylesheet" type="text/css" />
         <script src="<?=$payment_url?>/js/zmCore-1.26.min.js"></script>


        <script>
            var zme_avatar_width=75;
            var zme_avatar_heigth = 75;
            var zme_avatar_tag = "span";
            var zme_avatar_size = "160";
            document.domain = 'zing.vn';

   

        </script>
        <script src="http://widget.me.zing.vn/js/zingme_widget_ex_1.2.1.min.js"></script>

        <!--[if lte IE 7]>
        <style>
        #menuwrapper, #p7menubar ul a {height: 1%;}
        a:active {width: auto;}
        </style>
        <![endif]-->
    </head>
    <body>
<style>
.btsbmzc {
    background: none repeat scroll 0 0 #80A5D5;
    border: 1px solid #486DA4;
    color: #FFFFFF;   
    font-size: 11px;
    font-weight: bold;
    line-height: 16px;
    padding: 2px 6px;
    text-align: center;
    margin:5px;	
    cursor:pointer;

}
a.btsbmzc:hover{
color:white;
text-decoration:none;
}
.yel {
    color: #FF5C02;
    font-size: 16px;
    font-weight: bold;
}
</style>
        <div style="width:800px;margin:0 auto">
            <form name="frm" id="frm" method="post" action="<?= $this->formaction ?>">
                                    <div>
                                        <div>
                                            <div class="blog_login bgblogin">
                                                <div class="toplogin">
                                                    <p class="fl_left"><img src="<?=$payment_url?>/images/space.gif" alt=" " width="1" height="1" /></p>
                                                    <p class="fl_right"><img src="<?=$payment_url?>/images/space.gif" alt=" " width="1" height="1" /></p>
                                                </div>
                                                <div class="infologin">
						<img src="<?=$payment_url?>/images/logo_zingcredits.png"/>
                                                    <div class="loadframe" style='width:100%'>
                                                        <div class="msgnote"><strong>Ví Zing Me - Xác nhận thanh toán sản phẩm</strong></div>

                                                        <div class="blog_photo">
                                                            <div class="cntPhoto mgl">

                                                                <div class="heightLD">
                                                                    <!--Start MeXu-->
                                                                    <div id="layout_mexu">
                                                                        <div class="left_mexu">
<?php /* begin application logo&name render*/?>
<?php 
$appInfo=$this->appInfo;
if($appInfo->iconPath!=null){
?>

<table style='width:100%'>
	<tr>
		</td><td style='float:left'><img alt="" src="<?=$payment_url?>/images/<?=$appInfo->iconPath?>">		
		</td>
		<td style='color: green;font-weight: bold;text-align:left'>Bạn đang sử dụng <b>Ví Zing Me</b> để thanh toán cho ứng dụng <span style="color:red"><?=$appInfo->appName?></span>
	</tr>
</table>
<?php } /* end application logo&name render



                                                                           
                                                                            <div class="desc">
                                                                                <p class="name"><a href="#"><span class="name" rel="ZMED_<?=$_REQUEST['userID']?>?l=3"></span></a></p>
                                                                                <p class="gohome"><a href="http://me.zing.vn/<?=$_REQUEST['userID']?>" target='_parent'>Xem trang cá nhân</a></p>
                                                                            </div>
                                                                            <br class="clear" />
                                                                          */?>  
                                                                        </div>

