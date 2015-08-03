<?php
require_once $GLOBALS['THRIFT_ROOT'].'/transport/TZSocket.php';
require_once $GLOBALS['LIB_PACKAGES_ROOT']. "/hugelistint/hugelistint_dataservice/THugeListInt_DataService.php";
class Zing_HugeList_Service
{
	public $_hugelistint_client;
	public $socket;
	public $transport;
	public $protocol;

	private $host;
	private $port;
	private $sendTimeout = 400;
    private $recvTimeout = 400;
    private $AUTH_KEY = "";//vd : local123@hugelistint_apptrial_whitelist"; cho service cua zingme theme
	private $APPLICATION_ID = 0;//vd : 1002;
    private static $_instances = array();

	private function __construct($authkey, $application_id, $options)
	{
		if (array_key_exists('host', $options))
	    	$this->host = $options['host'];
		if (array_key_exists('port', $options))
		    $this->port = $options['port'];
		if (array_key_exists('sendTimeout', $options))
		    $this->sendTimeout = $options['sendTmeout'];
		if (array_key_exists('recvTimeout', $options))
		    $this->recvTimeout = $options['recvTimeout'];

		$this->AUTH_KEY = $authkey;
		$this->APPLICATION_ID = $application_id;

		$this->socket = new TZSocket($this->host, $this->port);
		$this->socket->setSendTimeout($this->sendTimeout);
		$this->socket->setRecvTimeout($this->recvTimeout);
		$this->transport = new TFramedTransport($this->socket);
		$this->protocol = new TBinaryProtocolAccelerated($this->transport);
		$this->_hugelistint_client = new THugeListInt_DataServiceClient($this->protocol);
	}

	public static function getInstance($authkey, $application_id,$options) {

		$key = $application_id . ':' . $options['host'].':'.$options['port'];
		if (!isset(self::$_instances[$key]))
		    self::$_instances[$key] = new self($authkey, $application_id,$options);
		return self::$_instances[$key];
    }

	private function openTransport()
	{
		if(!$this->transport->isOpen())
		    $this->transport->open();
    }
    private function closeTransport()
    {
		if($this->transport->isOpen())
		    $this->transport->close();
    }
    public function  __destruct()
    {
		$this->closeTransport();
    }

	
	/*
	 * add user to hugelist
	 * param: userid
	 */
	public function add($userid)
	{
		//validate parameter
		if($userid === null){
			throw new Exception("Error input parameter userid:" .$userid);
		}

		try
		{
			$this->openTransport();
			$result = $this->_hugelistint_client->li_Add($this->AUTH_KEY, $this->APPLICATION_ID, $userid);
		}
		catch(Exception $ex)
		{
			$this->closeTransport();
			throw new Exception("Error when add user :".$ex->getMessage());
		}
	}

	/*
	 * add list user to hugelist
	 * param: listUserid
	 */
	public function addList($listUserid)
	{
		//validate parameter
		if($listUserid === null){
			throw new Exception("Error input parameter listUserid:" .$listUserid);
		}

		try
		{
			$this->openTransport();
			return $this->_hugelistint_client->li_AddList($this->AUTH_KEY, $this->APPLICATION_ID, $listUserid);
		}
		catch(Exception $ex)
		{
			$this->closeTransport();
			throw new Exception("Error when add list user :".$ex->getMessage());
		}
	}

	/*
	 * remove user from hugelist
	 * param: userid
	 */
	public function remove($userid)
	{
		//validate parameter
		if($userid === null){
			throw new Exception("Error input parameter userid:" .$userid);
		}

		try
		{
			$this->openTransport();
			return $this->_hugelistint_client->li_Remove($this->AUTH_KEY, $this->APPLICATION_ID, $userid);
		}
		catch(Exception $ex)
		{
			$this->closeTransport();
			throw new Exception("Error when remove user :".$ex->getMessage());
		}
	}

	/*
	 * remove list user from hugelist
	 * param: listUserid
	 */
	public function removeList($listUserid)
	{
		//validate parameter
		if($listUserid === null){
			throw new Exception("Error input parameter listUserid:" .$listUserid);
		}

		try
		{
			$this->openTransport();
			return $this->_hugelistint_client->li_RemoveList($this->AUTH_KEY, $this->APPLICATION_ID, $listUserid);
		}
		catch(Exception $ex)
		{
			$this->closeTransport();
			throw new Exception("Error when remove list user :".$ex->getMessage());
		}
	}

	/*
	 * remove all user from hugelist
	 */
	public function removeAll()
	{
		try
		{
			$this->openTransport();
			return $this->_hugelistint_client->li_RemoveAll($this->AUTH_KEY, $this->APPLICATION_ID);
		}
		catch(Exception $ex)
		{
			$this->closeTransport();
			throw new Exception("Error when remove all user :".$ex->getMessage());
		}
	}

	public function initAppId($appid) {
		try {
			$this->openTransport();
			$val = new wthugelistint_TValue();
			$val->entries = array();
			return $this->_hugelistint_client->put($this->AUTH_KEY, $appid, $val, 1);
		} catch (Exception $ex) {
			$this->closeTransport();
			throw new Exception("initAppId :" . $ex->getMessage());
		}
	}

}
?>