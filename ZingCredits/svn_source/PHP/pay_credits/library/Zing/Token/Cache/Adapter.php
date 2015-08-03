<?php
class Zing_Token_Cache_Adapter
{
	public static  function factory()
	{	
		$type = "memcache";
		switch($type){
			case "memcache":
				return Zing_Token_Cache_Memcache::getInstance();
				break;
			default:
				return Zing_Token_Cache_Memcache::getInstance();
				break;
		}
	}
}
?>
