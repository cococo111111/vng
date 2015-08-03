<?php
$GLOBALS['THRIFT_ROOT'] = ROOT_PATH.'/library/Apache/Thrift';

require_once $GLOBALS['THRIFT_ROOT'].'/packages/zingme_payment_type/zingme_payment_type_types.php';
require_once $GLOBALS['THRIFT_ROOT'].'/packages/zingme_payment_service/TPayment.php';
require_once $GLOBALS['THRIFT_ROOT'].'/transport/TZSocket.php';
require_once $GLOBALS['THRIFT_ROOT'].'/protocol/TBinaryProtocol.php';
require_once $GLOBALS['THRIFT_ROOT'].'/transport/TFramedTransport.php';
require_once $GLOBALS['THRIFT_ROOT'].'/transport/TBufferedTransport.php';
require_once ZENDLIB_PATH. "Zing/Packages/hugelistint/hugelistint_dataservice/THugeListInt_DataServiceR.php";
class Zing_HugeList_ServiceR
{
	private $client;
   	private $transport;
 	private $AUTH_KEY = "";//vd : local123@hugelistint_apptrial_whitelist"; cho service cua zingme theme
	private $APPLICATION_ID = 0;//vd : 1002;
       private static $_instances = array();
	function  __construct() {
    }
	private function connect(){
        try{
	    $config = Zend_Registry::get('appconf');	
            $options = array('host'=>$config->hugelist->read->host,
                             'port'=>$config->hugelist->read->port);
	    $this->APPLICATION_ID=1002;
            $socket = new TZSocket($options['host'], $options['port']);
            $socket->setSendTimeout(1000);
            $socket->setRecvTimeout(1000);
            $this->transport = new TFramedTransport($socket);
            $protocol = new TBinaryProtocolAccelerated($this->transport);
            $this->client = new THugeListInt_DataServiceRClient($protocol);
        }catch(Exception $e){           
            throw $e;
        }
    }

	public function hasEntry($userid)
	{
		$result=false;
		try{
		    $this->connect();
		    $this->transport->open();
		    $result = $this->client->li_HasEntry($this->APPLICATION_ID,$userid);
		    $this->transport->close();
		}  catch (Exception $e){
			var_dump( $e);
		    $this->transport->close();
		    throw $e;
		}
		return $result;		
	}

	public function getSlice($startIndex, $count)
	{
		$result=0;
		try{
		    $this->connect();
		    $this->transport->open();
		    $result = $this->client->li_GetSlice($this->APPLICATION_ID,$startIndex, $count);
		    $this->transport->close();
		}  catch (Exception $e){
			var_dump( $e);
		    $this->transport->close();
		    throw $e;
		}
		return $result;		
	}
	public function getEntriesCount()
	{
		$result=0;
		try{
		    $this->connect();
		    $this->transport->open();
		    $result = $this->client->li_GetEntriesCount($this->APPLICATION_ID);
		    $this->transport->close();
		}  catch (Exception $e){
			var_dump( $e);
		    $this->transport->close();
		    throw $e;
		}
		return $result;		
	}

}
?>
