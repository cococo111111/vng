<?php
/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of MAC
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
class MAC {
    //put your code here
    private $client;
    private $transport;
    private $tx;
    function  __construct($tx) {
        $this->tx = $tx;
    }
	private function connect(){
        try{
            $config = $this->getConfig();
            $options = array('host'=>$config->thrift->host,
                             'port'=>$config->thrift->port);

            $socket = new TSocket($options['host'], $options['port']);
            $socket->setSendTimeout($config->thrift->timeout->send);
            $socket->setRecvTimeout($config->thrift->timeout->recv);
			$this->transport = new TFramedTransport($socket);
            $protocol = new TBinaryProtocol($this->transport);
            $this->client = new T_PaymentBOClient($protocol);
        }catch(Exception $e){
            throw $e;
        }
    }
    
    public function checkMAC(){
        try{
            $this->connect();
            $this->transport->open();
            $this->client->checkMAC($this->tx);
        }  catch (Exception $e){
            throw $e;
        }
    }
}
?>
