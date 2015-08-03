<?php

require_once APPLICATION_PATH.'/models/AppInfo.php';
require_once APPLICATION_PATH.'/models/Balance.php';
require_once APPLICATION_PATH.'/models/TReport.php';
require_once APPLICATION_PATH.'/models/Tranx.php';
require_once APPLICATION_PATH.'/models/Reporter.php';
require_once APPLICATION_PATH.'/models/AdminReportHandler.php';
require_once APPLICATION_PATH.'/models/AdminHandle.php';
require_once LIB_PATH.'Zing/stdprofile/stdprofile.php';

class AppController extends Zend_Controller_Action
{
	private $adminSig;
	private $emptyField='Nhập các trường đánh dấu (*)';
	public function init()
	{
	$config = Zend_Registry::get('configuration');
	$this->adminSig=$config->admin->signature->key;
	$this->auth();
	$this->_helper->viewRenderer->setNoRender();

	}
	private function auth(){
		$auth = Zing_Admin_Auth::getInstance();		
		$this->view->name = $auth->getIdentity()->name;
		if($auth->hasIdentity()){
		  $this->view->showSignOut=true;
		}
		else{
		 $this->view->showSignOut=false;
		 $this->_forward('signin','index');
		}
	}
	private function getIDByName($username){
	  $profile = new Zing_Me_Profile_Profile();
	  $uid=$profile->getUID($username);
          if($uid->error=='0'){
		return $uid->value;
	}
	return 0;

	}
	private function getNameByID($userid){
		$profile=new Zing_Me_Profile_Profile();
		$userprofile=$profile->getStdProfile($userid);
		if($userprofile->error==0){
			return $userprofile->value->userName;
		}
		return "0";	 
	}
	
	public function indexAction()
	{
	
	$this->view->container = $this->view->render("index/index.phtml");
	}
	
 

	public function userbalanceAction(){
	      $array_of_params = $this->_request->getParams ();
	      if(isset($array_of_params['validate'])){
		$this->view->validate=$array_of_params['validate'];
	      }
	      if(isset($array_of_params['messages'])){
		$this->view->messages=$array_of_params['messages'];
	      }
	      if(isset($array_of_params['balance'])){
		$this->view->balance=$array_of_params['balance'];
	      }
	      
	      $this->view->frmSearch = $this->view->render("index/app_userbalance.phtml");
	      $this->view->container = $this->view->render("index/index.phtml");
	}
	public function userbalancesubmitAction(){
	    if(!$this->isValidUserBalance()){	      
	      $this->_forward('userbalance',null,null,array('validate'=>$this->emptyField));	
		return;
	    }
	    try{
	    $array_of_params = $this->_request->getParams ();
	    $balanceServer=new Balance();
			
	    $response = $balanceServer->getBalance($this->getUserFromParam($array_of_params));
	    $this->_forward('userbalance',null,null,array('balance'=>$response));	
	    }
	    catch(Exception $e){
	      var_dump($e);die();
	    }

	}
	
	public function complaintAction(){
	  $this->view->frmSearch=$this->view->render('index/app_complainttable.phtml');
	  $this->view->container=$this->view->render('index/index.phtml');
	}
	public function tranxbyuserAction(){
	  $this->view->frmSearch=$this->view->render('index/app_tranxbyuser.phtml');
	  $this->view->container=$this->view->render('index/index.phtml');

	}
	public function tranxbyuser2Action(){
	  $this->view->frmSearch=$this->view->render('index/app_tranxbyuser_db2.phtml');
	  $this->view->container=$this->view->render('index/index.phtml');

	}
	
	public function tranxbyusersubmitAction(){

	  $array_of_params = $this->_request->getParams ();
	    $this->view->param=$array_of_params;
	  $tReport=new TReport();
	      if(isset($array_of_params['ajax']) && $array_of_params['ajax']==1){ 
		  $tranxs = $tReport->getTransStatus($array_of_params['tranxid'], $array_of_params['localtime']);
		//var_dump($tranxs);die();
		$this->view->tranxs=$tranxs;
		 
		  echo $this->view->render('index/app_tranxbyuser_detail.phtml');
		  exit();
	      }

	
	  if(!isset($array_of_params['fromdate']) || $array_of_params['fromdate']==''
	    || !isset($array_of_params['todate']) || $array_of_params['todate']==''){
	    
	    $array_of_params['fromdate']=date('Y').'-'.date('m').'-01 00:00';
	    $array_of_params['todate']=date("Y-m-d 23:59", strtotime('-1 second',strtotime('+1 month',strtotime(date('m').'/01/'.date('Y').' 00:00:00'))));	
	  }
	  
	    
	     $this->view->param=$array_of_params;
	    $fromdate= ($array_of_params['fromdate'].':00');
	    $todate= ($array_of_params['todate'].':59');	 
		$fd=strtotime($fromdate);
		$td=strtotime($todate);
		if(date('Y',$fd)!=date('Y',$td) || date('m',$fd)!=date('m',$td)){
			$validate[]='Chỉ cho phép thống kê trong cùng 1 tháng. Chọn lại thời gian thống kê';
			$this->view->validate=$validate;
		}
else{  
	    if(isset($array_of_params['tranxid']) && $array_of_params['tranxid']!=''){
	    try{
	     $tranxs =  $tReport->getTransByUser("",$array_of_params['tranxid'],$fromdate,$todate,0,10,0);

	      $this->view->tranxs=$tranxs;
	      
	     }catch(Exception $e){var_dump($e);die();}
	    }
	  else{
	    if(isset($array_of_params['username']) && $array_of_params['username']!=''){
	    try{
	      //check page paramater
	      if(! isset($array_of_params['page']) || $array_of_params['page']=='' || intval($array_of_params['page'])<1){
		$array_of_params['page']=1;
	      } 
	       
	      $count=100;
	      $index=intval($array_of_params['page']) * $count - $count;

	     $tranxs = $tReport->getTransByUser($this->getUserFromParam($array_of_params),0,$fromdate,$todate,$index,$count,$array_of_params['status']);	//  var_dump($tranxs);die();
	      $this->view->tranxs=$tranxs;
	     }catch(Exception $e){var_dump($e);die();}
	    }
	  }
}
	  

	  $this->view->frmSearch=$this->view->render('index/app_tranxbyuser.phtml');
	  $this->view->container=$this->view->render('index/index.phtml');

	}
public function tranxbyusersubmit2Action(){

	  $array_of_params = $this->_request->getParams ();
	    $this->view->param=$array_of_params;
	  $tReport=new TReport();
	      if(isset($array_of_params['ajax']) && $array_of_params['ajax']==1){ 
		  $tranxs = $tReport->getUserTransStatus($array_of_params['tranxid'], $array_of_params['localtime']);
		  $this->view->tranxs=$tranxs;
		 
		  echo $this->view->render('index/app_tranxbyuser_detail.phtml');
		  exit();
	      }

	
	  if(!isset($array_of_params['fromdate']) || $array_of_params['fromdate']==''
	    || !isset($array_of_params['todate']) || $array_of_params['todate']==''){
	    
	    
	  }
	  else{
	    
	    
	    $fromdate= ($array_of_params['fromdate'].' 00:00:00');
	    $todate= ($array_of_params['todate'].' 23:59:59');	   
	    if(isset($array_of_params['tranxid']) && $array_of_params['tranxid']!=''){
	    try{$reporter=new Reporter();
	     $tranxs = $reporter->getDetailTranx($array_of_params['tranxid'],$fromdate,$todate);
	   
	      $this->view->tranxs=array($tranxs);
	      
	     }catch(Exception $e){var_dump($e);die();}
	    }
	  else{
	    if(isset($array_of_params['username']) && $array_of_params['username']!=''){
	    try{
	      //check page paramater
	      if(! isset($array_of_params['page']) || $array_of_params['page']=='' || intval($array_of_params['page'])<1){
		$array_of_params['page']=1;
	      } 
	        $this->view->param=$array_of_params;
	      $count=100;
	      $index=intval($array_of_params['page']) * $count - $count;
		$reporter=new Reporter();

	     $tranxs = $reporter->getTransByUser($this->getIDByName($array_of_params['username']),$array_of_params['username'],$fromdate,$todate,$index,$count);	   

	      $this->view->tranxs=$tranxs;
	     }catch(Exception $e){var_dump($e);die();}
	    }
	  }
	  }

	  $this->view->frmSearch=$this->view->render('index/app_tranxbyuser_db2.phtml');
	  $this->view->container=$this->view->render('index/index.phtml');

	}
	
	public function tranxbyappAction(){
	  $array_of_params = $this->_request->getParams ();
	  $this->view->param=$array_of_params;
	  $appserver=new AppInfo();
	  $allApps=$appserver->getAllAppInfo();
	$allApps=$this->sortAppByID($allApps);
	$this->view->allApps=$allApps;
	  $this->view->frmSearch=$this->view->render('index/app_tranxbyapp.phtml');
	  $this->view->container=$this->view->render('index/index.phtml');

	}
	public function tranxbyappbkAction(){
	  $array_of_params = $this->_request->getParams ();
	  $this->view->param=$array_of_params;
	  $appserver=new AppInfo();
	  $allApps=$appserver->getAllAppInfo();
	$allApps=$this->sortAppByID($allApps);
	$this->view->allApps=$allApps;
	  $this->view->frmSearch=$this->view->render('index/app_tranxbyapp_bk.phtml');
	  $this->view->container=$this->view->render('index/index.phtml');

	}
	public function tranxbyappsummaryAction(){
	  $array_of_params = $this->_request->getParams ();
	  $this->view->param=$array_of_params;
	  $appserver=new AppInfo();
	  $allApps=$appserver->getAllAppInfo();
	$allApps=$this->sortAppByID($allApps);
	$this->view->allApps=$allApps;
	  $this->view->frmSearch=$this->view->render('index/app_tranxbyapp_summary.phtml');
	  $this->view->container=$this->view->render('index/index.phtml');

	}

	public function tranxbyappsubmitAction(){
	  $array_of_params = $this->_request->getParams ();
	  $this->view->param=$array_of_params;
	if(!isset($array_of_params['fromdate']) || $array_of_params['fromdate']==''
	    || !isset($array_of_params['todate']) || $array_of_params['todate']==''){
		$validate[]='Chọn thời gian thống kê. Chỉ cho phép thống kê trong cùng 1 tháng';
			$this->view->validate=$validate;
	    }
	else{
		try{
		$fromdate= ($array_of_params['fromdate'].':00');
		$todate= ($array_of_params['todate'].':59');
		$fd=strtotime($fromdate);
		$td=strtotime($todate);
		
		if(date('Y',$fd)!=date('Y',$td) || date('m',$fd)!=date('m',$td)){
			$validate[]='Chỉ cho phép thống kê trong cùng 1 tháng. Chọn lại thời gian thống kê';
			$this->view->validate=$validate;
		}
		else{
			if(($array_of_params['status']!="" && $array_of_params['status']!="0")
			&&($array_of_params['appid']=='0')){
			$validate[]='Chỉ cho phép thống kê giao dịch thất bại của 1 ứng dụng cụ thể.Chọn ứng dụng';
			$this->view->validate=$validate;
			}
else{
		$tReport=new TReport();
//check page paramater
	      if(! isset($array_of_params['page']) || $array_of_params['page']=='' || intval($array_of_params['page'])<1){
		$array_of_params['page']=1;
	      } 
	        $this->view->param=$array_of_params;
	      $count=100;
	      $index=intval($array_of_params['page']) * $count - $count;
		$tranxs = $tReport->getTransByApp($array_of_params['appid'],$fromdate,$todate,0,$index,$count,$array_of_params['status']);

		$this->view->tranxs=$tranxs;}
	}
		}catch(Exception $e){var_dump($e);die();}

	}
	$appserver=new AppInfo();
       $allApps=$appserver->getAllAppInfo();
	$allApps=$this->sortAppByID($allApps);
	$this->view->allApps=$allApps;
	if(isset($array_of_params['summary'])){
		 $this->view->frmSearch=$this->view->render('index/app_tranxbyapp_summary.phtml');
	}
	else{
		 $this->view->frmSearch=$this->view->render('index/app_tranxbyapp.phtml');
	}
	 
	  $this->view->container=$this->view->render('index/index.phtml');

	}
	public function tranxbyappbksubmitAction(){
	  $array_of_params = $this->_request->getParams ();
	  $this->view->param=$array_of_params;
	if(!isset($array_of_params['fromdate']) || $array_of_params['fromdate']==''
	    || !isset($array_of_params['todate']) || $array_of_params['todate']==''){
		echo 'from date and to date must be picked';
	    }
	else{
		try{
		$fromdate= ($array_of_params['fromdate'].' 00:00:00');
		$todate= ($array_of_params['todate'].' 23:59:59');
		
		if(! isset($array_of_params['page']) || $array_of_params['page']=='' || intval($array_of_params['page'])<1){
		$array_of_params['page']=1;
	      } 
		$appIDs=array();
		$appserver=new AppInfo();
		if($array_of_params['appid']=='0'){

       			$allApps=$appserver->getAllAppInfo();
	$allApps=$this->sortAppByID($allApps);

			foreach($allApps as $app){
				$appIDs[''.$app->appID]=$app->appName;
			}
		}
		else{
			$appIDs[$array_of_params['appid'].'']=$appserver->getAppName($array_of_params['appid']);
		}		
		$count=100;
	        $index=intval($array_of_params['page']) * $count - $count;
		$reporter=new Reporter();
		$result=array();
		$keys=array_keys($appIDs);
		foreach($keys as $appID){			
			$result[] = $reporter->getTransByApp($appID,$fromdate,$todate,$index,$count);		
		}
		
	        $this->view->param=$array_of_params;		
		$this->view->tranxs=$result;
		}catch(Exception $e){var_dump($e);die();}
	}
	$appserver=new AppInfo();
        $allApps=$appserver->getAllAppInfo();
	$allApps=$this->sortAppByID($allApps);
	$this->view->allApps=$allApps;
	$this->view->frmSearch=$this->view->render('index/app_tranxbyapp_bk.phtml');
	$this->view->container=$this->view->render('index/index.phtml');

	}


public function tranxbyappsummarysubmitAction(){
		$array_of_params = $this->_request->getParams ();
		$appserver=new AppInfo();
		$allApps=$appserver->getAllAppInfo();
	$allApps=$this->sortAppByID($allApps);
	$this->view->allApps=$allApps;
		
		if(!isset($array_of_params['fromdate']) || $array_of_params['fromdate']==''
		    || !isset($array_of_params['todate']) || $array_of_params['todate']==''){
			$array_of_params['fromdate']=date('Y-m-d', strtotime("now -1 day"));
			$array_of_params['todate']=date('Y-m-d', strtotime("now -1 day"));
			$array_of_params['radiogroup']=1;
		}
		{	$this->view->param=$array_of_params;
			$fromdate= ($array_of_params['fromdate'].' 00:00:00');
			$todate= ($array_of_params['todate'].' 23:59:59');
			$appIDs=array();
			$appserver=new AppInfo();
			if($array_of_params['appid']=='0'){
		
	       			$allApps=$appserver->getAllAppInfo();
	$allApps=$this->sortAppByID($allApps);

				foreach($allApps as $app){
					$appIDs[''.$app->appID]=$app->appName;
				}
			}
			else{
				$appIDs[$array_of_params['appid'].'']=$appserver->getAppName($array_of_params['appid']);
			}		
			$reporter = new Reporter();
			$result=array();
			$keys=array_keys($appIDs);
			foreach($keys as $appID){
				$subresult=array();
				switch ($array_of_params['radiogroup']){
				case 1:
				$subresult=$reporter->summaryDaily($appID,$fromdate,$todate);
				break;
				case 2:
				$subresult=$reporter->summary($appID,$fromdate,$todate);
				break;
				}
				if(count($subresult)>0 && $subresult[0]['appID']!=null){
				$result[]=$subresult;
				}
			}
			$this->view->data=$result;
			$this->view->frmSearch=$this->view->render('index/app_tranxbyapp_summary.phtml');	 
		  	$this->view->container=$this->view->render('index/index.phtml');
		}
	}

	private function isValidApp($app){
		if($app['appname']=='' || $app['appid']=='' || $app['appdesc']=='' || $app['appurl']=='' ||$app['applogo']=='' || $app['appresturl']==''){
			return false;
		}
		return true;
	}
	private function isValidUserAdjust(){
		$array_of_params = $this->_request->getParams ();
		if($array_of_params['username']=='' || $array_of_params['balance']=='' || $array_of_params['reason']==''){
			return false;
		}
		return true;
	}
	private function isValidUserBalance(){
		$array_of_params = $this->_request->getParams ();
		if($array_of_params['username']==''){
			return false;
		}
		return true;
	}
	private function getUserFromParam($param){
		if($param['isuid']=='1'){
		$this->view->userid=$param['username'];	
		$this->view->username=$this->getNameByID($param['username']);
		return $param['username'];
		}
		else{
		$this->view->userid=$this->getIDByName($param['username']);
		$this->view->username=$param['username'];				
		return $this->view->userid;
		}
	}
	public function lastedtranxAction(){
	 $array_of_params = $this->_request->getParams ();
	 $this->view->param=$array_of_params;
	    if(isset($array_of_params['username']) && $array_of_params['username']!=''){
	    try{
	      $tranxService=new Tranx();
		
	      $tranxs = $tranxService->getLatestTx($this->getUserFromParam($array_of_params));	   

	      $this->view->tranxs=$tranxs;
	     }catch(Exception $e){var_dump($e);die();}
	    }
	  
	  $this->view->frmSearch=$this->view->render('index/app_lastedTranx.phtml');
	  $this->view->container=$this->view->render('index/index.phtml');
	  
	}
	public function tranxinfoAction(){
		$array_of_params = $this->_request->getParams ();
		$fromdate= ($array_of_params['fromdate'].' 00:00:00');
		$todate= ($array_of_params['fromdate'].' 23:59:59');
		$tReport=new TReport();
		$tranx = $tReport->getTransByUser('',$array_of_params['tranxid'],$fromdate,$todate,0,10);
		if(count($tranx)==1){
			$tranx=$tranx[0];
			$appserver=new AppInfo();				
			echo 'array.push('.$tranx->amount.');';			
			echo 'array.push("Hoàn lại '.$tranx->amount.' Zing Xu cho giao dịch mua '.$tranx->itemNames.' game '.$appserver->getAppName($tranx->agentID).' (mã: '.$array_of_params['tranxid'].')");';
			echo 'array.push("'.$tranx->userName.'");';

		}
		else{
			echo 'array.push(-1);';	
			echo 'array.push("Không tồn tại giao dịch");';
			
		}
		die();
		
	
	}
public function appuserAction(){
	$adminReportHandler=new AdminReportHandler();
	$this->view->allUser = $adminReportHandler->getAllAdmin();
	$this->view->frmSearch=$this->view->render('index/user.phtml');
	$this->view->container=$this->view->render('index/index.phtml');
}
public function appnewuserAction(){
	$array_of_params = $this->_request->getParams ();
	if(isset($array_of_params['validate'])){
	$this->view->validate=$array_of_params['validate'];
	}
	$appserver=new AppInfo();
	$allApps=$appserver->getAllAppInfo();
	$allApps=$this->sortAppByID($allApps);
	$this->view->allApps=$allApps;
	$this->view->frmSearch=$this->view->render('index/createuser.phtml');
	$this->view->container=$this->view->render('index/index.phtml');
}
public function appnewusersubmitAction(){
	try{
	$userData["txtUserName"] = $this->_request->getPost('txtUserName');
	$userData["txtPass"] = $this->_request->getPost('txtPass');
	$userData["txtConfirmPass"] = $this->_request->getPost('txtConfirmPass');
	$userData["appid"] = $this->_request->getPost('appid');
	$userData["txtName"] = $this->_request->getPost('txtName');
	$this->view->userData=$userData;
	if($userData["txtUserName"]==""||$userData["txtPass"]==""||
	$userData["txtConfirmPass"]==""||$userData["appid"]==""
	||$userData["txtName"]==""){
	$validate[]='Enter required fields!';
	$this->_forward('appnewuser',null,null,array('validate'=>$validate));	
	return;
	}
	if($userData["txtPass"]!=$userData["txtConfirmPass"]){
	$validate[]='Password not match!';
	$this->_forward('appnewuser',null,null,array('validate'=>$validate));	
	return;
	}
	$adminReportHandler=new AdminReportHandler();
	$adminReportHandler->insertAdmin($userData);
	$this->_forward('appuser');
	}
	catch(Exception $e){var_dump($e);die();}
}


public function edituserAction(){
	try{
	$array_of_params = $this->_request->getParams ();
	if(isset($array_of_params['validate'])){
	$this->view->validate=$array_of_params['validate'];
	}
	$adminReportHandler=new AdminReportHandler();
	$admin=$adminReportHandler->getAdmin($array_of_params['userId']);
	$adminApps=$adminReportHandler->getAllAdminApp($array_of_params['userId']);
	$adminAppsMap=array();
	foreach($adminApps as $adminApp){
	$adminAppsMap[''.$adminApp['adminAppID']]='1';
	}
	
	$userData["txtUserName"] = $admin["adminID"];
	$userData["appid"] = $adminAppsMap;
	$userData["txtName"] = $admin["adminName"];
	$this->view->userData=$userData;
	$appserver=new AppInfo();
	$allApps=$appserver->getAllAppInfo();
	$allApps=$this->sortAppByID($allApps);
	$this->view->allApps=$allApps;
	$this->view->frmSearch=$this->view->render('index/edituser.phtml');
	$this->view->container=$this->view->render('index/index.phtml');
	}
	catch(Exception $e){var_dump($e);die();}
}
public function editusersubmitAction(){
	try{
	$userData["txtUserName"] = $this->_request->getPost('txtUserName');
	$userData["appid"] = $this->_request->getPost('appid');
	$userData["txtName"] = $this->_request->getPost('txtName');
	$this->view->userData=$userData;
	if($userData["txtUserName"]==""||$userData["appid"]=="" 
	||$userData["txtName"]==""){
	$validate[]='Enter required fields!';
	$this->_forward('edituser',null,null,array('validate'=>$validate,'userId'=>$userData["txtUserName"],'appid'=>$userData["appidold"]));	
	return;
	}
	$adminReportHandler=new AdminReportHandler();
	$adminReportHandler->updateAdmin($userData);
	$this->_forward('appuser');
	}
	catch(Exception $e){var_dump($e);die();}
}
public function deluserAction(){
	$array_of_params = $this->_request->getParams ();
	if($array_of_params["userId"]!=""){
	$adminReportHandler=new AdminReportHandler();
	$adminReportHandler->deleteAdmin($array_of_params["userId"]);
	}
	$this->_forward('appuser');	
}
public function resetpassAction(){
	$array_of_params = $this->_request->getParams ();
	if($array_of_params["userId"]!=""){
	$adminReportHandler=new AdminReportHandler();
	$adminReportHandler->resetpass($this->defaultPass,$array_of_params["userId"]);
	}
	die();	
}

public function adminuserAction(){
	$adminReportHandler=new AdminReportHandler();
	$this->view->allUser = $adminReportHandler->getAllCreditsAdmin();
	$this->view->frmSearch=$this->view->render('index/creditsuser.phtml');
	$this->view->container=$this->view->render('index/index.phtml');
}
public function adminnewuserAction(){
	$array_of_params = $this->_request->getParams ();
	if(isset($array_of_params['validate'])){
	$this->view->validate=$array_of_params['validate'];
	}	
	$this->view->frmSearch=$this->view->render('index/createcreditsuser.phtml');
	$this->view->container=$this->view->render('index/index.phtml');
}
public function adminnewusersubmitAction(){
	try{
	$userData["txtUserName"] = $this->_request->getPost('txtUserName');
	$userData["txtPass"] = $this->_request->getPost('txtPass');
	$userData["txtConfirmPass"] = $this->_request->getPost('txtConfirmPass');
	$userData["txtName"] = $this->_request->getPost('txtName');
	$this->view->userData=$userData;
	if($userData["txtUserName"]==""||$userData["txtPass"]==""||
	$userData["txtConfirmPass"]==""||$userData["txtName"]==""){
	$validate[]='Enter required fields!';
	$this->_forward('adminnewuser',null,null,array('validate'=>$validate));	
	return;
	}
	if($userData["txtPass"]!=$userData["txtConfirmPass"]){
	$validate[]='Password not match!';
	$this->_forward('adminnewuser',null,null,array('validate'=>$validate));	
	return;
	}
	$adminReportHandler=new AdminReportHandler();
	$adminReportHandler->insertCreditsAdmin($userData);
	$this->_forward('adminuser');
	}
	catch(Exception $e){var_dump($e);die();}
}

public function adminedituserAction(){
	try{
	$array_of_params = $this->_request->getParams ();
	if(isset($array_of_params['validate'])){
	$this->view->validate=$array_of_params['validate'];
	}
	$adminReportHandler=new AdminReportHandler();
	$admin=$adminReportHandler->getCreditsAdmin($array_of_params['userId']);

	
	
	$userData["txtUserName"] = $admin["adminID"];	
	$userData["txtName"] = $admin["adminName"];
	$this->view->userData=$userData;	
	$this->view->frmSearch=$this->view->render('index/creditsedituser.phtml');
	$this->view->container=$this->view->render('index/index.phtml');
	}
	catch(Exception $e){var_dump($e);die();}
}
public function admineditusersubmitAction(){
	try{
	$userData["txtUserName"] = $this->_request->getPost('txtUserName');
	$userData["txtName"] = $this->_request->getPost('txtName');
	$this->view->userData=$userData;
	if($userData["txtUserName"]==""||$userData["txtName"]==""){
	$validate[]='Enter required fields!';
	$this->_forward('adminedituser',null,null,array('validate'=>$validate,'userId'=>$userData["txtUserName"]));	
	return;
	}
	$adminReportHandler=new AdminReportHandler();
	$adminReportHandler->updateCreditsAdmin($userData);
	$this->_forward('adminuser');
	}
	catch(Exception $e){var_dump($e);die();}
}
public function admindeluserAction(){
	$array_of_params = $this->_request->getParams ();
	if($array_of_params["userId"]!=""){
	$adminReportHandler=new AdminReportHandler();
	$adminReportHandler->deleteCreditsAdmin($array_of_params["userId"]);
	}
	$this->_forward('adminuser');	
}
public function adminresetpassAction(){
	$array_of_params = $this->_request->getParams ();
	if($array_of_params["userId"]!=""){
	$adminReportHandler=new AdminReportHandler();
	$adminReportHandler->resetCreditspass($this->defaultPass,$array_of_params["userId"]);
	}
	die();	
}





public function sortAppByID($apps){
	foreach ($apps as $key => $row) {
	$weight[$key] = $row->appID;
	}
	array_multisort($weight, SORT_ASC, $apps);
	return $apps;
}
public function whitelistAction(){
$array_of_params = $this->_request->getParams ();
$appserver=new AppInfo();
$app = $appserver->getAppInfo($array_of_params['appid']);
$this->view->appName=$app->appName;
$this->view->appID=$app->appID;
$this->view->wl=$app->lswhitelist;//var_dump($app->lswhitelist);die();
$this->view->frmSearch=$this->view->render('index/whitelist.phtml');
$this->view->container=$this->view->render('index/index.phtml');
}
public function addwhitelistAction(){
$array_of_params = $this->_request->getParams ();
$appserver=new AppInfo();
$appserver->addIdToWhitelist($array_of_params['appid'],$array_of_params['u']);
$this->_forward('whitelist');return;
}

public function removewhitelistAction(){
$array_of_params = $this->_request->getParams ();
try{
//var_dump($array_of_params['appid'],$array_of_params['u']);die();

$appserver=new AppInfo();
$appserver->removeIdFromWhitelist($array_of_params['appid'],$array_of_params['u']);
$this->_forward('whitelist');return;
}
catch(Exception $e){var_dump($e);die();}
}





/*

public function batchgo3Action(){try{
	$treport=new TReport();
	$tranzFailure = $treport->getTransByApp('ntvv2-farm','2011-06-22 00:00:00','2011-06-22 23:59:59',0,10,300,'-103');

	$appserver=new AdminHandle();

echo 'count='.count($tranzFailure).'<br>';

echo '<table border="1"><tr><td>date</td><td>username</td><td>tranid</td><td>amount</td><td>response</td></tr>';

	foreach($tranzFailure as $tr){
	if($tr->userName=='twopigs.9x'){continue;}
	echo "<tr><td>".date('H:i, d/m/Y',$tr->txTime)."</td><td>".$tr->userName."</td><td>".$tr->txID.'</td><td>'.$tr->amount.'</td><td>'."Hoàn lại ".$tr->amount." Zing Xu cho giao dịch mua ".$tr->itemNames." game ".$tr->agentID." (mã: ".$tr->txID.")".'</td>';
	
//$response = $appserver->adjustUser($tr->userID,$tr->amount,$this->adminSig,"Hoàn lại ".$tr->amount." Zing Xu cho giao dịch mua \"".$tr->itemNames."\" game ".$tr->agentID." (mã: ".$tr->txID.")");
echo '<td>'.$response.'</td></tr>';
	}
	echo '</table>';
die();
}
catch(Exception $e){var_dump($e);die();}

}
*/
}
