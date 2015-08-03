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
require_once $GLOBALS['THRIFT_ROOT'].'/packages/zingme_payment_service/TPayment.php';
require_once $GLOBALS['THRIFT_ROOT'].'/transport/TSocket.php';
require_once $GLOBALS['THRIFT_ROOT'].'/protocol/TBinaryProtocol.php';
require_once $GLOBALS['THRIFT_ROOT'].'/transport/TFramedTransport.php';
require_once $GLOBALS['THRIFT_ROOT'].'/transport/TBufferedTransport.php';
class Billing {
    //put your code here
    private $client;
    private $transport;
    private $agentID;
    private $data;
    private $token;
    private $clientIP;
    function  __construct($agentID,$data, $token, $clientIP) {
        $this->agentID = $agentID;
	 $this->data = $data;
        $this->token = $token;
        $this->clientIP=$clientIP;
    }
	private function connect(){
        try{
    	    $config = Zend_Registry::get('appconf');		
 	    $options = array('host'=>$config->billing->host,
                             'port'=>$config->billing->port);

            $socket = new TSocket($options['host'], $options['port']);
            $socket->setSendTimeout($config->billing->connection->sendtimeout);
            $socket->setRecvTimeout($config->billing->connection->recvtimeout);
            $this->transport = new TFramedTransport($socket);
            $protocol = new TBinaryProtocol($this->transport);
            $this->client = new TPaymentClient($protocol);
        }catch(Exception $e){
            throw $e;
        }
    }
/*
    public function bill(){
        try{
            $this->connect();
            $this->transport->open();
            $resCode = $this->client->bill($this->agentID,$this->data,$this->token);
            $this->transport->close();
        }  catch (Exception $e){
            $this->transport->close();

            throw $e;
        }
        return $resCode;
    }
*/
    public function bill(){
        try{
            $this->connect();
            $this->transport->open();
            $resCode = $this->client->bill($this->agentID,$this->data,$this->token,$this->clientIP);
            $this->transport->close();
        }  catch (Exception $e){
            $this->transport->close();

            throw $e;
        }
        return $resCode;
    }
    
}
?>
