<?php
define('SES_DIR', APPLICATION_PATH.'models/Session');
$GLOBALS['THRIFT_ROOT'] = ZENDLIB_PATH.'Apache/Thrift';
$GLOBALS['SESSION_ROOT'] = SES_DIR;

class Bootstrap extends Zend_Application_Bootstrap_Bootstrap
{
}

