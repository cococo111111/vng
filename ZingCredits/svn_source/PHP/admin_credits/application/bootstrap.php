<?php

//Set up auto loader
//set_include_path("/zserver/php/lib/corelib/" . PATH_SEPARATOR . get_include_path());
//phpinfo();exit;
require_once "Zend/Loader.php"; 
@Zend_Loader::registerAutoload();


define('LIB_PACKAGES_DIR',  'Zing/Packages');
define('LOCAL_LIB_PACKAGES_DIR', LIB_PATH . 'Zing/Packages');
define('LOCAL_LIB_CF_SERVICE_DIR', LIB_PATH . 'Zing/ContentFilterService');

// Set up Thrift

$GLOBALS['THRIFT_ROOT'] =  'Apache/Thrift';
$GLOBALS['LIB_PACKAGES_ROOT'] = LIB_PACKAGES_DIR;

$GLOBALS['LOCAL_LIB_PACKAGES_DIR'] = LOCAL_LIB_PACKAGES_DIR;
$GLOBALS['LOCAL_LIB_CF_SERVICE_DIR'] = LOCAL_LIB_CF_SERVICE_DIR;
$GLOBALS['LOCAL_LIB_VPCL_DIR'] = LOCAL_LIB_VPCL_DIR;

//configuration
$configuration = new Zend_Config_Ini(APPLICATION_PATH . 'etc/' . APP_ENV. '.global.ini', APP_ENV);
Zend_Registry::set('configuration', $configuration);
// include define config file
require_once APPLICATION_PATH . 'etc/define.php';

// logging system
//$logger = new Zend_Log();
//$logger->addWriter(new Zend_Log_Writer_Stream($configuration->common->log->path));
//Zend_Registry::set('logger', $logger);
 

//set languages
require_once APPLICATION_PATH . 'etc/languages.php';
Zend_Registry::set('langvi', $vietnam);
unset($vietnam);

$frontController = Zend_Controller_Front::getInstance();
$frontController->setParam('env', APP_ENV);

$frontController->addControllerDirectory(APPLICATION_PATH . 'modules/default/controllers', 'default');
$default_route = new Zend_Controller_Router_Route(':action',
	array('controller' => 'index', 'action' => 'index', 'module' => 'default'));

$app_route = new Zend_Controller_Router_Route(':action',
	array('controller' => 'app', 'action' => 'index', 'module' => 'default'));

$viewpointchangelog_route = new Zend_Controller_Router_Route('viewpointchangelog/:action',
	array('controller' => 'Viewpointchangelog', 'action' => 'index', 'module' => 'default'));

$router = $frontController->getRouter();
//$router->addRoute('default',$default_route);
//$router->addRoute('app_route',$app_route);

$router->addRoute('viewpointchagelog',$viewpointchangelog_route);


$frontController->setRouter($router);

// enable error controller plugin
$frontController->throwExceptions(true);
/*
require_once APPLICATION_PATH . '/plugins/ProfilerPlugin.php';
$frontController->registerPlugin(new ProfilerPlugin());

require_once APPLICATION_PATH . '/plugins/CheckAdminPlugin.php';
$frontController->registerPlugin(new CheckAdminPlugin());
*/
Zend_Layout::startMvc(APPLICATION_PATH . 'layouts');
