<?php
/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of Warmup
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
class Warmup {
    //put your code here
    private $client;
    private $transport;
    function  __construct() {
    }
    private function connect(){
        try{
            $config = Zend_Registry::get('appconf');
 	    $options = array('host'=>$config->billing->host,
                             'port'=>$config->billing->port);         

            $socket = new TSocket($options['host'], $options['port']);
            $socket->setSendTimeout($config->billing->host);
            $socket->setRecvTimeout($config->billing->port);
            $this->transport = new TFramedTransport($socket);
            $protocol = new TBinaryProtocol($this->transport);
            $this->client = new TPaymentClient($protocol);
        }catch(Exception $e){
            throw $e;
        }
    }

    public function warmup($userID){
        try{
            $this->connect();
//            echo "connect OK <br>";
            $this->transport->open();
            $resCode = $this->client->warmupCache($userID);
            $this->transport->close();
//            var_dump($resCode);
        }  catch (Exception $e){
            $this->transport->close();

            throw $e;
        }
        return $resCode;
    }
}
?>
