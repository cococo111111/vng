<?php
$GLOBALS['THRIFT_ROOT'] = ROOT_PATH.'/library/Apache/Thrift';

require_once $GLOBALS['THRIFT_ROOT'].'/packages/zingme_payment_type/zingme_payment_type_types.php';
require_once $GLOBALS['THRIFT_ROOT'].'/packages/zingme_payment_service/TLatestCache.php';
require_once $GLOBALS['THRIFT_ROOT'].'/packages/zingme_payment_service/TZKBackEnd.php';
require_once $GLOBALS['THRIFT_ROOT'].'/transport/TSocket.php';
require_once $GLOBALS['THRIFT_ROOT'].'/protocol/TBinaryProtocol.php';
require_once $GLOBALS['THRIFT_ROOT'].'/transport/TFramedTransport.php';
require_once $GLOBALS['THRIFT_ROOT'].'/transport/TBufferedTransport.php';
class Tranx {
    private $client;
    private $transport;
    private $tx;
    private $token;
    function  __construct() {
    }
	private function connect(){
        try{
	    $config = Zend_Registry::get('appconf');
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

	private function backendConnect(){
        try{
	    $config = Zend_Registry::get('appconf');	
            $options = array('host'=>$config->backend->host,
                             'port'=>$config->backend->port);
            $socket = new TSocket($options['host'], $options['port']);
            $socket->setSendTimeout($config->backend->connection->sendtimeout);
            $socket->setRecvTimeout($config->backend->connection->recvtimeout);
            $this->transport = new TFramedTransport($socket);
            $protocol = new TBinaryProtocol($this->transport);
            $this->client = new TZKBackEndClient($protocol);
        }catch(Exception $e){var_dump($e);
            throw $e;
        }
    }
    public function rollbackTransaction($tranxId){
	try{
	
            $this->backendConnect();
            $this->transport->open();
            $returnCode = $this->client->rollbackTransaction($tranxId);
            $this->transport->close();
        }  catch (Exception $e){
            $this->transport->close();
            throw $e;
        }
        return $returnCode;
    }
	
    public function retryTransaction($tranxId){
	try{
            $this->backendConnect();
            $this->transport->open();
            $returnCode = $this->client->retryTransaction($tranxId);
            $this->transport->close();
        }  catch (Exception $e){
            $this->transport->close();
            throw $e;
        }
        return $returnCode;
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
}
