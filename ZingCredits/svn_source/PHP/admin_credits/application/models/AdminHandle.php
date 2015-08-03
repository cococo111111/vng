<?php

$GLOBALS['THRIFT_ROOT'] = ROOT_PATH.'/Lib/Apache/Thrift';
require_once $GLOBALS['THRIFT_ROOT'].'/packages/zingme_payment_type/zingme_payment_type_types.php';

require_once $GLOBALS['THRIFT_ROOT'].'/packages/zingme_payment_service/TAdminServer.php';
require_once $GLOBALS['THRIFT_ROOT'].'/transport/TSocket.php';
require_once $GLOBALS['THRIFT_ROOT'].'/protocol/TBinaryProtocol.php';
require_once $GLOBALS['THRIFT_ROOT'].'/transport/TFramedTransport.php';
require_once $GLOBALS['THRIFT_ROOT'].'/transport/TBufferedTransport.php';
class AdminHandle {
    private $client;
    private $transport;

    function  __construct() {
    }
	private function connect(){
        try{
	    $config = Zend_Registry::get('configuration');
            $options = array('host'=>$config->admin->host,
                             'port'=>$config->admin->port);		
	
            $socket = new TSocket($options['host'], $options['port']);
            $socket->setSendTimeout($config->admin->connection->sendtimeout);
            $socket->setRecvTimeout($config->admin->connection->recvtimeout); 	      
            $this->transport = new TFramedTransport($socket);
            $protocol = new TBinaryProtocol($this->transport);
            $this->client = new TAdminServerClient($protocol);
        }catch(Exception $e){
            throw $e;
        }
    }

   
     
    
    
        public function adjustUser($userID,$adjustMoney,$adminSig,$reason){
        try{
            $this->connect();
            $this->transport->open();
           
            $list = $this->client->adjustUser($userID,$adjustMoney,$adminSig,$reason,AdminHandle::getClientIP(),time());
           $this->transport->close();
        }  catch (Exception $e){
            $this->transport->close();
            throw $e;
        }
        return $list;
    }
   private static function getClientIP(){
      if(!empty($_SERVER['HTTP_X_FORWARDED_FOR'])){
	return $_SERVER['HTTP_X_FORWARDED_FOR'];
      }
      else{
	return $_SERVER['REMOTE_ADDR'];
      }
    }
}
