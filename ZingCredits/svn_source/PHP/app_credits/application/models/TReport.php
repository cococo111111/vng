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

    public function getTransaction($txID, $startTime, $endTime){
        try{
            $this->connect();
            $this->transport->open();
            $balance = $this->client->getTransaction($txID, $startTime, $endTime);
            $this->transport->close();
        }  catch (Exception $e){
            $this->transport->close();
            throw $e;
        }
        return $balance;
    }


    public function getAdminAction($startTime, $endTime, $startIndex, $numRow){
        try{
            $this->connect();
            $this->transport->open();
            $tranx = $this->client->getAdminAction($startTime, $endTime, $startIndex, $numRow);
            $this->transport->close();
        }  catch (Exception $e){
            $this->transport->close();
            throw $e;
        }
        return $tranx;
    }
    public function getTotalMoneyOnUser($userID, $startTime, $endTime, $appID, $status){
    try{
	$this->connect();
	$this->transport->open();
	$tranx = $this->client->getTotalMoneyOnUser($userID, $startTime, $endTime, $appID, $status);
	$this->transport->close();
    }  catch (Exception $e){
	$this->transport->close();
	throw $e;
    }
    return $tranx;
}
public function getTotalMoneyOnApp($appID, $startTime, $endTime, $status){
        try{
            $this->connect();
            $this->transport->open();
            $tranx = $this->client->getTotalMoneyOnApp($appID, $startTime, $endTime, $status);
            $this->transport->close();
        }  catch (Exception $e){
            $this->transport->close();
            throw $e;
        }
        return $tranx;
    }
public function countTransactionsOnUser($userID, $startTime, $endTime, $appID, $status){
        try{
            $this->connect();
            $this->transport->open();
            $tranx = $this->client->countTransactionsOnUser($userID, $startTime, $endTime, $appID, $status);
            $this->transport->close();
        }  catch (Exception $e){
            $this->transport->close();
            throw $e;
        }
        return $tranx;
    }
public function countTransactionsOnApp($appID, $startTime, $endTime, $status){
        try{
            $this->connect();
            $this->transport->open();
            $tranx = $this->client->countTransactionsOnApp($appID, $startTime, $endTime, $status);
            $this->transport->close();
        }  catch (Exception $e){
            $this->transport->close();
            throw $e;
        }
        return $tranx;
    }
public function getListTransaction($userID, $startTime, $endTime, $appID, $status, $startIndex, $numRow){
        try{
            $this->connect();
            $this->transport->open();
            $tranx = $this->client->getListTransaction($userID, $startTime, $endTime, $appID, $status, $startIndex, $numRow);
            $this->transport->close();
        }  catch (Exception $e){
            $this->transport->close();
            throw $e;
        }
        return $tranx;
    }
public function getCompletedTrans($userID, $startTime, $endTime){
        try{
            $this->connect();
            $this->transport->open();
            $tranx = $this->client->getCompletedTrans($userID, $startTime, $endTime);
            $this->transport->close();
        }  catch (Exception $e){
            $this->transport->close();
            throw $e;
        }
        return $tranx;
    }
public function getUserTrans($userID, $startTime, $endTime, $startIndex, $numRow){
        try{
            $this->connect();
            $this->transport->open();
            $tranx = $this->client->getUserTrans($userID, $startTime, $endTime, $startIndex, $numRow);
            $this->transport->close();
        }  catch (Exception $e){
            $this->transport->close();
            throw $e;
        }
        return $tranx;
    }
public function getUserTransStatus($txID, $localTime){
        try{
            $this->connect();
            $this->transport->open();
            $tranx = $this->client->getUserTransStatus($txID, $localTime);
            $this->transport->close();
        }  catch (Exception $e){
            $this->transport->close();
            throw $e;
        }
        return $tranx;
    }
public function getTransByApp($appID, $startTime, $endTime, $tranxStatus, $tranxType,$startIndex,$numRow){
        try{
            $this->connect();
            $this->transport->open();
            $tranx = $this->client->getTransByApp($appID, $startTime, $endTime, $tranxStatus, $tranxType,$startIndex,$numRow);
            $this->transport->close();
        }  catch (Exception $e){
            $this->transport->close();
            throw $e;
        }
        return $tranx;
    }
public function summary($appID, $startTime, $endTime){
        try{
            $this->connect();
            $this->transport->open();
            $tranx = $this->client->summary($appID, $startTime, $endTime);
            $this->transport->close();
        }  catch (Exception $e){
            $this->transport->close();
            throw $e;
        }
        return $tranx;
    }
public function summaryDaily($appID, $startTime, $endTime){
        try{
            $this->connect();
            $this->transport->open();
            $tranx = $this->client->summaryDaily($appID, $startTime, $endTime);
            $this->transport->close();
        }  catch (Exception $e){
            $this->transport->close();
            throw $e;
        }
        return $tranx;
    }
}
?>
