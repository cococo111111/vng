<?php
/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of Logger
 *
 * @author root
 */
class Logger {
    //put your code here
    const LOGGER = "logger";
    public static function getLogger(){
    	if(Zend_Registry::isRegistered(self::LOGGER)){
    		return Zend_Registry::get(self::LOGGER);
    	}
        $config = new Zend_Config_Ini(ROOT_PATH . '/config/log.ini');
        $config = $config->toArray();
        $date = new Zend_Date();
        $part = "YYYY-MM-dd";
        $fileName = $config[0]['writerParams']['stream'];
        $ext = substr($fileName, -4);
        $fileName = substr($fileName, 0, -4);
        $fileName = $fileName . "_" . $date->get($part) . $ext;
        $config[0]['writerParams']['stream'] = $fileName;
        $logger = Zend_Log::factory($config);
        Zend_Registry::set(self::LOGGER, $logger);
        return $logger;
    }
}
?>
