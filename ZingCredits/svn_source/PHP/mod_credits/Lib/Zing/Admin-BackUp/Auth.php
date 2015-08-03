<?php
class Zing_Admin_Auth extends  Zend_Auth 
{
	protected $_identity = null;
    protected static $_instance = null;
    const COOKIE_ADMIN_AUTH_KEY = "ZADMIN_AUTH";
	protected function __construct()
    {
    	parent::__construct();
    	$zadmin_auth = Zing_Cookies::getCookie(self::COOKIE_ADMIN_AUTH_KEY);
    	$this->_storage = new Zing_Admin_Auth_Storage();
    	if(!empty($zadmin_auth))
    		$this->_storage->setZAdminAuthKey($zadmin_auth);
    }
    
   /**
     * Returns an instance of Zend_Auth
     *
     * Singleton pattern implementation
     *
     * @return Zing_Admin_Auth Provides a fluent interface
     */
	public static function getInstance()
    {
        if (null === self::$_instance) {
            self::$_instance = new self();
        }

        return self::$_instance;
    }
    
	public function hasIdentity()
    {
    	if(!empty($this->_identity))
    		return true;
    	$zadmin_auth = Zing_Cookies::getCookie(self::COOKIE_ADMIN_AUTH_KEY);
    	
    	if(empty($zadmin_auth) || !Zing_Auth_Util::checkSessionKey($zadmin_auth))
    	{
    		Zing_Cookies::clearCookies($zadmin_auth);
    		return false;
    	}
        $storage = $this->getStorage();
        $storage->setZAdminAuthKey($zadmin_auth);
        $identity = $storage->read();
        
        if(!empty($identity))
        {
    		$this->_identity = new stdClass();
	        $this->_identity->userid = $identity["userid"];
	        $this->_identity->username = $identity["username"];
	        $this->_identity->usertype = $identity["usertype"];
	        $this->_identity->status = $identity["status"];
	        return true;
        }
    	return false;
    }

    public function getIdentity()
    {
       return $this->_identity;
    }
	
    public function authenticate(Zend_Auth_Adapter_Interface $adapter)
    {
    	$result = $adapter->authenticate();
    	if ($result->isValid()) 
        {
        	$this->_identity = $adapter->getResultRowObject(null, 'password');
        	$storage = $this->getStorage();
        	$zadmin_auth = $storage->create();
        	$storage->write($this->_identity);
        	Zing_Cookies::createCookies(self::COOKIE_ADMIN_AUTH_KEY,$zadmin_auth,0);
        }
        return $result;
    }
    public function clearIdentity()
    {
    	$this->_identity=null;
		$this->getStorage()->clear();
		Zing_Cookies::clearCookies(self::COOKIE_ADMIN_AUTH_KEY);
    }
}
?>