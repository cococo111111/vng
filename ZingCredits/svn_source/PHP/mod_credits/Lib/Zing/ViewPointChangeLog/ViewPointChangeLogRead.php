<?php

require_once $GLOBALS['THRIFT_ROOT'].'/Thrift.php';
require_once $GLOBALS['THRIFT_ROOT'].'/transport/TFramedTransport.php';

require_once $GLOBALS['THRIFT_ROOT'].'/transport/TSocket.php';
require_once $GLOBALS['THRIFT_ROOT'].'/protocol/TBinaryProtocol.php';

require_once $GLOBALS['LOCAL_LIB_PACKAGES_DIR'].'/ViewPointChangeLog/ViewPointChangeLog_types.php';
require_once $GLOBALS['LOCAL_LIB_PACKAGES_DIR'].'/ViewPointChangeLog/ViewPointChangeLogReadService.php';



class Zing_ViewPointChangeLog_ViewPointChangeLogRead {
	private $_host = '10.30.12.182';
	private $_port = 9086;
	private $_sendTimeOut = 100;
	private $_recvTimeOut = 4000;

	private $socket = null;
	private $transport = null;
	private $protocol = null;
	private $client = null;

	static $instance = array();

	public static function getInstance($options) {
		if (!is_array($options)) {
			throw new Exception('Error found in configuration !!!');
		}

		if (array_key_exists('host', $options)) {
			$host = $options['host'];
		}
		if (array_key_exists('port', $options)) {
			$port = $options['port'];
		}

		$key = "$host:$port";
		if (!isset ($instance[$key])) {
			$instance[$key] = new self($options);
		} // if
		return $instance[$key];
	} // getInstance

	public function __construct($options) {
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
		$this->client = new ViewPointChangeLogReadServiceClient($this->protocol);
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

	
	public function  getLogs($searchInfo, $page, $size){
		try {
			$this->openTransport();
			$data = $this->client-> getLogs($searchInfo, $page, $size);
		} catch (Exception $e) {
			//var_dump( $e);exit;
			$this->closeTransport();
			return array ();
		}
		return $data;

	}	
	
}
