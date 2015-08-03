<?php

require_once APPLICATION_PATH . '/models/XMLUser.php';
require_once LIB_PATH . '/Zing/Admin/Auth.php';

class IndexController extends Zend_Controller_Action {

    public function init() {
        $this->auth();
    }

    private function auth() {
        $auth = Zing_Admin_Auth::getInstance();
        $this->view->userrole = $auth->getIdentity()->userrole;
        if (empty($this->view->userrole))
            $this->view->userrole = 4;
        $this->view->name = $auth->getIdentity()->name;
        if ($auth->hasIdentity()) {
            $this->view->showSignOut = true;
        } else {
            $this->view->showSignOut = false;
            $this->_forward('signin', 'index');
        }
    }

    public function indexAction() { //$this->_helper->viewRenderer->setNoRender();
        //$this->_forward('index','app');
        $this->view->container = $this->view->render("index/index.phtml");
    }

    private function getParam() {
        $appData["systemId"] = $this->_request->getPost('systemId');
        $appData["userid"] = $this->_request->getPost('userid');
        $appData["fromdate"] = $this->_request->getPost('fromdate');
        $appData["todate"] = $this->_request->getPost('todate');
        $appData["tranxtype"] = $this->_request->getPost('tranxtype');
        return $appData;
    }

    public function signinAction() {
        if ($this->getRequest()->isPost()) {
            $username = $this->_request->getPost('username');
            $password = $this->_request->getPost('password');
            $auth = Zing_Admin_Auth::getInstance();
            $valid = $auth->authenticate($username, md5($password));
            if ($valid)
                $this->_redirect("index");
        }


        $this->view->container = $this->view->render("index/signin.phtml");
    }

    public function signoutAction() {
        $auth = Zing_Admin_Auth::getInstance();
        $auth->clearIdentity();
        $this->_forward("signin");
    }

    public function changepassAction() {
        if ($this->getRequest()->isPost()) {
            $currpassword = $this->_request->getPost('currpassword');
            $password = $this->_request->getPost('password');
            $confirmpassword = $this->_request->getPost('confirmpassword');
            $passlength = $this->_request->getPost('passlength');

            $model_user = new Models_XMLUser();
            $auth = Zing_Admin_Auth::getInstance();
            $userId = $auth->getIdentity()->userid;
            $resultChangePass = $model_user->changePass($currpassword, $password, $confirmpassword, $passlength, $userId);

            $result['success'] = false;
            $result['error'] = array();
            switch ($resultChangePass) {
                case SUCCESS:
                    $result['success'] = true;
                    break;
                case CHANGEPASS_EMPTY_CURR_PASS:
                    $result['error'][] = "Current password is empty";
                    break;
                case CHANGEPASS_EMPTY_NEW_PASS:
                    $result['error'][] = "New password is empty";
                    break;
                case CHANGEPASS_EMPTY_CONFIRM_PASS:
                    $result['error'][] = "Confirm password is empty";
                    break;
                case CHANGEPASS_WRONG_OLD_PASS:
                    $result['error'][] = "Current password is wrong";
                    break;
                case CHANGEPASS_CONFIRM_NOT_MATCH:
                    $result['error'][] = "Password and confirm password do not match";
                    break;
                case CHANGEPASS_PASS_NOT_LENGTH_ENOUGH:
                    $result['error'][] = "Password must be more than 5 characters";
                    break;
                default:
                    $result['error'][] = "Unknown error";
                    break;
            }
            echo json_encode($result);
            die();
        } else {
            $this->view->container = $this->view->render("index/changepass.phtml");
        }
    }

}
