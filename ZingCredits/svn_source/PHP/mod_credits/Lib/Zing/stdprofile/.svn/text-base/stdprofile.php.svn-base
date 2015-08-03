<?php

include_once LIB_PATH. 'Zing/Packages/stdprofile2/stdprofile2/StdProfile2Service_Rd.php';

class Zing_Me_Profile_Profile
{
        /**
         * @var TSocket
         */
        private $socket;

        /**
         * @var TTransport
         */
        private $transport;

        /**
         * @var TProtocol
         */
        private $protocol;

        /**
         * @var TStdProfileServiceClient
         */
        private $client;

       
	private $_handle=null;
        private $sendTimeout = 1000;
        private $recvTimeout = 1000;

      

        

        function __construct($options)
        {
                $config = Zend_Registry::get('configuration');	
                $this->socket = new TSocket($config->stdprofile->host, $config->stdprofile->port);
                $this->socket->setSendTimeout($this->sendTimeout);
                $this->socket->setRecvTimeout($this->recvTimeout);
                $this->transport = new TFramedTransport($this->socket);
                $this->protocol = new TBinaryProtocolAccelerated($this->transport);
                $this->client = new StdProfile2Service_RdClient($this->protocol);

		$this->_handle = new zcommon_OpHandle();
                $this->_handle->source = $config->stdprofile->source;
                $this->_handle->auth = $config->stdprofile->auth;
        }

        function  __destruct()
        {
                //Globals::dumpLog('__destruct transport close');
                if($this->transport->isOpen()) $this->transport->close();
                //$this->writeLog('__destruct transport close ' . microtime(true));
        }

     

    public function getStdProfile($uid)
        {
                try
                {
                        $this->_openTransport();
                        $result = $this->client->get($this->_handle,$uid);
                        return $result;
                }
                catch(Exception $e)
                {                       
                        $this->_closeTransport();
                        return null;
                }
        }
    public function getUID($uname)
        {
                try
                {
                        $this->_openTransport();
                        $result = $this->client->getUID($this->_handle,$uname);
                        return $result;
                }
                catch(Exception $e)
                {                       
                        $this->_closeTransport();
                        return null;
                }
        }

    

        private function _openTransport()
        {
                if(!$this->transport->isOpen()) $this->transport->open();
        }

        private function _closeTransport()
        {
                if($this->transport->isOpen()) $this->transport->close();
        }

      

}
?>
