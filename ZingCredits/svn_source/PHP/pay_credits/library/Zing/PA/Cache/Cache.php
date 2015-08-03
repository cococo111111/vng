<?php

class Zing_PA_Cache_Cache
{
	protected static $_instance = null;

	/**
	 * 
	 * @param unknown_type $type
	 * @return Zing_MP3_Cache_Adapter_Interface
	 */
	public static function factory($type = 'memcache')
	{
		if($type == 'memcache')
		{
			self::$_instance = Zing_PA_Cache_Adapter_Memcache::getInstance();
		}
		
		return self::$_instance;
	}
} 
