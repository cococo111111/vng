<?php
require_once APPLICATION_PATH.'/models/Balance.php';
require_once APPLICATION_PATH.'/models/Tranx.php';
require_once APPLICATION_PATH.'/models/AppInfor.php';
require_once ZENDLIB_PATH.'Zing/stdprofile/stdprofile.php';
require_once ZENDLIB_PATH.'Zing/HugeList/ServiceR.php';
class IndexController extends Zend_Controller_Action {
    const BALANCE = "BALANCE";
    const PURCHASE = "PURCHASE";
    const TOPUP = "TOPUP";
    const GIFT = "GIFT";
    const GIFT_NAME = "Zingme";
    const MERCHANT = "111111";
    const BALANCE_CODE = 10;
    const PURCHASE_CODE = 20;
    const TOPUP_CODE = 20;
    const GIFT_CODE = 30;
    const DEFAUL_PART = "YYYY-MM-dd hh:mm:ss";
    private $logger;
    private $userID;
    private $userproxy;
    public function init() {
	try{	
	$config = Zend_Registry::get('appconf');
        $storageAdapter=new Zing_Auth_Storage_Session(array('host'=>$config->authorization->storage->host,'port'=>	$config->authorization->storage->port));
        //$auth=new Zing_App_Auth();
       // $auth->setStorage($storageAdapter);
 	  $auth = Zing_Authv2::getInstance();
	 
        if ($auth->isLogged()){

            $identity=$auth->getIdentity();
            $this->userID = $this->uName2uID($identity['username']);
            $this->view->userID = $this->userID;             
		if(!$this->checkUser( $identity['username'])){
			echo "<p style='text-align:center'>Bạn không được phép truy cập hệ thống Ví Zing Me</p>";
			exit();
		}
        }

        $this->loadConfig(ROOT_PATH . '/config/config.ini');
        require_once ROOT_PATH . '/util/Logger.php';
	try{
		    $balance = new Balance();
		    $res = $balance->getBalance($this->userID);	
		    if($res->code == 0){
		        $this->view->balance = $res->currentBalance;
		    }else if($res->code == -3){
		        $this->view->balance = 0;
		    }else{
		        $this->view->errMsg = "fail";
		    }
		}catch(Exception $e){
		}
	}
	catch(Exception $e){
	  echo 'Hệ Thống Ví Zing Me đang được bảo trì. Mời bạn quay lại sau.';
	  die();
	}

    }
    private function uName2uID($uName){
      $name2ID = new Zing_Me_Profile_Profile();	
      $id=$name2ID->getUID($uName);
	if($id->error=='0'){
	return $id->value;
	}	
      return 0;
    }
	private function checkUser($user){
		$config = Zend_Registry::get('appconf');
		if($config->user->flag!=1){
			return true;
		}
		$result=false;
		try{
		
		$id=$this->uName2uID($user);
		$hugeList=new Zing_HugeList_ServiceR();
		$result =$hugeList->hasEntry($id);
		}
		catch(Exception $e){
			
		}
		return $result;		
	}
    private function loadConfig($file){
        if(!Zend_Registry::isRegistered('caching')){
            $config = new Zend_Config_Ini($file, 'caching');
            Zend_Registry::set('configuration', $config);
        }
    }
  
    public function indexAction() {
        try{
            $tranx = new Tranx();
            $txs = $tranx->getLatestTx($this->userID);
            $appInfo=new AppInfor();
	    $registedApps=$appInfo->getAllAppInfo();
        }catch(Exception $e){
        }
        $this->view->txs = $txs;
        $this->view->registedApps = $registedApps;
        $this->view->userID = $this->userID;

        $this->render('latesttx');
    }
  
}

