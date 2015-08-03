<?php
require_once 'Zend/Auth/Storage/Interface.php';
require_once $GLOBALS['THRIFT_ROOT'].'/Thrift.php';
require_once $GLOBALS['THRIFT_ROOT'].'/protocol/TBinaryProtocol.php';
require_once $GLOBALS['THRIFT_ROOT'].'/transport/TSocket.php';
require_once $GLOBALS['THRIFT_ROOT'].'/transport/THttpClient.php';
require_once $GLOBALS['THRIFT_ROOT'].'/transport/TFramedTransport.php';
require_once SES_DIR.'/sms_types.php';
require_once SES_DIR.'/SessionService.php';


class Zing_Auth_Storage_Session implements Zing_Auth_Storage_Interface
{
    
	private $_socket=null;
	private $_transport=null;
	private $_protocol=null;
	private $_client=null;
	private $_session=null;
	private $_sessionId=null;
	public function __construct($options)
    {
		$this->_socket = new TSocket($options['host'], $options['port']);
		$this->_transport = new TFramedTransport($this->_socket, 1024, 1024);
		$this->_protocol = new TBinaryProtocol($this->_transport);
		$this->_client = new SessionServiceClient($this->_protocol);
    }

    public function setSessionId($sessionId)
	{
		$this->_sessionId=$sessionId;
	}
	public function getSessionId()
	{
		return $this->_sessionId;
	}
    public function isEmpty()
    {
        return ($this->_session==null);
    }
	public function create($uin, $zingId, $accountName)
	{
			$localIp=isset($_SERVER['REMOTE_ADDR'])?$_SERVER['REMOTE_ADDR']:'127.0.0.1';
			$longSession=0;//default là session dài
			$this->_transport->open();
			$this->_sessionId= $this->_client->Create($uin, $zingId, $accountName, $localIp, $longSession);
			$this->_transport->close();
			return $this->_sessionId;
	}
    /**
     * Defined by Zend_Auth_Storage_Interface
     *
     * @return mixed
     */
    public function read()
    {
		try
		{
		   if ($this->_session==null)
			{
				$this->_transport->open();
				$this->_session= $this->_client->GetSession($this->_sessionId);
				$this->_transport->close();
			}
		}
		catch(Exception $e)
		{
			return false;
		}
		return $this->_session;
    }

    /**
     * Defined by Zend_Auth_Storage_Interface
     *
     * @param  mixed $contents
     * @return void
     */
    public function write($contents)
    {
        //chua implement
    }

    /**
     * Defined by Zend_Auth_Storage_Interface
     *
     * @return void
     */
    public function clear()
    {
		$this->_transport->open();
		$this->_client->Remove($this->_sessionId);
		$this->_transport->close();
		$this->_session=null;
    }
}
