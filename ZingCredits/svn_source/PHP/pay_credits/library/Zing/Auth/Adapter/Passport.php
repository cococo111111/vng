<?php
class Zing_Auth_Adapter_Passport implements Zing_Auth_Adapter_Interface
{
	private $_options;
	private $_service;
	function __construct($options)
	{
		$this->_options=$options;
		$this->_service=new Zing_Me_Passport_Service($options);
	}
	function doLogin($username,$password)
	{
		return $this->_service->doLogin($username,$password);
	}
	
}
?>