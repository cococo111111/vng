<?php
require_once ZENDLIB_PATH.'Zing/HugeList/ServiceR.php';
require_once ZENDLIB_PATH.'Zing/HugeList/Service.php';
require_once ZENDLIB_PATH.'Zing/Name2ID/name2id.php';
require_once ZENDLIB_PATH.'Zing/stdprofile/stdprofile.php';

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
	public function testAction(){
	$config = Zend_Registry::get('appconf');
        $storageAdapter=new Zing_Auth_Storage_Session(array('host'=>$config->authorization->storage->host,'port'=>	$config->authorization->storage->port));
	$auth=new Zing_App_Auth();
	$auth->setStorage($storageAdapter);
	if ($auth->isLogged()){
	$identity=$auth->getIdentity();
	var_dump(($identity['username']));
	var_dump($this->checkUser($identity['username']));
	}
	var_dump('a');die();
	}	
	public function listAction(){
	$hugeList=new Zing_HugeList_ServiceR();
	$result =$hugeList->getEntriesCount();
	var_dump($result);die();
	}
	public function unameAction(){var_dump('a');die();
		$hugeList=new Zing_HugeList_ServiceR();
		$result =$hugeList->getSlice(0,10);
		$result=$result->data->value->entries;	
		$stdprofile = new Zing_STDProfile_ServiceR();
		$result = $stdprofile->multiGetData($result);	
		$result=$result->data;
		$keys= array_keys($result);

		for($i=count($keys)-1;$i>=0;$i--){
			$user=$keys[$i];		
			echo($result[$user]->userName).',';
		}
die();
	}	
private function checkUser($user){
	$config = Zend_Registry::get('appconf');

	$result=false;
	try{
	$name2ID = new Zing_Name2ID_ServiceR();	
	$id=$name2ID->getID($user);
	//		var_dump($id);
	$hugeList=new Zing_HugeList_ServiceR();
	$result =$hugeList->hasEntry($id);
	}
	catch(Exception $e){

	}
	return $result;		
	}
	public function addAction(){var_dump('a');die();
		$zinghuglib=new Zing_HugeList_Service();
		$name2ID = new Zing_Name2ID_ServiceR();
		$id=$name2ID->getID('redphx');var_dump($id);
		$zinghuglib->add($id);
		$hugeList=new Zing_HugeList_ServiceR();
		$result =$hugeList->hasEntry($id);
		var_dump($result);die();
		
	}

}

