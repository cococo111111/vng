<?php
class Zing_App_Auth
{
	private $_storage=null;
	private $_identity=null;
	private function setIdentityBySSO($objSession)
	{
		$this->_identity=array();
		$this->_identity['display_name']		= $objSession->nick;
		$this->_identity['mail']				= $objSession->email;
		$this->_identity['login_time']			= $objSession->createTime;
		$this->_identity['last_access_time']	= $objSession->lastAccess;
		$this->_identity['uin']					= $objSession->uin;
		$this->_identity['username']			= $objSession->accountName;
	}
	public function isLogged()
	{
		
		$zauth=isset($_COOKIE['ZAUTH'])?$_COOKIE['ZAUTH']:''; 
		$hasMeCookies=($zauth!='')&&Zing_Auth_Util::checkSessionKey($zauth);  
		if($hasMeCookies&&$this->_storage!=null)
		{
			$this->_storage->setSessionId($zauth);
			$objSession=$this->_storage->read();
			if(!empty($objSession))
			{
				
				$objSession->accountName = trim($objSession->accountName);
				if(!empty($objSession->accountName))
				{
					$this->setIdentityBySSO($objSession);
					return true;
				}
			}
			
		}
		return false;
	} 
	public function setStorage(Zing_Auth_Storage_Interface $storage)
    {
        $this->_storage = $storage;
    }
	public function hasIdentity()
	{
		return ($this->_identity==null);
	}
	public function getIdentity()
	{
		return $this->_identity;
	}
	public function doLogout()
	{
		Zing_Cookies::clearCookies('ZAUTH');
		Zing_Cookies::clearCookies('acn');
		Zing_Cookies::clearCookies('skey');
		Zing_Cookies::clearCookies('uin');
		$this->_storage->clear();
		$this->_identity=null;
	}
}  
?>
