<?php
$GLOBALS['THRIFT_ROOT'] = ROOT_PATH.'/Lib/Apache/Thrift';

require_once LIB_PATH. 'Zing/Packages/name2id_service/name2id_service_constants.php';
require_once LIB_PATH. 'Zing/Packages/name2id_service/name2id_service_types.php';
require_once LIB_PATH. 'Zing/Packages/name2id_service/TName2IDService.php';
require_once $GLOBALS['THRIFT_ROOT'].'/transport/TZSocket.php';
require_once $GLOBALS['THRIFT_ROOT'].'/protocol/TBinaryProtocol.php';
require_once $GLOBALS['THRIFT_ROOT'].'/transport/TFramedTransport.php';
require_once $GLOBALS['THRIFT_ROOT'].'/transport/TBufferedTransport.php';

class Zing_Name2ID_ServiceR
{
	private $client;
   	private $transport; 	
       private static $_instances = array();
	function  __construct() {
    }
	private function connect(){
        try{
	    $config = Zend_Registry::get('configuration');	
            $options = array('host'=>$config->name2id->host,
                             'port'=>$config->name2id->port);
            $socket = new TZSocket($options['host'], $options['port']);
            $socket->setSendTimeout(1000);
            $socket->setRecvTimeout(1000);
            $this->transport = new TFramedTransport($socket);
            $protocol = new TBinaryProtocolAccelerated($this->transport);
            $this->client = new TName2IDServiceClient($protocol);
        }catch(Exception $e){           
            throw $e;
        }
    }

	public function getID($userName)
	{
		$result=false;
		try{
		    $this->connect();
		    $this->transport->open();
		    $result = $this->client->getID($userName);
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
