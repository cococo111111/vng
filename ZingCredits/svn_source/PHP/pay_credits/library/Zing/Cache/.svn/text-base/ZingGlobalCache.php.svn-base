<?php
class Zing_Cache_ZingGlobalCache
{
	static $_cache_array = array();
	static $_no_of_instance = null;
	private static $_default_instance = 'default';

	/**
	 * Enter description here...
	 *
	 * @param unknown_type $instance
	 * @return Zing_Cache_ZingCache
	 */
	public static function getGlobalCache($instance = 'default')
	{
		if(self::checkEnable())
		{
			if(array_key_exists($instance, self::$_cache_array))
			{
				return self::$_cache_array[$instance];
			}
			else if(count(self::$_cache_array) > 0)
			{
				return self::$_cache_array[self::$_default_instance];
			}
			else
			{
				return null;
			}
		}
		else
		{
			return null;
		}
	}

	public static function getAllProfilerData()
	{
		$output = "";
		if(self::checkEnable())
		{
			if(count(self::$_cache_array) > 0)
			{
				foreach(self::$_cache_array as $key => $value)
				{
					$output .= $value->getProfilerData($key);
					$output .= "<br><br>";
				}
			}
		}
		return $output;
	}

	private static function checkEnable()
	{
		self::initConfig();
		if(self::$_no_of_instance != null)
		{
			return true;
		}
		else return false;
	}

	private static function initConfig()
	{
		if(self::$_no_of_instance == null)
		{
			$config = Zend_Registry::get('configuration');
			if(isset($config->cachingfarm))
			{
				if(isset($config->default_cache))
				{
					self::$_default_instance = $config->default_cache;
				}

				$list = $config->cachingfarm->list;

				$arr = explode(',', $list);

				if(count($arr) > 0)
				{
					self::$_no_of_instance = count($arr);

					for($i=0;$i<self::$_no_of_instance;$i++)
					{
						$cache = $arr[$i];
						if(isset($config->cachingfarm->$cache))
						{
							$con =  $config->cachingfarm->$cache;

							$_enable = $con->enable;
							$_server = $con->host;
							$_port = $con->port;
							$debug = Globals::isDebug();

							if(empty($debug)) $debug = false;

							if($_enable)
							{
								//echo "i'm here with $cache - $_enable - $_server - $_port <br>";
								$memcache = new Zing_Cache_ZingCache($_server, $_port, $debug);
								self::$_cache_array[$cache] = $memcache;
							}
						}
					}
				}
			}
		}


	}

	public static function flushLocalCache($instance = '')
	{
		$cache = Zing_Cache_ZingGlobalCache::getGlobalCache($instance);
		if($cache != null)
		{
			echo "flushlocalcate $instance \n";
			$cache->flushLocalCache();
		}
	}

	public static function getMultiCache($keys, $instance = '')
	{
		$cache = Zing_Cache_ZingGlobalCache::getGlobalCache($instance);
		$result = FALSE;
		if($cache != null)
		{
			$result = $cache->getMultiCache($keys);
		}
		return $result;
	}

	public static function getCache($key, $instance = '')
	{
		$cache = Zing_Cache_ZingGlobalCache::getGlobalCache($instance);
		$result = FALSE;
		if($cache != null)
		{
			$result = $cache->getCache($key);
		}
		return $result;
	}

	public static function increment($key, $value, $instance = '')
	{
		$cache = Zing_Cache_ZingGlobalCache::getGlobalCache($instance);
                $cache->increment($key, $value);
	}

	public static function deleteCache($key, $instance = '')
	{
		$cache = Zing_Cache_ZingGlobalCache::getGlobalCache($instance);
		if($cache != null)
		{
			$cache->deleteCache($key);
		}
	}

	public static function setCache($key, $value, $instance = '', $expireTime = 0,  $compress=0)
	{
		$cache = Zing_Cache_ZingGlobalCache::getGlobalCache($instance);
		if($cache != null)
		{
			$cache->setCache($key,$value,$expireTime,$compress);
		}
	}


	//public static function setCache($key, )




}
?>