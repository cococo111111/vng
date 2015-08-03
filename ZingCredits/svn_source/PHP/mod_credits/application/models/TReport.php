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
require_once $GLOBALS['THRIFT_ROOT'].'/packages/zingme_payment_type/zingme_payment_type_types.php';
require_once $GLOBALS['THRIFT_ROOT'].'/packages/zingme_payment_service/TReport.php';
require_once $GLOBALS['THRIFT_ROOT'].'/transport/TSocket.php';
require_once $GLOBALS['THRIFT_ROOT'].'/protocol/TBinaryProtocol.php';
require_once $GLOBALS['THRIFT_ROOT'].'/transport/TFramedTransport.php';
require_once $GLOBALS['THRIFT_ROOT'].'/transport/TBufferedTransport.php';
class TReport {
    //put your code here
    private $client;
    private $transport;
    function  __construct() {
    }
	private function connect(){
        try{
 	  $config = Zend_Registry::get('configuration');
            $options = array('host'=>$config->report->host,
                             'port'=>$config->report->port);	

            $socket = new TSocket($options['host'], $options['port']);
            $socket->setSendTimeout($config->report->connection->sendtimeout);
            $socket->setRecvTimeout($config->report->connection->recvtimeout);
            $this->transport = new TFramedTransport($socket);
            $protocol = new TBinaryProtocol($this->transport);
            $this->client = new TReportClient($protocol);
        }catch(Exception $e){
            throw $e;
        }
    }

  
public function getTransByUser($userID, $txID, $startTime, $endTime, $startIndex, $numRow, $txStatus){
        try{
            $this->connect();
            $this->transport->open();
            $tranx = $this->client->getTransByUser($userID, $txID, $startTime, $endTime, $startIndex, $numRow, $txStatus);
            $this->transport->close();
        }  catch (Exception $e){
            $this->transport->close();
            throw $e;
        }
        return $tranx;
    }
public function getTransStatus($txID, $localTime){
        try{
            $this->connect();
            $this->transport->open();
            $tranx = $this->client->getTransStatus($txID, $localTime);
            $this->transport->close();
        }  catch (Exception $e){
            $this->transport->close();
            throw $e;
        }
        return $tranx;
    }
public function getTransByApp($appID, $startTime, $endTime, $tranxType,$startIndex,$numRow, $txStatus){
        try{
            $this->connect();
            $this->transport->open();
            $tranx = $this->client->getTransByApp($appID, $startTime, $endTime, $tranxType,$startIndex,$numRow, $txStatus);
            $this->transport->close();
        }  catch (Exception $e){
            $this->transport->close();
            throw $e;
        }
        return $tranx;
    }

}
?>
