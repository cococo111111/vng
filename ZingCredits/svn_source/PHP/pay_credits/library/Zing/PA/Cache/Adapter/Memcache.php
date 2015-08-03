<?php

class Zing_PA_Cache_Adapter_Memcache implements Zing_PA_Cache_Adapter_Interface
{
	
	private $_cache_instance = 'pa';
	
	protected static $_instance = null;
	
	function __construct()
	{
		
	}

	public static function getInstance()
	{
		if(self::$_instance == null)
		{
			self::$_instance = new self();
		}
		return self::$_instance;
	}
	
	public function setCache($key, $value, $expire = 0, $bCompress = 0)
	{

		GlobalCache::setCache($key,$value, $this->_cache_instance, $expire, $bCompress);
	}	

	public function getCache($key)
	{
		$result = GlobalCache::getCache($key, $this->_cache_instance);
		return $result;
	}
	
	public function deleteCache($key)
	{
		GlobalCache::deleteCache($key, $this->_cache_instance);
	}
	
	public function increment($key, $value){
		GlobalCache::increment($key, $value, $this->_cache_instance);
	}

	public function decrement($key, $value = null){
		GlobalCache::decrement($key, $this->_cache_instance, $value);
	}
	
	public function getMultiCache($keys = array())
	{
		$result = GlobalCache::getMultiCache($keys, $this->_cache_instance);
	}	
	
	
}
