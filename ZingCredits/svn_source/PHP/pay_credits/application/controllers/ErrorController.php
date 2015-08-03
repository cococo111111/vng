<?php
require_once APPLICATION_PATH.'/models/AppInfo.php';
class ErrorController extends Zend_Controller_Action
{

    public function errorAction()
    {
        $errors = $this->_getParam('error_handler');
        
        switch ($errors->type) {
            case Zend_Controller_Plugin_ErrorHandler::EXCEPTION_NO_ROUTE:
            case Zend_Controller_Plugin_ErrorHandler::EXCEPTION_NO_CONTROLLER:
            case Zend_Controller_Plugin_ErrorHandler::EXCEPTION_NO_ACTION:
        
                // 404 error -- controller or action not found
                $this->getResponse()->setHttpResponseCode(404);
                $this->view->message = 'Page not found';
                break;
            default:
                // application error
                $this->getResponse()->setHttpResponseCode(500);
                $this->view->message = 'Application error';
                break;
        }
        
        // Log exception, if logger available
        if ($log = $this->getLog()) {
            $log->crit($this->view->message, $errors->exception);
        }
        
        // conditionally display exceptions
        if ($this->getInvokeArg('displayExceptions') == true) {
            $this->view->exception = $errors->exception;
        }
        
        $this->view->request   = $errors->request;
        $appID=$this->_getParam("appID");
        if($appID!=''){
        $this->view->appInfo=$this->getAppInfo($appID);
        }
    }
     private function getAppInfo($appId){
      try{
      $appinfo = new AppInfo();
      return $appinfo->getAppInfo($appId);
      }
      catch(Exception $e){
	      return "";
      }
    }

    public function getLog()
    {
        $bootstrap = $this->getInvokeArg('bootstrap');
        if (!$bootstrap->hasPluginResource('Log')) {
            return false;
        }
        $log = $bootstrap->getResource('Log');
        return $log;
    }
    public function scribeAction(){    
      //require_once ZENDLIB_PATH.'Zing/Log/ScriberPayment.php';       
      //$sendResult = ScriberPayment::sendLogStep1('nhutnguyenhong','123455','ntvv');
      //var_dump($sendResult);die();
      
    }
    public function identityAction(){
      $config = Zend_Registry::get('appconf');
        $storageAdapter=new Zing_Auth_Storage_Session(array('host'=>$config->authorization->storage->host,'port'=>	$config->authorization->storage->port));
        $auth=new Zing_App_Auth();
        $auth->setStorage($storageAdapter);

        if ($auth->isLogged()){

            $identity=$auth->getIdentity();
            var_dump($identity);die();
            $this->view->userNo = $identity['uin'];
            $this->userID = $identity['username'];	
            
        }
    }


}

