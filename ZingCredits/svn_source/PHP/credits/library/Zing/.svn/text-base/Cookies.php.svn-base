<?php
class Zing_Cookies
{
	const COOKIES_DOMAIN='zing.vn';

	static function getCookie($name, $defaultValue = '')
	{
		return isset($_COOKIE[$name]) ? $_COOKIE[$name]:$defaultValue;
	}
	
	static function clearCookies($name , $path='/', $domain=self::COOKIES_DOMAIN)
	{
		header('P3P: CP="NOI ADM DEV PSAi COM NAV OUR OTRo STP IND DEM"');
		return setcookie($name, 'deleted', time()-365*24*3600, $path, $domain);
	}
	
	
	
}
?>