<?php
// Define path to application directory
define('ROOT_PATH', realpath(dirname(__FILE__).'/../'));//base folder
define('ZENDLIB_PATH', ROOT_PATH.'/library/');
defined('APPLICATION_PATH')
    || define('APPLICATION_PATH', ROOT_PATH . '/application/');

// Define application environment
defined('APP_ENV')
    || define('APP_ENV', (getenv('APP_ENV') ? getenv('APP_ENV') : 'production'));

// Ensure library/ is on include_path
set_include_path(implode(PATH_SEPARATOR, array(
    realpath(APPLICATION_PATH . '/../library/'),
    get_include_path(),
)));

 require_once 'Zend/Loader/Autoloader.php';
        $loader = Zend_Loader_Autoloader::getInstance();
        $loader->registerNamespace(array('Zing_', 'Zend_', 'Crypt_'));

/** Zend_Application */
require_once 'Zend/Application.php';


// Create application, bootstrap, and run
$application = new Zend_Application(
    APP_ENV,
    APPLICATION_PATH . '/configs/application.ini'
);

//configuration
$configuration = new Zend_Config_Ini(APPLICATION_PATH . '/etc/' . APP_ENV. '.global.ini', APP_ENV);
Zend_Registry::set('appconf', $configuration);
if($configuration->maintain->flag=='1'){
echo 'Hệ Thống Ví Zing Me đang được bảo trì. Mời bạn quay lại sau.';
die();
}
define('STATIC_PATH', $configuration->web->payment->url);

$application->bootstrap()->run();
