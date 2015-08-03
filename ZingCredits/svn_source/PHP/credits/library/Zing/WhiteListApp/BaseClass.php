<?php

class Zing_WhiteListApp_BaseClass
{
	private $_authkey = '';
	private $_application_id = '';

	private $_read_options = array();
	private $_read = null;

	private $_write_options = array();
	private $_write = null;
	
	function __construct($authkey, $application_id, $read_options, $write_options) {		
		$this->_authkey = $authkey;
		$this->_application_id = $application_id;
		$this->_read_options = $read_options;
		$this->_write_options = $write_options;
		

	}

	public function checkInList($userid) {
		$read = $this->_getReadService();		
		return $read->hasEntry($userid);
	}

	

	/**
	 *
	 * @return Zing_HugeList_ServiceR
	 */
	private function _getReadService() {
		if($this->_read != null) return $this->_read;

		if($this->_read_options == null) {
			throw new Exception("No config for read instance", -1);
		}

		$this->_read = Zing_HugeList_ServiceR::getInstance($this->_authkey, $this->_application_id, $this->_read_options);
		return $this->_read;
	}

	/**
	 *
	 * @return Zing_HugeList_Service
	 */
	private function _getWriteService() {
		if($this->_write != null) return $this->_write;
		if($this->_write_options == null) {
			throw new Exception("No config for write instance", -2);
		}
		$this->_write = Zing_HugeList_Service::getInstance($this->_authkey, $this->_application_id,$this->_write_options);
		return $this->_write;
	}


}


?>
