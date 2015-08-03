<?php

/*
 * Class: Zing_Admin_Auths.
 * Description: Auth class for ContentFilter system.
 * Modifier: Khanv.
 * Last modified: Jan 12th 2011
 */
//require_once LIB_PATH.'Zing/Admin/Auth/Storage.php';
require_once MODEL_PATH . 'XMLUser.php';

class Zing_Admin_Auth extends Zend_Auth {

    protected $_identity = null;
    protected static $_instance = null;

    const COOKIE_ADMIN_AUTH_KEY = "ZING_ADMIN_CREDITS_AUTH";
    const COOKIE_ADMIN_AUTH_SIG_KEY = "ZING_ADMIN_CREDITS_AUTH_SIG";
    const COOKIE_ADMIN_AUTH_ROLE = "ZING_ADMIN_CREDITS_AUTH_ROLE";

    protected function __construct() {
        parent::__construct();
        $zadmin_auth = Zing_Cookies::getCookie(self::COOKIE_ADMIN_AUTH_KEY);
        $this->_storage = new Zing_Admin_Auth_Storage();
        if (!empty($zadmin_auth))
            $this->_storage->setZAdminAuthKey($zadmin_auth);
        $this->hasIdentity();
    }

    /**
     * Returns an instance of Zend_Auth
     *
     * Singleton pattern implementation
     *
     * @return Zing_Admin_Auth Provides a fluent interface
     */
    public static function getInstance() {
        if (null === self::$_instance) {
            self::$_instance = new self();
        }

        return self::$_instance;
    }

    public function hasIdentity() {
        if (!empty($this->_identity))
            return true;
        $zadmin_auth = Zing_Cookies::getCookie(self::COOKIE_ADMIN_AUTH_KEY);

        if (empty($zadmin_auth) || !Zing_Auth_Util::checkSessionKey($zadmin_auth)) {
            Zing_Cookies::clearCookies($zadmin_auth);
            return false;
        }
        $storage = $this->getStorage();
        $storage->setZAdminAuthKey($zadmin_auth);
        $identity = $storage->read();

        if (!empty($identity)) {
            //Identity in cache is array
            $this->_identity = new stdClass();
            $this->_identity->userid = $identity["userid"];
            $this->_identity->username = $identity["username"];
            $this->_identity->userrole = $identity["userrole"];
            $this->_identity->controlblock = $identity["controlblock"];
            $this->_identity->name = $identity["name"];

            return true;
        }
        return false;
    }

    public function getIdentity() {
        return $this->_identity;
    }

    public function authenticate($username, $password) {
        $modelUser = new Models_XMLUser();
        $result = $modelUser->authenticate($username, $password);

        if ($result->valid) {
            //Identity from DB is object
            $this->_identity = new stdClass();
            $this->_identity->userid = $result->user_info->userid;
            $this->_identity->username = $result->user_info->username;
            $this->_identity->userrole = $result->user_info->userrole;
            $this->_identity->controlblock = $result->user_info->controlblock;
            $this->_identity->name = $result->user_info->name;

            $storage = $this->getStorage();
            $zadmin_auth = $storage->create();
            $storage->write($this->_identity);
            Zing_Cookies::createCookies(self::COOKIE_ADMIN_AUTH_KEY, $zadmin_auth, 0);
            Zing_Cookies::createCookies(self::COOKIE_ADMIN_AUTH_ROLE,  $result->user_info->userrole, 0);
            Zing_Cookies::createCookies(self::COOKIE_ADMIN_AUTH_SIG_KEY, md5($zadmin_auth . $result->user_info->userrole . ADMIN_AUTOREPORT_KEY), 0);
        }
        return $result->valid;
    }

    public function clearIdentity() {
        $this->_identity = null;
        $this->getStorage()->clear();
        Zing_Cookies::clearCookies(self::COOKIE_ADMIN_AUTH_KEY);
        Zing_Cookies::clearCookies(self::COOKIE_ADMIN_AUTH_ROLE);
        Zing_Cookies::clearCookies(self::COOKIE_ADMIN_AUTH_SIG_KEY);
    }
}

?>
