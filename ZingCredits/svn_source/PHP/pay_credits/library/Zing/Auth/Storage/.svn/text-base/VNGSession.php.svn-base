<?php

require_once $GLOBALS['THRIFT_ROOT'] . '/Thrift.php';
require_once $GLOBALS['THRIFT_ROOT'] . '/protocol/TBinaryProtocol.php';
require_once $GLOBALS['THRIFT_ROOT'] . '/transport/TSocket.php';
require_once $GLOBALS['THRIFT_ROOT'] . '/transport/THttpClient.php';
require_once $GLOBALS['THRIFT_ROOT'] . '/transport/TFramedTransport.php';
require_once dirname(__FILE__) . '/fwsso/fwsso_types.php';
require_once dirname(__FILE__) . '/fwsso/SessionServiceRead.php';

class Zing_Auth_Storage_VNGSession {

	private $_socket = null;
	private $_transport = null;
	private $_protocol = null;
	private $_client_read = null;
	private $_client_write = null;
	private $_session = null;
	private $_sessionId = null;
	protected static $_instance = null;
	const HOST = "10.30.12.180";
	const PORT = 8080;

	/**
	 * *
	 *
	 * @return Zing_Auth
	 */
	public static function getInstance() {
		if (null === self::$_instance) {
			self::$_instance = new self();
		}
		return self::$_instance;
	}

	public function __construct() {
		$options['host'] = self::HOST;
		$options['port'] = self::PORT;
		$this->_socket = new TSocket($options['host'], $options['port'], true);
		$this->_transport = new TFramedTransport($this->_socket);
		$this->_protocol = new TBinaryProtocolAccelerated($this->_transport);
		$this->_client_read = null;
		$this->_client_write = null;
	}

	public function setSessionId($sessionId) {
		$this->_sessionId = $sessionId;
	}

	public function getSessionId() {
		return $this->_sessionId;
	}

	public function read() {
		try {
			if ($this->_session == null) {
				$client = $this->getClientRead();
				$this->openTransport();
				$this->_session = $client->GetSession($this->_sessionId);
			}
		} catch (Exception $e) {
			return false;
		}

		return $this->_session;
	}

	private function getClientRead() {
		if ($this->_client_read == null)
			$this->_client_read = new SessionServiceReadClient($this->_protocol);
		return $this->_client_read;
	}

	public function openTransport() {
		if (!$this->_transport->isOpen())
			$this->_transport->open();
	}

	public function __destruct() {
		if ($this->_transport->isOpen())
			$this->_transport->close();
	}

}
