<?php
require_once ZENDLIB_PATH.'Zing/Log/ScriberAbstract.php';
class ScriberPayment extends  Log_ScriberAbstract{

    const CATTEGORY = 'ZINGAPP_ZINGCREDITS_FRONTEND';
    // ServerIP   RequestDomain      ClientIP        UserID		Username        AgentID        RefID    ResponseCode		refNo		LogStep		time
    const LOG_FORMAT = "%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s";
    

    private static $_instance = null;
    public function __construct($config)
    {
		parent::__construct($config);
    }

    public static function getInstance()
    {
	if(self::$_instance == null)
	{
	   $config = Zend_Registry::get('appconf');
	   if(empty($config)){
	       return null;
	   }
	   $configscribe['scribe_servers'] = explode(",",$config->scribe->host);	   
	   $configscribe['scribe_ports'] = explode(",",$config->scribe->port);
	   self::$_instance = new self($configscribe);
	}
	return self::$_instance;
    }
    private static function getClientIP(){
      if(!empty($_SERVER['HTTP_X_FORWARDED_FOR'])){
	return $_SERVER['HTTP_X_FORWARDED_FOR'];
      }
      else{
	return $_SERVER['REMOTE_ADDR'];
      }
    }
    private static function sendLog($username,$agentID,$refID,$responseCode,$refNo,$logStep){
      try{
     // $name2ID = new Zing_Name2ID_ServiceR();
     // $userID=$name2ID->getID($username);
      $time = time();
      $category = self::CATTEGORY;
      $clientip=ScriberPayment::getClientIP();      
      $serverip = $_SERVER["SERVER_ADDR"];
      $domain_name = $_SERVER["HTTP_HOST"];
      $message = sprintf(self::LOG_FORMAT,$serverip,$domain_name,$clientip,/*$userID,*/$username,$agentID,$refID,$responseCode,$refNo,$logStep,$time);
      //var_dump($message);return;
      $obj = self::getInstance();
      if(!($obj instanceof Log_ScriberAbstract)){echo 'not istanse';
	return false;
      }
      return $obj->_sendLog($category,$message);
      }
      catch (Exception $ex)
      {
	var_dump($ex);
	return false;
      }
    }
    /*
    *send log when user request a bill
    */
    public static function sendLogStep1($username,$agentID){
      return ScriberPayment::sendLog($username,$agentID,"","","",1,$time);
    }
    /*
    *send log when server parse data from billing info
    */
    public static function sendLogStep2($username,$agentID,$refID){
      return ScriberPayment::sendLog($username,$agentID,$refID,"","",2,$time);
    }
    /*
    *send log when server finish process user's bill
    */
    public static function sendLogStep3($username,$agentID,$refID,$responseCode,$refNo){
      return ScriberPayment::sendLog($username,$agentID,$refID,$responseCode,$refNo,3,$time);
    }
}
?>