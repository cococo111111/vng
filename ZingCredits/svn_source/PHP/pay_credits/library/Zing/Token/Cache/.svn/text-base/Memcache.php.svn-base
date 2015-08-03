<?php
class Zing_Token_Cache_Memcache
{
	static private $_instance = NULL;
	const TOKENKEY = "%s";
	static private $_adapter = NULL;
	
	private function _getTokenkey($key)
	{
		return sprintf(self::TOKENKEY,$key);
	}
	public function __construct()
	{
	}
	public static function getInstance()
	{
		if(self::$_instance == NULL)
		{
			self::$_instance = new self();
		}
		return self::$_instance;
	}
	private function getCacheAdapter()
	{
		$config = Zend_Registry::get('appconf');		
		if($this->_adapter == NULL)
		{
			$this->_adapter = new Zing_Cache_ZingCache($config->cache->token->host,$config->cache->token->port);
		}
		return $this->_adapter;
	}
	public function setTokenCache($macaddress,$data)
	{
		$objCache = $this->getCacheAdapter();
		$key = $this->_getTokenkey($macaddress);
		$objCache->setCache($key,$data,0);
	}
	public function getTokenCache($macaddress)
	{
		$objCache = $this->getCacheAdapter();
		$key = $this->_getTokenkey($macaddress);
		$data = $objCache->getCache($key);
		return $data;
	}
	public function deleteToken($macaddress)
	{
		$objCache = $this->getCacheAdapter();
		$key = $this->_getTokenkey($macaddress);
		$objCache->deleteCache($key);
	}
}
?>
