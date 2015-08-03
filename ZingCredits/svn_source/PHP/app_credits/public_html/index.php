
<?php
//echo "Hệ Thống Report đang maintain.";die();
if (isset($_REQUEST['params'])){
	header('Location: ./'.$_REQUEST['params']);
	die();
}
ini_set("session.cookie_domain","zing.vn");
define('BASE_PATH', realpath(dirname(__FILE__)));//public folder
define('ROOT_PATH', realpath(dirname(__FILE__).'/../'));//base folder

define('BASE_URL', 'http://'.$_SERVER['SERVER_NAME']);
define('APPLICATION_PATH', ROOT_PATH.'/application/');
define('LIB_PATH', ROOT_PATH.'/Lib/');
define('CONFIGURATION_PATH', APPLICATION_PATH.'etc/' );
define('DOCUMENT_ROOT', $_SERVER['DOCUMENT_ROOT']) ;
define('APP_ENV',$_SERVER["APP_ENV"]);

//For Models
define('MODEL_PATH', APPLICATION_PATH.'/models/');


//echo APP_ENV;exit;
set_include_path(LIB_PATH . PATH_SEPARATOR . get_include_path());

if(APP_ENV == 'production')
{
	set_include_path("/zserver/php/lib/corelib/" . PATH_SEPARATOR . get_include_path());
}
else
{
	set_include_path("/zserver/php/lib/corelib/" . PATH_SEPARATOR . get_include_path());
}

//set_include_path(LIB_PATH . PATH_SEPARATOR . get_include_path());

try
{
    require APPLICATION_PATH.'bootstrap.php';    
    
}
catch (Exception $exception) 
{
    echo '<html><body><center>'
       . 'An exception occured while bootstrapping the application.';
        echo '<br /><br />' . $exception->getMessage() . '<br />'
           . '<div align="left">Stack Trace:'
           . '<pre>' . $exception->getTraceAsString() . '</pre></div>';
    echo '</center></body></html>';
    exit(1);
}



try
{
      
	Zend_Controller_Front::getInstance()->dispatch();

}
catch(Exception $ex)
{
	$log_msg = "\nUncautch exception: "
		. $ex->getMessage()
		. $ex->getTraceAsString()."\n";
	$logger->log($log_msg, Zend_Log::ERR);
}

