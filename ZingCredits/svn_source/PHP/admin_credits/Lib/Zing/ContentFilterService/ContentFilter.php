<?php

include_once($GLOBALS['THRIFT_ROOT'].'/Thrift.php');

include_once($GLOBALS['THRIFT_ROOT'].'/protocol/TBinaryProtocol.php');

include_once($GLOBALS['THRIFT_ROOT'].'/transport/TTransport.php');
include_once($GLOBALS['THRIFT_ROOT'].'/transport/TFramedTransport.php');
include_once($GLOBALS['THRIFT_ROOT'].'/transport/TSocket.php');

include_once $GLOBALS['LOCAL_LIB_PACKAGES_DIR'].'/ContentFilter/ContentFilterService.php';

class ContentFilter
{
	const HOST = '10.30.12.182';
	const PORT = 7712;
	const SEND_TIME_OUT = 100;
	const REV_TIME_OUT = 4000;	
	
	private $_host = '10.30.12.182';
	private $_port = 7712;
	private $_sendTimeOut = 100;
	private $_recvTimeOut = 4000;

	private $socket = null;
	private $transport = null;
	private $protocol = null;
	private $client = null;
	
	private static $instance;
	
	public static function getInstance($options) 
	{	
		if (!is_array($options)) 
		{
			throw new Exception('Error found in configuration !!!');
		}
		
		$host = ContentFilter::HOST;
		$port = ContentFilter::PORT;

		if (array_key_exists('host', $options)) 
		{
			$host = $options['host'];
		}
		if (array_key_exists('port', $options)) 
		{
			$port = $options['port'];
		}

		$key = "$host:$port";
		if (!isset (ContentFilter::$instance[$key])) 
		{
			ContentFilter::$instance[$key] = new ContentFilter($options);
		} // if
		return ContentFilter::$instance[$key];
	} // getInstance
	
	private function ContentFilter($options) 
	{
		if (array_key_exists('host', $options)) 
		{
			$this->_host = $options['host'];
		}
		if (array_key_exists('port', $options)) 
		{
			$this->_port = $options['port'];
		}
		if (array_key_exists('sendTimeOut', $options)) 
		{
			$this->_sendTimeOut = $options['sendTimeOut'];
		}
		if (array_key_exists('recvTimeOut', $options)) 
		{
			$this->_recvTimeOut = $options['recvTimeOut'];
		}
		$this->socket = new TSocket($this->_host, $this->_port);
		$this->socket->setSendTimeout($this->_sendTimeOut);
		$this->socket->setRecvTimeout($this->_recvTimeOut);
		$this->transport = new TFramedTransport($this->socket);
		$this->protocol = new TBinaryProtocolAccelerated($this->transport);
		
		$this->client = new ContentFilterServiceClient($this->protocol);
	}

   	function __destruct() {
       		$this->transport->close();
   	}
	
	private function closeTransport()
	{
		if($this->transport->isOpen()){
			$this->transport->close();
		}
	}
	private function openTransport()
	{
		if(!$this->transport->isOpen()){
			$this->transport->open();
		}
	}
	
	public function add(contentfilter_ContentFilterData $data)
	{
		try 
		{
			$this->openTransport();
			$this->client->add($data);			
		} 
		catch (Exception $e) 
		{
			echo $e;
			$this->closeTransport();
			return array ();
		}		
	}
	
  	public function remove($systemId, $appId, $objectContent)
  	{
		try 
		{
			$this->openTransport();
			$this->client->remove($systemId, $appId, $objectContent);			
		} 
		catch (Exception $e) 
		{
			echo $e;
			$this->closeTransport();
			return array ();
		}		 
  	}
  	
  	public function remove_multi($systemId, $appId, $objectContent)
  	{
		try 
		{
			$this->openTransport();
			$this->client->remove_multi($systemId, $appId, $objectContent);			
		} 
		catch (Exception $e) 
		{
			echo $e;
			$this->closeTransport();
			return array ();
		}		 
  	}
  	
  	public function approve($systemId, $appId, $objectContent)
  	{
		try 
		{
			$this->openTransport();
			$this->client->approve($systemId, $appId, $objectContent);			
		} 
		catch (Exception $e) 
		{
			echo $e;
			$this->closeTransport();
			return array ();
		}		 
  	}
  	
  	public function approve_multi($systemId, $appId, $objectContent)
  	{
		try 
		{
			$this->openTransport();
			$this->client->approve_multi($systemId, $appId, $objectContent);			
		} 
		catch (Exception $e) 
		{
			echo $e;
			$this->closeTransport();
			return array ();
		}		 
  	}
  	
  	public function filter($systemId, $appId, $toUserId, $keyWord, $resultSize,$offset)  	
  	{
		try 
		{
			$this->openTransport();
			$data = $this->client->filter($systemId, $appId, $toUserId, $keyWord, $resultSize,$offset);
			return $data;
		} 
		catch (Exception $e) 
		{
			echo $e;
			$this->closeTransport();
			return array ();
		}		 
  	}
	
  	public function Check()
  	{
		try 
		{
			return "OK";
		} 
		catch (Exception $e) 
		{
			echo $e;
			$this->closeTransport();
			return array ();
		}		 
  	}
}
