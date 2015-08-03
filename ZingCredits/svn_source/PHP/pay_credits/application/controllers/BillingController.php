<?php
$GLOBALS['THRIFT_ROOT'] = ROOT_PATH.'/library/Apache/Thrift';
require_once $GLOBALS['THRIFT_ROOT'].'/packages/zingme_payment_type/zingme_payment_type_types.php';
require_once APPLICATION_PATH . '/pg_fe/TokenGenerator.php';
require_once APPLICATION_PATH . '/pg_fe/Billing.php';
require_once APPLICATION_PATH . '/pg_fe/Warmup.php';
require_once ROOT_PATH . '/util/DataFormat.php';
require_once APPLICATION_PATH.'/models/Balance.php';
require_once APPLICATION_PATH.'/models/AppInfo.php';
require_once ZENDLIB_PATH.'Zing/stdprofile/stdprofile.php';
require_once ZENDLIB_PATH.'Zing/HugeList/ServiceR.php';
require_once ZENDLIB_PATH.'Zing/Log/ScriberPayment.php'; 
require_once APPLICATION_PATH . '/pg_fe/LastedCache.php';
class BillingController extends Zend_Controller_Action {
     public $apps;	
     const DILIMITER = "-";
     private $_cache;
     private $userAppCache;	
     private $userID;
     private $tranx; 
    public function init(){
	try{
	if($this->_getParam('action')=='balance' || $this->_getParam('action')=='time'){return;}
	$config = Zend_Registry::get('appconf');
        $storageAdapter=new Zing_Auth_Storage_Session(array('host'=>$config->authorization->storage->host,'port'=>	$config->authorization->storage->port));
        //$auth=new Zing_App_Auth();
//        $auth->setStorage($storageAdapter);
	$auth = Zing_Authv2::getInstance();	
	$isLogged=false;
        if ($auth->isLogged()){
	    $isLogged=true;
            $identity=$auth->getIdentity();
            if(!$this->checkUser( $identity['username'])){
		$isLogged=false;	
	    }
	    else{
	      $this->userID= $this->uName2uID($identity['username']);
	      $this->view->userID=$this->userID;
	    }
        }
        if(!$isLogged){
	  echo "<p style='text-align:center'>Bạn chưa đăng nhập nên bạn không được phép truy cập hệ thống Ví Zing Me</p>";
	  exit();
        }

	//loidcit,nhutnguyenhong,namnq,hung.vo,andqt83
	if($config->maintain->flag=='1' &&($this->userID=='15948941' || $this->userID=='11298436' ||$this->userID=='3804482' ||$this->userID=='487796' ||$this->userID=='382344')){
	}
	else{
	if($config->maintain->flag=='1'){
	echo 'Hệ Thống ZingCredits đang được bảo trì. Mời bạn quay lại sau.';
	die();
	}
	}
	$this->userAppCache = Zing_UserApp_Cache_Adapter::factory();
      if($this->_getParam('action')=='billstatus'||$this->_getParam('action')=='ufora'){return;}
	if($this->_getParam('userID')==''){
	//sure that this is first time user request this page	
	ScriberPayment::sendLogStep1($this->userID,$this->_getParam("appID"));
	$this->parseTransaction();
	}
	
	
        if(!Zend_Registry::isRegistered('logger')){
            require_once ROOT_PATH . '/util/Logger.php';
            $logger = Logger::getLogger();
            Zend_Registry::set('logger', $logger);
        }	
	$this->view->appInfo=$this->getAppInfo($this->_getParam("appID"));
	$this->view->actionName = $this->getRequest()->getActionName();
	$this->_cache = Zing_Token_Cache_Adapter::factory();	
	}catch(Exception $e){
		$this->redirectError();
	
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
  private function redirectError(){
   $this->_redirect('/error/error?appID='.$this->_getParam("appID"));return;
  }
    private function parseTransaction(){
      //validate params
      if(!$this->validateParams()){
	      $this->redirectError();return;
      }
      $agentID=$this->_getParam("appID");
      $encodedData=$this->_getParam("data");

      $balance = new Balance();
      $tx=$balance->zingUnSignature($agentID,$encodedData,0);
      if(count($tx)==0){
      $this->redirectError();
      }
      $tx=$this->convertTranx($tx);
      $this->tranx=$tx;
	 //check whether user login and user request transaction are same
	if($this->tranx->userName!=$this->userID){
	  echo "<p style='text-align:center'>Bạn đang thực hiện giao dịch trái phép - Ví Zing Me</p>";
	  exit();
	}	

      //sure that this is second time when server parse trasaction
      ScriberPayment::sendLogStep2($this->userID,$this->_getParam("appID"),$tx->refID);
    }
    private function convertTranx($data){
      $tranx = new vng_zingme_payment_thrift_T_Transaction();
      $tranx->userName=$data[0];
      $tranx->refID=$data[1];
      $tranx->itemIDs=$data[2];
      $tranx->itemNames=$data[3];
      $tranx->itemQuantities=$data[4];
      $tranx->itemPrices=$data[5];
      $tranx->amount=$data[6];
      $tranx->txTime=$data[7];
      return $tranx;
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
    private function getAppInfo($appId){
      try{
      $appinfo = new AppInfo();
      return $appinfo->getAppInfo($appId);
      }
      catch(Exception $e){
	      return "";
      }
    }
    private function getCachedKey(){
      $key=$this->_getParam("appID");
      if($this->tranx!=null){
	$key.=$this->tranx->refID;
      }
      else{
	$key.=$this->_getParam('refID');
      }
      return $key;
    }
    private function generateToken(){
	//get from memcached first
	$key=$this->getCachedKey();
	$dataToken = $this->_cache->getTokenCache($key);
	if($dataToken==FALSE){
	  $tokenObj = new TokenGenerator();		
	  $token = $tokenObj->getToken($this->userID);	
	  $pToken = DataFormat::hexstr($token->pToken);
	  $this->view->pToken = $pToken;
	  $dataToken = array('billstat'=>0,'tokenkey'=>$pToken);//billstat:0-chua xac nhan,1-hoan thanh xac nhan
	  $this->_cache->setTokenCache($key,$dataToken);
          return true;
	}
	else{
		if($dataToken['billstat']=="1"){
		return false;
		}
		$this->view->pToken = $dataToken['tokenkey'];
		return true;
	}
    }
    private function updateTokenCached(){
	//get from memcached first
	$key=$this->getCachedKey();
	$dataToken = $this->_cache->getTokenCache($key);
	$pToken = $this->_getParam("pToken");
	
	if($dataToken!=FALSE){
		if($dataToken['billstat']==0 && ($dataToken['tokenkey']==$pToken)){
			
			$dataToken['billstat'] = 1;
			$this->_cache->setTokenCache($key,$dataToken);
		}
	}
	else{		
		$dataToken['tokenkey'] = $pToken;
		$dataToken['billstat'] = 1;
		$this->_cache->setTokenCache($key,$dataToken);	
	}
    }
	private function getAppCache($userid,$appID){
		return $this->userAppCache->getCache($userid.'_'.$appID);
		
	}
    public function requestformAction() {
        try{
		//validate params
		if(!$this->validateParams()){
			$this->redirectError();return;
		}
		
		
	

	    if(!$this->tranx){
	    $this->parseTransaction();
	    }
	    
		
	    $isUnFinish = $this->generateToken(); 
		if(!$isUnFinish){
			echo "<p style='text-align:center'>Bạn đã thực hiện giao dịch này rồi - Ví Zing Me</p>";
			exit();
		}
            $warmupObj = new Warmup();
            $warmupObj->warmup($this->userID);

        }catch(Exception $e){
            $this->redirectError();return;
        }

	$appCache = $this->getAppCache($this->userID,$this->view->appInfo->appID);
	if($appCache && $appCache=='1'){
		$this->_forward('bill',null,null,array('appID'=>$this->view->appInfo->appID,'data'=>$this->view->data,'pToken'=>$this->view->pToken));
		return;
	}
	$this->view->data=$this->_getParam("data");
	$this->view->title= "Ví Zing Me - Xác nhận thanh toán sản phẩm";
        $this->view->formaction = "/billing/bill";
	$this->view->userID=$this->userID;
	$this->view->tranx=$this->tranx;
        $this->render('billingex');
    }
    private function validateParams(){
		if($this->_getParam("appID")==null || 
		$this->_getParam("data")==null){
		return false;
		}
		return true;
    }

    public function balanceAction() {
   		$this->_helper->viewRenderer->setNoRender(true);
		$data = $this->_getParam("data");
		$agentID = $this->_getParam("appID");
		$result=0;
		try{
		    $balance = new Balance();
		    $parser = $balance->zingUnSignature($agentID,$data,1);
		    /*if($parser[0]!=$agentID){
		    echo '0';
		    return;
		    }
		    */
		    $res = $balance->getBalance($parser[0]);
		    if($res->code == 0){
		        $result = $res->currentBalance;
		    }else if($res->code == -3){
		        $result = 0;
		    }else{
		        $result = 0;
		    }
		}catch(Exception $e){
		    
		}
		echo  $result;
	}
 
    public function billAction() {
    	//validate params
	if(!$this->validateParams() || $this->_getParam("pToken")==null){
	  $this->redirectError();return;
	}       
        $token = new vng_zingme_payment_thrift_T_Token();
        $pToken = $this->_getParam("pToken");
        $token->pToken = DataFormat::hex2str($pToken);
	$this->updateTokenCached();
        $billing = new Billing($this->_getParam("appID"),$this->_getParam("data"), $token,$_SERVER['REMOTE_ADDR']);	
	$resCode=$billing->bill();       
	$this->view->billNo= $this->_getParam("refID");
	$this->view->title= "Ví Zing Me - Kết quả xác nhận thanh toán sản phẩm";
	$this->view->billresult= $resCode->code;
	$this->view->refID=$resCode->refNo;
	//sure that this is 3th time when server finish process user's bill
	ScriberPayment::sendLogStep3($this->userID,$this->_getParam("appID"),$this->_getParam("refID"),$resCode->code,$resCode->refNo);
	if($resCode->code!=0){
	$this->render('billingfail');
	}
	else{
	$this->view->success=1;
	$appCache = $this->getAppCache($this->userID,$this->_getParam("appID"));
	if($appCache && $appCache=='1'){
	$this->view->isLite=1;
	}
	$this->render('billingresult');
	}
    }
    public function billstatusAction(){
      $this->_helper->viewRenderer->setNoRender(true);
      $lastedCache=new LastedCache($this->userID,$this->_getParam("refID"));
      $code=$lastedCache->getTransactionStatus();
      $output='';
      switch ($code) {
	  case '101':
	  case '102':
	      $output=1;
	  break;
	  case '-103':
	  case '-104':
	      $output=0;
	      break;
      }    
      echo $output;
    }
   
    private function getIDByName($username){
      $name2ID = new Zing_Name2ID_ServiceR();
      $userID=$name2ID->getID($username);
      return $userID;
    }
    public function indexAction() {
        $this->render('billing');
    }
    public function timeAction(){
	echo 'Viet Nam GMT+7: '. date('Y-m-d H:i:s',time());
        date_default_timezone_set('UTC');
        echo '<br> UTC (GMT+0):    '.date('Y-m-d H:i:s',time());die();
    }
public function	uforaAction(){//ua:User App
$appCache = $this->getAppCache($this->userID,$this->_getParam("appID"));
if($this->_getParam("lite")=='1'){
$this->userAppCache->setCache($this->userID.'_'.$this->_getParam("appID"),"1");
echo '1';
}
if($this->_getParam("lite")=='0'){
$this->userAppCache->delete($this->userID.'_'.$this->_getParam("appID"));
echo '0';
}
die();
}
}

