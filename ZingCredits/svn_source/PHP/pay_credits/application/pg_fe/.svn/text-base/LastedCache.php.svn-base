<?php
/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of Billing
 *
 * @author root
 */
$GLOBALS['THRIFT_ROOT'] = ROOT_PATH.'/library/Apache/Thrift';

require_once $GLOBALS['THRIFT_ROOT'].'/packages/zingme_payment_type/zingme_payment_type_types.php';
require_once $GLOBALS['THRIFT_ROOT'].'/packages/zingme_payment_service/TLatestCache.php';
require_once $GLOBALS['THRIFT_ROOT'].'/transport/TSocket.php';
require_once $GLOBALS['THRIFT_ROOT'].'/protocol/TBinaryProtocol.php';
require_once $GLOBALS['THRIFT_ROOT'].'/transport/TFramedTransport.php';
require_once $GLOBALS['THRIFT_ROOT'].'/transport/TBufferedTransport.php';
class LastedCache {
    //put your code here
    private $client;
    private $transport;
    private $userID;
    private $tranxID;
    function  __construct($userID, $tranxID) {
        $this->userID = $userID;
        $this->tranxID = $tranxID;
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

   public function getTransactionStatus(){
        try{
            $this->connect();
            $this->transport->open();
            $tranx = $this->client->getTransactionStatus($this->userID,$this->tranxID);
            $this->transport->close();
        }  catch (Exception $e){
            $this->transport->close();

            throw $e;
        }
        return $tranx;
    }    
}
?>
