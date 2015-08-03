<?php
require_once APPLICATION_PATH.'/models/AppInfo.php';
require_once APPLICATION_PATH.'/models/TReport.php';
require_once APPLICATION_PATH.'/models/Reporter.php';
require_once APPLICATION_PATH.'/models/AdminReportHandler.php';

class AppController extends Zend_Controller_Action
{

	private $adminSig;
	private $emptyField='Nhập các trường đánh dấu (*)';
	public function init()
	{
	try{
	$this->view->uApps=$this->sortAppByID($this->getAppMap());
	$config = Zend_Registry::get('configuration');
	$this->adminSig=$config->admin->signature->key;
	$this->auth();
	$this->_helper->viewRenderer->setNoRender();
}catch(Exception $e){var_dump($e);die();}

	}
	private function auth(){
		$auth = Zing_Admin_Auth::getInstance();
		$this->view->userrole = $auth->getIdentity()->userrole;
		if(empty($this->view->userrole))
		$this->view->userrole = 4;
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
	  $name2ID = new Zing_Name2ID_ServiceR();
	  $userID=$name2ID->getID($username);
	  return $userID;
	}
	public function sortAppByID($apps){
	foreach ($apps as $key => $row) {
	$weight[$key] = $key;
	}
	array_multisort($weight, SORT_ASC, $apps);
	return $apps;
}
	private function getAppMap(){
		$auth = Zing_Admin_Auth::getInstance();
		$adminflg=$auth->getIdentity()->adminflg;
//var_dump($adminflg);die();
		$appserver=new AppInfo();
		$modelUser = new AdminReportHandler();
		$appIDs= $modelUser->getAdminApp($this->getUserID());

		$appMap=array();
		foreach($appIDs as $appID){
		if($appID['adminAppID']=='credits_report'){
		$this->view->app_credits_report=1;
		continue;
		}
		$appName = $appserver->getAppName($appID['adminAppID']);
		$appMap[$appID['adminAppID']]=$appName;
		}

		if($adminflg=='1'){
		$appserver=new AppInfo();
		$allApp = $appserver->getAllAppInfo();

		foreach($allApp as $appInfo){
		$appMap[''.$appInfo->appID]=$appInfo->appName;
		}
		}
		if($adminflg=='2'){
		$appserver=new AppInfo();
		$allApp = $appserver->getAllAppInfo();

		foreach($allApp as $appInfo){
		if($appInfo->appID=='admin' || $appInfo->appID=='zing'){
			continue;
		}
		$appMap[''.$appInfo->appID]=$appInfo->appName;
		}
		}
		return $appMap;
	}
	private function getUserID(){
		$auth = Zing_Admin_Auth::getInstance();
		$userid = $auth->getIdentity()->userid;
		return $userid;
	}
	public function tranxbyappAction(){
	  $this->_forward('tranxbyappsubmit');	
	}

	public function tranxbyappsubmitAction(){
	  $array_of_params = $this->_request->getParams ();
	  $this->view->param=$array_of_params;
	if(!isset($array_of_params['fromdate']) || $array_of_params['fromdate']==''
	    || !isset($array_of_params['todate']) || $array_of_params['todate']==''){
		$array_of_params['fromdate']=date('Y-m-d', strtotime("now -1 day"));
		$array_of_params['todate']=date('Y-m-d', strtotime("now -1 day"));
	    }
	{
		try{
		$fromdate= ($array_of_params['fromdate'].' 00:00:00');
		$todate= ($array_of_params['todate'].' 23:59:59');
		
		if(! isset($array_of_params['page']) || $array_of_params['page']=='' || intval($array_of_params['page'])<1){
		$array_of_params['page']=1;
	      } 
		$count=100;
	        $index=intval($array_of_params['page']) * $count - $count;
		$reporter=new Reporter();

	        $this->view->param=$array_of_params;
		$tranxs = $reporter->getTransByApp(self::getAppID(),$fromdate,$todate,$index,$count);		

		$this->view->tranxs=$tranxs;
		}catch(Exception $e){var_dump($e);die();}
	}
	
		$this->view->frmSearch=$this->view->render('index/app_tranxbyapp.phtml');
		$this->view->container=$this->view->render('index/index.phtml');

	}

	public function tranxbyappsummaryAction(){

	  $this->_forward('tranxbyappsummarysubmit');	

	}

	public function tranxbyappsummarysubmitAction(){
		$array_of_params = $this->_request->getParams ();		
		
		if(!isset($array_of_params['fromdate']) || $array_of_params['fromdate']==''
		    || !isset($array_of_params['todate']) || $array_of_params['todate']==''
			||!isset($array_of_params['appid'])){
			$array_of_params['fromdate']=date('Y-m-d', strtotime("now -1 day"));
			$array_of_params['todate']=date('Y-m-d', strtotime("now -1 day"));
			$array_of_params['radiogroup']=1;
			$array_of_params['appid']=0;
		}
		{	$this->view->param=$array_of_params;
			$fromdate= ($array_of_params['fromdate'].' 00:00:00');
			$todate= ($array_of_params['todate'].' 23:59:59');
			
			$appIDs=array();
			$appserver=new AppInfo();
			if($array_of_params['appid']=='0'){
	       			$appIDs=$this->view->uApps;
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

			if($array_of_params['export']=='csv'){
				$this->exportCSV($result);die();
			}
			else{
			$this->view->data=$result;
			$this->view->frmSearch=$this->view->render('index/app_tranxbyapp_summary.phtml');	 
		  	$this->view->container=$this->view->render('index/index.phtml');
			}
		}
	}
	private function exportCSV($report){
		  header("Content-type: application/csv");
header("Content-Disposition: \"inline; filename=credits_export.csv");  
		 echo "App,Ngày,Tổng số Tranx,Thành công,Thất bại,TimeOut,NetworkError,Đang giao dịch,ZingXu giao dịch,User\r\n";
		foreach($report as $databyapp){
			foreach($databyapp as $data){		
				$totalWorkingTranx=$data['totalTranx']-($data['totalSuccessTranx']+$data['totalFailureTranx']+$data['totalNetworkFailTranx']+$data['totalTimeoutTranx']);
		echo "\"".$data['appID'].'","'.$data['analyticDate'].'","'.($data['totalTranx']==0?'':$data['totalTranx']).'","'.($data['totalSuccessTranx']==0?'':$data['totalSuccessTranx']).'","'.($data['totalFailureTranx']==0?'':$data['totalFailureTranx']).'","'.($data['totalTimeoutTranx']==0?'':$data['totalTimeoutTranx']).'","'.($data['totalNetworkFailTranx']==0?'':$data['totalNetworkFailTranx']).'","'.($totalWorkingTranx==0?'':$totalWorkingTranx).'","'.$data['amount'].'","'.(isset($data['totalUsers'])?$data['totalUsers']:'')."\"";
		echo "\r\n";
			}
		}
	}
public function reportAction(){
$this->_forward('reportsubmit');	
}
public function reportsubmitAction(){
if(empty($this->view->app_credits_report)){die();}
$array_of_params = $this->_request->getParams ();		
		
		if(!isset($array_of_params['fromdate']) || $array_of_params['fromdate']==''
		    || !isset($array_of_params['todate']) || $array_of_params['todate']==''
			||!isset($array_of_params['appid'])){
			$array_of_params['fromdate']=date('Y-m-d', strtotime("now -1 day"));
			$array_of_params['todate']=date('Y-m-d', strtotime("now -1 day"));
		}
		{	
			$fromdate= ($array_of_params['fromdate'].' 00:00:00');
			$todate= ($array_of_params['todate'].' 23:59:59');
			
			$appIDs=array();
			$appserver=new AppInfo();
			if(empty($array_of_params['appid'])){
			$array_of_params['appid']=array("0");
			}
			$this->view->param=$array_of_params;
			if(!is_array($array_of_params['appid'])){
				$array_of_params['appid']=explode(",",$array_of_params['appid']);
			}
			if(in_array('0',$array_of_params['appid'])){
	       			$appIDs=$this->view->uApps;
			}
			else{
				foreach($array_of_params['appid'] as $appID){
				$appIDs[$appID]='';
				}
			}	
			$reporter = new Reporter();
			$result=array();

			$keys=array_keys($appIDs);
			$this->view->appChoices=$keys;
			foreach($keys as $appID){
				$subresult=array();
				$subresult=$reporter->summaryDaily($appID,$fromdate,$todate);
				foreach($subresult as $appByDate){
					$result[''.$appByDate['analyticDate']][''.$appByDate['appID']]=$appByDate['amount'];
				}
							
			}
			$subresult=$reporter->summaryDaily('zing',$fromdate,$todate);
			foreach($subresult as $appByDate){
				$result[''.$appByDate['analyticDate']]['zing']=$appByDate['amount'];
			}
			$this->view->data=$result;
			if($array_of_params['export']=='csv'){
				$this->exportCSVReport();die();
			}
			else{
			
			$this->view->frmSearch=$this->view->render('index/app_report.phtml');	 
		  	$this->view->container=$this->view->render('index/index.phtml');
			}
		}

}





private function exportCSVReport() {
 $param = $this->view->param;
  header("Content-type: application/csv");
header("Content-Disposition: \"inline; filename=credits_export.csv");
 echo "Ngày,Tổng ZingXu Nạp,ZingXu đã thanh toán theo ứng dụng\r\n\"\",\"\",";
 $appchoices = $this->view->appChoices;
 $i = 0;
 $count = count($appchoices);
 foreach ($appchoices as $app) {
  $i++;
  if ($app == 'zing' || $app == 'admin') {   
   continue;
  }
  echo '"' . $app . '"';
  if ($i <= $count - 1) {
   echo ',';
  }
 }
 echo "\r\n";
 $data = $this->view->data;
 $fromDate = $param['fromdate'];
 $toDate = $param['todate'];
 $exeDate = $param['fromdate'];
 while (true) {
  $echo = "";
  $dateData = $data[$exeDate . ' 00:00:00'];
  if (empty($dateData)) {
   $echo.= "\"" . $exeDate . "\",\"\",";
   $j = 0;
   foreach ($appchoices as $app) {
    $j++;
    if ($app == 'zing' || $app == 'admin') {
     continue;
    }
    $echo.= "\"\"";

    if ($j <= $count - 1) {
     $echo.=',';
    }
   }
   $echo.="\r\n";
  } else {
   $echo.="\"" . $exeDate . "\",";
   $appAmounts = $data[$exeDate . ' 00:00:00'];
   $echo.="\"" . $appAmounts['zing'] . "\",";
   $k = 0;
   foreach ($appchoices as $app) {
    $k++;
    if ($app == 'zing' || $app == 'admin') {
     continue;
    }
    $echo.="\"" . ($appAmounts[$app] == 0 ? '' : $appAmounts[$app]) . "\"";
    if ($k <= $count - 1) {
     $echo.= ',';
    }
   }
   $echo.="\r\n";
  }

  echo $echo;
  if ($exeDate == $toDate) {
   break;
  }
  $exeDate = date("Y-m-d", strtotime("+1 day", strtotime($exeDate . ' 00:00:00')));
 }
}


public function reportsummaryAction(){
if(empty($this->view->app_credits_report)){die();}
		$array_of_params = $this->_request->getParams ();		
		
		if(!isset($array_of_params['fromdate']) || $array_of_params['fromdate']==''
		    || !isset($array_of_params['todate']) || $array_of_params['todate']==''){
			$array_of_params['fromdate']=date('Y-m-d', strtotime("now -1 day"));
			$array_of_params['todate']=date('Y-m-d', strtotime("now -1 day"));
		}
		{	
			$fromdate= ($array_of_params['fromdate'].' 00:00:00');
			$todate= ($array_of_params['todate'].' 23:59:59');
			$this->view->param=$array_of_params;
			$appIDs=$this->view->uApps;	
			$reporter = new Reporter();
			$result=array();
			
			$keys=array_keys($appIDs);
			$this->view->appChoices=$keys;			
			foreach($keys as $appID){
				if($appID=='zing' || $appID=='admin')continue;
				$subresult=array();
				$subresult=$reporter->summaryDaily($appID,$fromdate,$todate);
				foreach($subresult as $appByDate){
		
					$result[''.$appByDate['analyticDate']]['total']+=$appByDate['amount'];
				}
							
			}
			
			$subresult=$reporter->summaryDaily('zing',$fromdate,$todate);
		
			foreach($subresult as $appByDate){
				$result[''.$appByDate['analyticDate']]['zing']=$appByDate['amount'];
			}
			$prevDate= date('Y-m-d', strtotime($fromdate." -1 day"));
			$subresult=$reporter->getRemainBalance($prevDate." 00:00:00",$todate);		
			foreach($subresult as $appByDate){
				$result[''.$appByDate['analyticDate']]['remainbalance']=$appByDate['remainBalance'];
			}
			$this->view->data=$result;
			if($array_of_params['export']=='csv'){
				$this->exportSummaryCSVReport();die();
			}
			else{
			
			$this->view->frmSearch=$this->view->render('index/app_report_summary.phtml');	 
		  	$this->view->container=$this->view->render('index/index.phtml');
			}
		}

}
public function exportSummaryCSVReport(){
$param = $this->view->param;
header("Content-type: application/csv");
header("Content-Disposition: \"inline; filename=credits_summary_export.csv");
echo "Ngày,Tồn đầu kỳ,Nhập,Xuất,Tồn cuối kỳ\r\n";
$data=$this->view->data;
$fromDate=$param['fromdate'];
$toDate=$param['todate'];
$exeDate=$param['fromdate'];
$i=0;
$echo="";
while(true){
$dateData = $data[$exeDate.' 00:00:00'];
$i++;
$prevDate= date('Y-m-d', strtotime($exeDate." -1 day"));
$tonDauKy=$data[$prevDate.' 00:00:00']['remainbalance'];
$echo.="\"" . $exeDate . "\",\"".($tonDauKy<=0?"":$tonDauKy)."\","."\"".$dateData['zing']."\",\"".$dateData['total']."\","."\"".$dateData['remainbalance']."\"\r\n";
if($exeDate==$toDate){
break;
}
$exeDate=date("Y-m-d",strtotime("+1 day", strtotime($exeDate.' 00:00:00')));
}
echo $echo;
}

public function reportdetailAction(){
if(empty($this->view->app_credits_report)){die();}
		$array_of_params = $this->_request->getParams ();		
		
		if(!isset($array_of_params['fromdate']) || $array_of_params['fromdate']==''
		    || !isset($array_of_params['todate']) || $array_of_params['todate']==''){
			$array_of_params['fromdate']=date('Y-m-d', strtotime("now -1 day"));
			$array_of_params['todate']=date('Y-m-d', strtotime("now -1 day"));
		}
		{	
			$fromdate= ($array_of_params['fromdate'].' 00:00:00');
			$todate= ($array_of_params['todate'].' 23:59:59');
			$this->view->param=$array_of_params;
			$appIDs=$this->view->uApps;	
			$reporter = new Reporter();
			$result=array();
			
			$keys=array_keys($appIDs);			
			foreach($keys as $appID){
				if($appID=='zing' || $appID=='admin')continue;
				$subresult=array();
				$subresult=$reporter->summaryDaily($appID,$fromdate,$todate);
				foreach($subresult as $appByDate){					
					$result[''.$appByDate['analyticDate']][''.$appByDate['appID']]=$appByDate['amount'];
				}
							
			}
			
			$subresult=$reporter->summaryDaily('zing',$fromdate,$todate);
		
			foreach($subresult as $appByDate){
				$result[''.$appByDate['analyticDate']]['zing']=$appByDate['amount'];
			}
			$prevDate= date('Y-m-d', strtotime($fromdate." -1 day"));
			$subresult=$reporter->getRemainBalance($prevDate." 00:00:00",$todate);	
			foreach($subresult as $appByDate){
				$result[''.$appByDate['analyticDate']]['remainbalance']=$appByDate['remainBalance'];
			}
			$this->view->data=$result;
			if($array_of_params['export']=='csv'){
				$this->exportDetailCSVReport();die();
			}
			else{
			
			$this->view->frmSearch=$this->view->render('index/app_report_detail.phtml');	 
		  	$this->view->container=$this->view->render('index/index.phtml');
			}
		}

}
public function exportDetailCSVReport(){
$param = $this->view->param;
unset($this->view->uApps['zing']);
unset($this->view->uApps['admin']);
$uApps = array_keys($this->view->uApps);
header("Content-type: application/csv");
header("Content-Disposition: \"inline; filename=credits_detail_export.csv");
echo "Ngày,Tồn đầu kỳ,Nhập trong kỳ,Xuất trong kỳ,";
for($i=0;$i<count($uApps);$i++){
echo "\"\",";
}
echo "Tồn cuối kỳ\r\n";
echo "\"\",\"\",TỔNG NHẬP,";

foreach($uApps as $app){
if($app=='zing'||$app=='admin'){continue;}
echo "\"".$app."\",";
}
echo "TỔNG XUẤT,\"\"\r\n";



$data=$this->view->data;
$fromDate=$param['fromdate'];
$toDate=$param['todate'];
$exeDate=$param['fromdate'];
$i=0;
$echo="";
while(true){
$dateData = $data[$exeDate.' 00:00:00'];
$i++;
$a='';
$total=0;
foreach($uApps as $app){
if($app=='zing'||$app=='admin'){continue;}
$total+=$dateData[$app];

$a.="\"".($dateData[$app]==0?'':$dateData[$app])."\",";
}
$prevDate= date('Y-m-d', strtotime($exeDate." -1 day"));
$tonDauKy=$data[$prevDate.' 00:00:00']['remainbalance'];
$echo.="\"" . $exeDate . "\",\"". ($tonDauKy<=0?"":$tonDauKy) . "\",\"". $dateData['zing'] . "\",".$a."\"".($total==0?'':$total). "\",\"".$dateData['remainbalance']. "\"\r\n";

if($exeDate==$toDate){
break;
}
$exeDate=date("Y-m-d",strtotime("+1 day", strtotime($exeDate.' 00:00:00')));
}
echo $echo;
}
}
