<?php
class Zing_Auth_Util
{
	
	public static function hex2bin($str) 
	{
		$bin = "";
		$i = 0;
		do 
		{
			$bin .= chr(hexdec($str{$i}.$str{($i + 1)}));
			$i += 2;
		} while ($i < strlen($str));
		return $bin;
	}
   
    public static function checkVngSessionKey($key)
	{
        if(strlen($key) != 28)
			return false;
        $key = base64_decode($key);
        if(empty($key))
            return false;
        
		//C = 1, C = 1, S = 2, L=4, L = 4
		if(strlen($key) != 20)
			return false;
		return true;
		//return true;

	}
	public static function isZingLongSession($key){
		$type = strtoupper(substr($key,0,2));
		if($type == "3F")
			return true;
		return false;
	}
	public static function isVNGLongSession($key){
		$key = base64_decode($key);
		$result= unpack('Ctypeserver/Cversion/Srandom1/Lrandom2/Lppid/Lzmid/Lcrc',$key);
		if($result["typeserver"] == 33)
			return true;
		return false;
	}
	public static function getRealIp() {
		if (isset($_SERVER['HTTP_X_FORWARDED_FOR'])) {
			// case 1.A: proxy && HTTP_X_FORWARDED_FOR is defined
			$array = self::extractIP($_SERVER['HTTP_X_FORWARDED_FOR']);
			if ($array && count($array) >= 1) {
				return $array[0]; // first IP in the list
			}
		}
		if (isset($_SERVER['HTTP_X_FORWARDED'])) {
			// case 1.B: proxy && HTTP_X_FORWARDED is defined
			$array = self::extractIP($_SERVER['HTTP_X_FORWARDED']);
			if ($array && count($array) >= 1) {
				return $array[0]; // first IP in the list
			}
		}
		if (isset($_SERVER['HTTP_FORWARDED_FOR'])) {
			// case 1.C: proxy && HTTP_FORWARDED_FOR is defined
			$array = self::extractIP($_SERVER['HTTP_FORWARDED_FOR']);
			if ($array && count($array) >= 1) {
				return $array[0]; // first IP in the list
			}
		}
		if (isset($_SERVER['HTTP_FORWARDED'])) {
			// case 1.D: proxy && HTTP_FORWARDED is defined
			$array = self::extractIP($_SERVER['HTTP_FORWARDED']);
			if ($array && count($array) >= 1) {
				return $array[0]; // first IP in the list
			}
		}
		if (isset($_SERVER['HTTP_CLIENT_IP'])) {
			// case 1.E: proxy && HTTP_CLIENT_IP is defined
			$array = self::extractIP($_SERVER['HTTP_CLIENT_IP']);
			if ($array && count($array) >= 1) {
				return $array[0]; // first IP in the list
			}
		}

		if (isset($_SERVER['HTTP_VIA'])) {
			// case 2:
			// proxy && HTTP_(X_) FORWARDED (_FOR) not defined && HTTP_VIA defined
			// other exotic variables may be defined
			return ( $_SERVER['HTTP_VIA'] .
			'_' . $_SERVER['HTTP_X_COMING_FROM'] .
			'_' . $_SERVER['HTTP_COMING_FROM']
			);
		}
		if (isset($_SERVER['HTTP_X_COMING_FROM']) || isset($_SERVER['HTTP_COMING_FROM'])) {
			// case 3: proxy && only exotic variables defined
			// the exotic variables are not enough, we add the REMOTE_ADDR of the proxy
			return ( $_SERVER['REMOTE_ADDR'] .
			'_' . $_SERVER['HTTP_X_COMING_FROM'] .
			'_' . $_SERVER['HTTP_COMING_FROM']
			);
		}
		// case 4: no proxy (or tricky case: proxy+refresh)
		if (isset($_SERVER['REMOTE_HOST'])) {
			$array = self::extractIP($_SERVER['REMOTE_HOST']);
			if ($array && count($array) >= 1) {
				return $array[0]; // first IP in the list
			}
		}
		return $_SERVER['REMOTE_ADDR'];
	}

	public static function extractIP($ip) {
		if (ereg("^([0-9]{1,3}\.){3,3}[0-9]{1,3}", $ip, $array))
			return $array;
		else
			return false;
	}
    
}
?>