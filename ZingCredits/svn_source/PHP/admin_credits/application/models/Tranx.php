<?php


require_once $GLOBALS['THRIFT_ROOT'].'/packages/zingme_payment_type/zingme_payment_type_types.php';
require_once $GLOBALS['THRIFT_ROOT'].'/packages/zingme_payment_service/TLatestCache.php';
require_once $GLOBALS['THRIFT_ROOT'].'/transport/TSocket.php';
require_once $GLOBALS['THRIFT_ROOT'].'/protocol/TBinaryProtocol.php';
require_once $GLOBALS['THRIFT_ROOT'].'/transport/TFramedTransport.php';
require_once $GLOBALS['THRIFT_ROOT'].'/transport/TBufferedTransport.php';
class Tranx {
    private $client;
    private $transport;
    private $tx;

    function  __construct() {
    }
	private function connect(){
        try{
	    $config = Zend_Registry::get('configuration');
            $options = array('host'=>$config->transaction->host,
                             'port'=>$config->transaction->port);		
            $socket = new TSocket($options['host'], $options['port']);
            $socket->setSendTimeout($config->transaction->connection->sendtimeout);
            $socket->setRecvTimeout($config->transaction->connection->recvtimeout); 	      
            $this->transport = new TFramedTransport($socket);
            $protocol = new TBinaryProtocol($this->transport);
            $this->client = new TLatestCacheClient($protocol);
        }catch(Exception $e){
            throw $e;
        }
    }


    public function getLatestTx($userID){
        try{
            $this->connect();
            $this->transport->open();
            $list = $this->client->get($userID);
            $this->transport->close();
        }  catch (Exception $e){
            $this->transport->close();
            throw $e;
        }
        return $list;
    }
	 public function removeTransaction($userID, $tranxID){
        try{
            $this->connect();
            $this->transport->open();
            $this->client->removeTransaction($userID, $tranxID);
            $this->transport->close();
        }  catch (Exception $e){
            $this->transport->close();
            throw $e;
        }        
    }
}
