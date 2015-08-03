<?php

$GLOBALS['THRIFT_ROOT'] = ROOT_PATH.'/Lib/Apache/Thrift';
require_once $GLOBALS['THRIFT_ROOT'].'/packages/zingme_payment_type/zingme_payment_type_types.php';

require_once $GLOBALS['THRIFT_ROOT'].'/packages/zingme_payment_service/TAppServer.php';
require_once $GLOBALS['THRIFT_ROOT'].'/transport/TSocket.php';
require_once $GLOBALS['THRIFT_ROOT'].'/protocol/TBinaryProtocol.php';
require_once $GLOBALS['THRIFT_ROOT'].'/transport/TFramedTransport.php';
require_once $GLOBALS['THRIFT_ROOT'].'/transport/TBufferedTransport.php';
class AppInfo {
    private $client;
    private $transport;

    function  __construct() {
    }
	private function connect(){
        try{
	    $config = Zend_Registry::get('configuration');
            $options = array('host'=>$config->gameinfor->host,
                             'port'=>$config->gameinfor->port);		
	
            $socket = new TSocket($options['host'], $options['port']);
            $socket->setSendTimeout($config->gameinfor->connection->sendtimeout);
            $socket->setRecvTimeout($config->gameinfor->connection->recvtimeout); 	      
            $this->transport = new TFramedTransport($socket);
            $protocol = new TBinaryProtocol($this->transport);
            $this->client = new TAppServerClient($protocol);
        }catch(Exception $e){
            throw $e;
        }
    }

    public function getAppName($appID){
        try{
            $this->connect();
            $this->transport->open();
            $list = $this->client->getAppName($appID);
            $this->transport->close();
        }  catch (Exception $e){
            $this->transport->close();
            throw $e;
        }
        return $list;
    }
	 public function getAppInfo($appID){
        try{
            $this->connect();
            $this->transport->open();
            $list = $this->client->getAppInfo($appID);
            $this->transport->close();
        }  catch (Exception $e){
            $this->transport->close();
            throw $e;
        }
        return $list;
    }
     public function registerGameServer($appInfo,$adminSig,$createKey){
        try{
            $this->connect();
            $this->transport->open();
            $list = $this->client->registerGameServer($appInfo,$adminSig,$createKey);
            $this->transport->close();
        }  catch (Exception $e){
            $this->transport->close();
            throw $e;
        }
        return $list;
    }
     public function getAllAppInfo(){
        try{
            $this->connect();
            $this->transport->open();
            $list = $this->client->getAllAppInfo();
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
