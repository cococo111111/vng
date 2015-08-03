<?php
/**
 * A Zend Controller Plugin to check user login to admin module.
 * @author  Huytq2
 */

require_once 'Zend/Controller/Plugin/Abstract.php';
//require_once LIB_PATH.'Zing/ContentFilterAdmin/Auth.php';

class CheckAdminPlugin extends Zend_Controller_Plugin_Abstract
{
	private $_logger = null;

	public function __construct()
	{
		$this->loadLogger();
	}
	
	public function dispatchLoopStartup(Zend_Controller_Request_Abstract $request)
	{
		$module		= strtolower($request->getParam('module'));
		$controller	= strtolower($request->getParam('controller'));
		$action = strtolower($request->getParam('action'));
		//echo $controller;exit;
		if ($controller == 'viewpointchangelog') {
			return;
		}
//		$auth = Zing_Admin_Auth::getInstance();
//		if (!$auth->hasIdentity() && $controller != 'auth') {
		//START XML Check
		$auth = Zing_Admin_Auth::getInstance();
		if (!$auth->hasIdentity() && $action != 'signin'){
		//END XML Check
			if(APP_ENV == 'production')
				{	
					echo "Production: Access denied<br/>";
					echo "<a href='signin'>Click here to log in</a>";
					echo " and comeback later";
					exit;
				}
				elseif(APP_ENV == 'development')
				{					
					echo "Dev: Access denied<br/>";
					echo "<a href='signin'>Click here to log in</a>";
					echo " and comeback later";
					exit;
				}			
		}
		

		$identity	= $auth->getIdentity();
//		if ($module != 'meadmin' && $identity->usertype > 1) {
//			header('location: /meadmin');
//		}
		// this part will be moved into helper after.
		$viewRenderer = Zend_Controller_Action_HelperBroker::getStaticHelper('viewRenderer'); 
		// setup view helpers
		$view = new Zend_View();
		$viewRenderer->setView($view);     
		if ($auth->hasIdentity()) {
			//$viewRenderer->view->msgLogin = "Hello {$identity->username} <a href=\"/admin/auth/logout\">[Logout]</a>";
		} else { 
			//BlockManager::setLayout('admin_login');
			/*
			if ($module == 'admin') {
				BlockManager::setLayout('admin_login');			
			} else {
				BlockManager::setLayout('meadmin_login');
			}
			*/
			//$viewRenderer->view->msgLogin = "&nbsp;";
		}
	}

	private function loadLogger()
	{
		$this->_logger = Zend_Registry::get("logger");		
	}
}