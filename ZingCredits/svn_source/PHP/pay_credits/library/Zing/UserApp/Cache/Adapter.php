<?php
class Zing_UserApp_Cache_Adapter
{
	public static  function factory()
	{	
		$type = "memcache";
		switch($type){
			case "memcache":
				return Zing_UserApp_Cache_Memcache::getInstance();
				break;
			default:
				return Zing_UserApp_Cache_Memcache::getInstance();
				break;
		}
	}
}
?>
