<?php


class Zing_ViewPointChangeLog implements Zing_ViewPointChangeLogIF {

	protected static $_instance = null;

	function __construct() {

	}
	static public function getInstance() {

		if(self::$_instance == null) {
			self::$_instance = new self();
		}
		return self::$_instance ;

	}


	//read
	public function getLogs($searchInfo, $page, $size) {
		$viewPointImpl = $this->getReadAdapter();
		$rs=$viewPointImpl->getLogs($searchInfo, $page, $size);
     	return $rs;
	}

	public function getReadAdapter() {
		$options = array('host'=>VIEW_POINT_READ_HOST, 'port'=>VIEW_POINT_READ_PORT);
		return Zing_ViewPointChangeLog_ViewPointChangeLogRead::getInstance($options);
	}


}