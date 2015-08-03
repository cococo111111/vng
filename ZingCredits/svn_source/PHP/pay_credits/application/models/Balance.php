<?php
/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of Balance
 *
 * @author root
 */
$GLOBALS['THRIFT_ROOT'] = ROOT_PATH.'/library/Apache/Thrift';

require_once $GLOBALS['THRIFT_ROOT'].'/packages/zingme_payment_type/zingme_payment_type_types.php';
require_once $GLOBALS['THRIFT_ROOT'].'/packages/zingme_payment_service/TPayment.php';
require_once $GLOBALS['THRIFT_ROOT'].'/transport/TSocket.php';
require_once $GLOBALS['THRIFT_ROOT'].'/protocol/TBinaryProtocol.php';
require_once $GLOBALS['THRIFT_ROOT'].'/transport/TFramedTransport.php';
require_once $GLOBALS['THRIFT_ROOT'].'/transport/TBufferedTransport.php';
class Balance {
    //put your code here
    private $client;
    private $transport;
    private $tx;
    private $token;
    function  __construct() {
    }
	private function connect(){
        try{
 	  $config = Zend_Registry::get('appconf');
            $options = array('host'=>$config->balance->host,
                             'port'=>$config->balance->port);	

            $socket = new TSocket($options['host'], $options['port']);
            $socket->setSendTimeout($config->balance->connection->sendtimeout);
            $socket->setRecvTimeout($config->balance->connection->recvtimeout);
            $this->transport = new TFramedTransport($socket);
            $protocol = new TBinaryProtocol($this->transport);
            $this->client = new TPaymentClient($protocol);
        }catch(Exception $e){
            throw $e;
        }
    }

    public function getBalance($userID){
        try{
            $this->connect();
            $this->transport->open();
            $balance = $this->client->getBalance($userID);
            $this->transport->close();
        }  catch (Exception $e){
            $this->transport->close();
            throw $e;
        }
        return $balance;
    }


    public function parseTransaction($agentID, $encodedData){
        try{
            $this->connect();
            $this->transport->open();
            $tranx = $this->client->parseTransaction($agentID, $encodedData);
            $this->transport->close();
        }  catch (Exception $e){
            $this->transport->close();
            throw $e;
        }
        return $tranx;
    }
    public function zingUnSignature($agentID, $encodedData,$kindOfKey){
    try{
	$this->connect();
	$this->transport->open();
	$tranx = $this->client->zingUnSignature($agentID, $encodedData,$kindOfKey);
	$this->transport->close();
    }  catch (Exception $e){
	$this->transport->close();
	throw $e;
    }
    return $tranx;
}
}
?>
