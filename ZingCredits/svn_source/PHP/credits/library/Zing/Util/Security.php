<?php
class Zing_Util_Security{
	const DEFAULT_KEY = "payment_gateway";
	const DEFAULT_IV = "zingme00";
	public static function encryp($key, $input, $iv){
	    $td = mcrypt_module_open('tripledes', '', 'ecb', '');
	    $iv = mcrypt_create_iv (mcrypt_enc_get_iv_size($td), MCRYPT_RAND);
	    mcrypt_generic_init($td, $key, $iv);
	    $encrypted_data = mcrypt_generic($td, $input);
	    mcrypt_generic_deinit($td);
	    mcrypt_module_close($td);
	    return $encrypted_data;
	}
	public static function decryp($key, $encrypted, $iv){
	    $td = mcrypt_module_open('tripledes', '', 'ecb', '');
	    $iv = mcrypt_create_iv (mcrypt_enc_get_iv_size($td), MCRYPT_RAND);
	    mcrypt_generic_init($td, $key, $iv);
	    $decrypted_data = mdecrypt_generic($td, $encrypted);
	    mcrypt_generic_deinit($td);
	    mcrypt_module_close($td);
	    return $decrypted_data;
	}
}
?>