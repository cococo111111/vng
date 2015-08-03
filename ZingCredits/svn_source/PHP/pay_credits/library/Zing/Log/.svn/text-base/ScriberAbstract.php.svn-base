<?php

$GLOBALS['THRIFT_ROOT'] = ROOT_PATH.'/library/Apache/Thrift';
$GLOBALS['SCRIBE_ROOT'] = ZENDLIB_PATH.'Zing/Log/scribe';
require_once( ZENDLIB_PATH.'Zing/Log/scriber.php');
class Log_ScriberAbstract {

    private $_scriber = null;
    protected function __construct($config = array())
    {
	$this->_scriber = new scriber($config);
    }
    protected function _sendLog($category,$message) {
	try
	{
	    return $this->_scriber->writeLog($category,$message);
	}
	catch (Exception $ex)
	{
	    throw new Exception($ex->getMessage());
	}
    }
    
	protected function _sendLogs($category,$messages) {
		try
		{
		    return $this->_scriber->writeLogs($category,$messages);
		}
		catch (Exception $ex)
		{
		    throw new Exception($ex->getMessage());
		}
    }
    
}
?>