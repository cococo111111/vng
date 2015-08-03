<?php
class Zing_Auth_Storage_Memcached implements Zing_Auth_Storage_Interface {
    private $_server=null;
    private $_options=null;
    private $_session=null;
    private $_sessionId=null;
    public function __construct($options) {
        $this->_options=$options;
        $this->_server=new Memcache();
        $this->_server->addServer($options['host'],$options['port']);
    }

    public function setSessionId($sessionId) {
        $this->_sessionId=$sessionId;
    }
    public function getSessionId() {
        return $this->_sessionId;
    }
    public function isEmpty() {
        return $this->_session==null;
    }

    public function create($uin, $zingId, $accountName) {
        $localIp=isset($_SERVER['REMOTE_ADDR'])?$_SERVER['REMOTE_ADDR']:'127.0.0.1';
        $longSession=1;//default l� session d�i
        $this->_sessionId=Zing_Auth_Util::genSessionKey(true,true);//gen Long session
        $this->_server->set($this->_sessionId,"$uin $zingId $accountName $localIp",0,0);//never expires
        return $this->_sessionId;
    }


    /**
     * Returns the contents of storage
     *
     * Behavior is undefined when storage is empty.
     *
     * @throws Zing_Auth_Storage_Exception If reading contents from storage is impossible
     * @return mixed
     */
    public function read() {
        if ($this->_session==null) {
            $this->_session= $this->_server->get($this->_sessionId);
        }
        return $this->_session;
    }

    /**
     * Writes $contents to storage
     *
     * @param  mixed $contents
     * @throws Zing_Auth_Storage_Exception If writing $contents to storage is impossible
     * @return void
     */
    public function write($contents) {}


    /**
     * Clears contents from storage
     *
     * @throws Zing_Auth_Storage_Exception If clearing contents from storage is impossible
     * @return void
     */
    public function clear() {
        $this->_session=null;
        $this->_server->delete($this->_sessionId);
    //$this->_sessionId=null;
    }
}
?>