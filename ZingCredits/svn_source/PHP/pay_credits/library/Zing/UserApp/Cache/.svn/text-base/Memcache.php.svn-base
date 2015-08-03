<?php
class Zing_UserApp_Cache_Memcache
{
	static private $_instance = NULL;
	const TOKENKEY = "%s";
	private $_adapter = NULL;
	private $expire;
	private function _getTokenkey($key)
	{
		return sprintf(self::TOKENKEY,$key);
	}
	public function __construct()
	{
	$config = Zend_Registry::get('appconf');
	$this->expire=$config->cache->userapp->expire;
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
			$this->_adapter = new Zing_Cache_ZingCache($config->cache->userapp->host,$config->cache->userapp->port);
		}
		return $this->_adapter;
	}
	public function setCache($key,$data)
	{
		$objCache = $this->getCacheAdapter();
		$key = $this->_getTokenkey($key);
		$objCache->setCache($key,$data,$this->expire);
	}
	public function getCache($key)
	{
		$objCache = $this->getCacheAdapter();
		$key = $this->_getTokenkey($key);
		$data = $objCache->getCache($key);
		return $data;
	}
	public function delete($key)
	{
		$objCache = $this->getCacheAdapter();
		$key = $this->_getTokenkey($key);
		$objCache->deleteCache($key);
	}
}
?>
