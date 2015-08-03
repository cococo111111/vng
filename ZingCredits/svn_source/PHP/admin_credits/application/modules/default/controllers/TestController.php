<?php

require_once APPLICATION_PATH.'/models/testZCypherPX/ZCommon.php';
require_once APPLICATION_PATH.'/models/testZCypherPX/ZCypher.php';

class TestController extends Zend_Controller_Action
{

public function init()
	{
		
	}
	
	public function decodeAction()
	
	{

try{
$array_of_params = $this->_request->getParams ();
$key = new zcommon_StringHolder();
$key->value = $array_of_params['key'];

 
$data = new zcommon_StringHolder();

$data->value=$array_of_params['data'];
$params2 = new std__vectorT_std__string_t();
$e = ZCypher::zma_decode($params2, $data, $key, 0, 0);

self::dumpVector($params2);

}catch(Exception $ex){var_dump($ex);die();}

die();
	}
function dumpVector($list) {
	$size = $list->size();
	echo 'Size: ' . $size . '<br/>';
	for ($i = 0; $i < $size; ++$i) {
		$item = $list->get($i);
		var_dump($item);
		echo '<br/>';
	}
}
}
