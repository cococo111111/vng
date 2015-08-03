<?php

require_once APPLICATION_PATH.'/models/XMLUser.php';
require_once LIB_PATH.'/Zing/Admin/Auth.php';
class IndexController extends Zend_Controller_Action
{


	public function init()
	{
		$this->auth();
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
	public function indexAction()
	
	{	//$this->_helper->viewRenderer->setNoRender();
		//$this->_forward('index','app');
	    $this->view->container = $this->view->render("index/index.phtml");
	}
	private function getParam(){
		$appData["systemId"] = $this->_request->getPost('systemId');
		$appData["userid"] = $this->_request->getPost('userid');
		$appData["fromdate"] = $this->_request->getPost('fromdate');
		$appData["todate"] = $this->_request->getPost('todate');
		$appData["tranxtype"] = $this->_request->getPost('tranxtype');
		return $appData;
	}
	public function signinAction()
	{
		if($this->getRequest()->isPost())
		{
			$username = $this->_request->getPost('username');
			$password = $this->_request->getPost('password');
			$auth = Zing_Admin_Auth::getInstance();
			$valid = $auth->authenticate($username, ($password));
			if($valid)
			$this->_redirect("index");
			
		}
		

		$this->view->container = $this->view->render("index/signin.phtml");
	}

	public function signoutAction()
	{
		$auth = Zing_Admin_Auth::getInstance();
		$auth->clearIdentity();
		$this->_forward("signin");
	}
	public function changepassAction()
	{
		if($this->getRequest()->isPost())
		{
			$currpassword = $this->_request->getPost('currpassword');
			$password = $this->_request->getPost('password');
			$confirmpassword = $this->_request->getPost('confirmpassword');


			$model_user = new AdminReportHandler();
			$auth = Zing_Admin_Auth::getInstance();
			$userId	= $auth->getIdentity()->userid;



			
			try{
			if($currpassword==''||$password=='' || $confirmpassword==""){
				$result['error'][] = "Enter required fields!";
			}
			else{
			if($password!=$confirmpassword){
				$result['error'][] = "Password and confirm password do not match";
			}
			else{
			$user=$model_user->getCreditsAdmin($userId,$currpassword);
			if(count($user)!=1){
				$result['error'][] = "Current password is wrong";
			}
			else{
			$resultChangePass = $model_user->changePass($userId,$currpassword,$password);
			$result['success'] = true;
			}
			}
			}			
			}catch(Exception $e){$result['error'][] = "Unknown error";}			
			echo json_encode($result);
			die();
		}
		else
		{
			$this->view->container  = $this->view->render("index/changepass.phtml");
		}
	}

}
