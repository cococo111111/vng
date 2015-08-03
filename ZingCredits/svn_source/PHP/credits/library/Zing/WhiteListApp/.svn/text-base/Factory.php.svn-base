<?php

class Zing_WhiteListApp_Factory {

	protected static $_instances = array();

	private static $_featurelist = array(
		'zingmetheme' => 1002
	);

	/**
	 *
	 * @return Zing_WhiteListApp_BaseClass
	 */
	public static function factory($feature) {
		if(!array_key_exists($feature, self::$_featurelist)) return null;

		if(array_key_exists($feature,self::$_instances)) return self::$_instances[$feature];
	
		$config = Zend_Registry::get('appconf');

		if(!isset($config->hugelist->read)) {
			throw new Exception("No read config for hugelist");
		}
		if(!isset($config->hugelist->write)) {
			throw new Exception("No write config for hugelist");
		}
		if(!isset($config->hugelist->authkey)) {
			throw new Exception("No authkey config for hugelist");
		}

		$read_options = $config->hugelist->read->toArray();
		
		$write_options = $config->hugelist->write->toArray();
		$authkey = $config->hugelist->authkey;

		self::$_instances[$feature]= new Zing_WhiteListApp_BaseClass($authkey,self::$_featurelist[$feature],$read_options, $write_options);
		return self::$_instances[$feature];
	}

}
?>
