<?php
include_once($GLOBALS['THRIFT_ROOT'].'/Thrift.php');

include_once($GLOBALS['THRIFT_ROOT'].'/protocol/TBinaryProtocol.php');

include_once($GLOBALS['THRIFT_ROOT'].'/transport/TTransport.php');
include_once($GLOBALS['THRIFT_ROOT'].'/transport/TFramedTransport.php');
include_once($GLOBALS['THRIFT_ROOT'].'/transport/TSocket.php');

include_once $GLOBALS['LOCAL_LIB_PACKAGES_DIR'].'/LogContentFilter/LogCommentFilterWriteService.php';

class LogWrite {

	private $_host = 'localhost';
	private $_port = 9086;
	private $_sendTimeOut = 100;
	private $_recvTimeOut = 4000;

	private $socket = null;
	private $transport = null;
	private $protocol = null;
	private $client = null;

	public static function getInstance($options) {
		if (!is_array($options)) {
			throw new Exception('Error found in configuration !!!');
		}

		static $instance;
		$host = 'localhost';
		$port = 9086;

		if (array_key_exists('host', $options)) {
			$host = $options['host'];
		}
		if (array_key_exists('port', $options)) {
			$port = $options['port'];
		}

		$key = "$host:$port";
		if (!isset ($instance[$key])) {
			$instance[$key] = new FollowerWrite($options);
		} // if
		return $instance[$key];
	} 

	public function LogWrite($options) {
		if (!is_array($options)) {
			throw new Exception('Error found in configuration !!!');
		}
		if (array_key_exists('host', $options)) {
			$this->_host = $options['host'];
		}
		if (array_key_exists('port', $options)) {
			$this->_port = $options['port'];
		}
		if (array_key_exists('sendTimeOut', $options)) {
			$this->_sendTimeOut = $options['sendTimeOut'];
		}
		if (array_key_exists('recvTimeOut', $options)) {
			$this->_recvTimeOut = $options['recvTimeOut'];
		}
		$this->socket = new TSocket($this->_host, $this->_port,true);
		$this->socket->setSendTimeout($this->_sendTimeOut);
		$this->socket->setRecvTimeout($this->_recvTimeOut);
		$this->transport = new TFramedTransport($this->socket);
		$this->protocol = new TBinaryProtocolAccelerated($this->transport);
		$this->client = new LogCommentFilterWriteServiceClient($this->protocol);
	}

	function __destruct() {
		$this->transport->close();
	}

	private function closeTransport() {
		if ($this->transport->isOpen()) {
			$this->transport->close();
		}
	}

	private function openTransport() {
		if (!$this->transport->isOpen()) {
			$this->transport->open();
		}
	}

	public function addLog($log){
		try {
			$this->openTransport();
			$data = $this->client->addLog($log);
		} catch (Exception $e) {
			echo $e;
			$this->closeTransport();
			return array ();
		}
		return $data;
	}

	public function addMultiLog($logs){
		try {
			$this->openTransport();
			$data = $this->client->addMultiLog($log);
		} catch (Exception $e) {
			echo $e;
			$this->closeTransport();
			return array ();
		}
		return $data;
	}



}
?>
