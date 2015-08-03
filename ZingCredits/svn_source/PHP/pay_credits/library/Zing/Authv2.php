<?php
require_once 'Zing/Auth/Util.php';;
require_once 'Zing/Cookies.php';;
require_once 'Zing/Auth/Storage/VNGSession.php';;
class Zing_Authv2 {
	private $_vngauth = null;

	protected static $_instance = null;

	/**
	 * @return Zing_Authv2
	 */
	public static function getInstance() {
		if (null === self::$_instance) {
			self::$_instance = new self();
		}
		return self::$_instance;
	}

	public function isLogged() {
		$cookie = $this->_getCookies();
		$verified_vng = $this->_verifyVngSession($cookie->vngauth);
		$session_vng = $verified_vng["valid"];
		if(!$session_vng){
			$this->_clearCookie();
			return false;
		}
		$this->_setIdentity($verified_vng["identity"]);
		return true;
	}

	
	public function getIdentity() {
		return $this->_identity;
	}

	
	// private function
	private function _setIdentity($identity) {
		$this->_identity = $identity;
	}

	private function _getCookies() {
		$cookies = new stdClass();
		$cookies->vngauth = Zing_Cookies::getCookie('vngauth');
		return $cookies;
	}

	private function _clearCookie() {
		Zing_Cookies::clearCookies('vngauth');
	}
	/**
	 * check vng session
	 * @return array of identity if valid, false if not  valid
	 */
	public function _verifyVngSession($sessionid) {
		$verified = array("valid"=>false,"identity"=>null);
		if (empty($sessionid) || !Zing_Auth_Util::checkVngSessionKey($sessionid))
			return $verified;

		$storage = Zing_Auth_Storage_VNGSession::getInstance();
		$storage->setSessionId($sessionid);
		$result = $storage->read($sessionid);

		// check valid
		if ($result->resultCode != 0)
			return $verified;

		// check ip address
		$ipAddress = Zing_Auth_Util::getRealIp();
		//if ($result->session->hostname != $ipAddress)
		//	return $verified;

		// check useragent
		$useragent = strtoupper(md5($_SERVER['HTTP_USER_AGENT']));
		if ($result->session->useragent != $useragent)
			return $verified;

		$identity = array();
		$identity['display_name'] = $result->session->accountName;
		$identity['mail'] = "";
		$identity['login_time'] = $result->session->createTime;
		$identity['last_access_time'] = $result->session->lastAccess;
		$identity['uin'] = $result->session->uin;
		$identity['zin'] = $result->session->zin;
		$identity['username'] = strtolower($result->session->accountName);
		$verified["valid"] = true;
		$verified["identity"] = $identity;
		return $verified;
	}


	

}