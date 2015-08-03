<?php

require_once("Zing/Me/Common.php");
require_once $GLOBALS['LOCAL_LIB_PACKAGES_DIR'].'/ViewPointChangeLog/ViewPointChangeLog_types.php';
//require_once $GLOBALS['LOCAL_LIB_PACKAGES_DIR'].'/ViewPointChangeLog/ViewPointChangeLogReadService.php';

class ViewpointchangelogController extends Zend_Controller_Action
{	
	
	protected $_cssLinks;
	protected $_jsLinks;
	protected $_business;

	private $lstType = array(
		array("id"=>0,"name"=>"All"),
		array("id"=>1,"name"=>"Inc"),
		array("id"=>2,"name"=>"Dec")
	);

	 private $lstApp = array(
		array("id"=>0,"name"=>"All"),
		 array("id"=>107,"name"=>"Feed"),
		 array("id"=>100,"name"=>"Album"),
		 array("id"=>23,"name"=>"Blog"),
		 array("id"=>28,"name"=>"Photo"),
		 array("id"=>71,"name"=>"Question & Answer")
	);

	private $lstAct = array(
		array("appId"=>0,"id"=>0,"name"=>"All"),
		array("appId"=>107,"id"=>0,"name"=>"All"),
		array("appId"=>100,"id"=>0,"name"=>"All"),
                array("appId"=>23,"id"=>0,"name"=>"All"),
             array("appId"=>28,"id"=>0,"name"=>"All"),
             array("appId"=>71,"id"=>0,"name"=>"All")
	);

	public function init()
	{
//		$auth = Zing_Admin_Auth::getInstance();
//		$this->view->userrole = $auth->getIdentity()->userrole;
//		if(empty($this->view->userrole))
//			$this->view->userrole = 4;
//		$this->view->name = $auth->getIdentity()->name;
		
		$this->_business = Zing_ViewPointChangeLog::getInstance();
		
		$this->_cssLinks = array();
		$this->_jsLinks = array();		

		Zend_Layout::startMvc(APPLICATION_PATH . 'layouts');
		$layout = Zend_Layout::getMvcInstance();
		$layout->setLayoutPath(APPLICATION_PATH . 'layouts')->setLayout('layout_viewpoint');

	}

		

	public function indexAction(){		
		//----------- action---------
		if ($_SERVER['REQUEST_METHOD']=='GET')
		{
			$zSearchInfo= new ViewPointChangeLog_ZSearch();		
			$zSearchInfo->userName = $this->_request->getParam('userName','');
			$zSearchInfo->type = $this->_request->getParam('typeId','0');
			$zSearchInfo->appId = $this->_request->getParam('appId','0');
			$zSearchInfo->actId = $this->_request->getParam('actId','0');
			$fromDateTime = $this->_request->getParam('fromTime',date('d/m/Y', 0));
			$toDateTime = $this->_request->getParam('toTime',date('d/m/Y', time()));
			$zSearchInfo->fromTime = Utilcommon::convert_datetime(0,0,0,$fromDateTime)*1000;
			$zSearchInfo->toTime = Utilcommon::convert_datetime(23,59,59,$toDateTime)*1000;
			$zSearchInfo->fromTime=$zSearchInfo->fromTime>0?$zSearchInfo->fromTime:0;
			$page=$this->_request->getParam('page',1);

			$this->view->changelog=$this->_business->getLogs($zSearchInfo, $page, ITEM_PER_PAGE);
			$this->view->userName=$zSearchInfo->userName;
			$this->view->type=$zSearchInfo->type;
			$this->view->appId=$zSearchInfo->appId;
			$this->view->actId=$zSearchInfo->actId;
			$this->view->fromTime=$fromDateTime;
			$this->view->toTime=$toDateTime;			
		}
		//---------------------------
                $page= $this->_request->getParam('page',1);

		$this->view->lstType=$this->lstType;
		$this->view->lstApp=$this->lstApp;
		$this->view->lstAct=$this->lstAct;
               $this->view->page=$page;

		$this->view->left = $this->view->render('viewpointchangelog/left.phtml');
		$this->view->content = $this->view->render('viewpointchangelog/index.phtml');
                $this->addJS('viewpoint.js');
                $this->addJS('jquery-1.4.2.js');
		$this->addCSS('zing_admin/formapi.css');
		$this->addCSS('zing_admin/page.css');
		$this->addCSS('zing_admin/screen.css');
	}

	public function getidAction(){
		header("Content-Type: text/javascript");
		$this->_helper->layout()->disableLayout();
        $this->_helper->viewRenderer->setNoRender(true);		
		$appId=$this->_request->getParam('appId',$this->lstApp[0]['id']);
		$lstOption="";
		foreach ($this->lstAct as $act)
			if($act['appId']==$appId)	
			{
				$key=$act['id'];
				$value=$act['name'];
				//$lstOption=$lstOption."<option value=\'".$act['id'] ."'>". $act['name']."</option>";
				$lstOption=$lstOption."<option value='$key'>".$value."</option>";
			}
			
		$arr = array ('content'=>$lstOption);
		$strJSON = $_GET['callback'];
		$strJSON.="(";
		$strJSON.=json_encode($arr);
		$strJSON.=")";
		echo $strJSON;
	}
	

	public function dispatch($action)
	{
		try
		{
			parent::dispatch($action);
		}
		catch (Exception $ex)
		{
			return;
		}
		$this->view->currentAction = $action;
		$this->view->cssLinks = $this->_cssLinks;
		$this->view->jsLinks = $this->_jsLinks;
	}

	protected function addCSS($css)
	{
		$this->_cssLinks[] = $css;
	}

	protected function addJS($js)
	{
		$this->_jsLinks[] = $js;
	}

}