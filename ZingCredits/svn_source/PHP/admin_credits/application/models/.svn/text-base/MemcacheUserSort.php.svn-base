<?php

class MemcacheUserSort {
     private static  $_cache_instance = 'MemcacheUserSort';
    private static $_instance = NULL;
 public static function getInstance()
    {
        if(self::$_instance==NULL)
        {
            self::$_instance = new self();
        }
        return self::$_instance;
    }

	 private  function getCacheInstance()
    { try{
		$c = Zend_Registry::get('configuration');
		$servers = array();
		//var_dump($c->memcache->sortappinfo->host.' '.$c->memcache->sortappinfo->port);
		$servers[]=array('host' => $c->memcache->sortappinfo->host,'port' => $c->memcache->sortappinfo->port);
		$cache = new Zing_Cache_Memcache($servers,false,null,1024);
        return $cache;
	 }catch(Exception $e){
            throw $e;
        }
    }
  

  public function getAppSort($appid)
    {
//ini_set('display_errors', 1); 
//error_reporting(E_ALL);
try{
       $cache = $this->getCacheInstance();
       $data = $cache->getCache($appid);
       return $data;
 }catch(Exception $e){
           var_dump($e);die();
        }
    }
 public function setAppSort($appid,$sort)
    {
try{
       $cache = $this->getCacheInstance();
       $data = $cache->setCache($appid,$sort);
       return $data;
 }catch(Exception $e){
           var_dump($e);die();
        }
    }
}
