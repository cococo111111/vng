<?php
/*
 * Class: Zing_Admin_Auths_Storage.
 * Description: Auth Storage class for ContentFilter system.
 * Modifier: Khanv.
 * Last modified: Jan 12th 2011
 */
class Zing_Admin_Auth_Storage implements Zend_Auth_Storage_Interface 
{
	const STORAGE_SESSION_CACHE_KEY = "contentfilter.u.%s.session";
	const STORAGE_SESSION_CACHE_EXPIRE = 7200; // 2 HOURS
	const CACHE_INSTANCE = "adminsession";
	private $_zadminauth;
	const SESSION_CACHE_KEY = "";
	 /**
     * Returns true if and only if storage is empty
     *
     * @throws Zend_Auth_Storage_Exception If it is impossible to determine whether storage is empty
     * @return boolean
     */
    public function isEmpty()
    {
    		
    }

    /**
     * Returns the contents of storage
     *
     * Behavior is undefined when storage is empty.
     *
     * @throws Zend_Auth_Storage_Exception If reading contents from storage is impossible
     * @return mixed
     */
    public function read()
    {
    	$cache_key = sprintf(self::STORAGE_SESSION_CACHE_KEY,$this->_zadminauth);
		return Zing_Cache_ZingGlobalCache::getCache($cache_key,self::CACHE_INSTANCE);
    }

    /**
     * Writes $contents to storage
     *
     * @param  mixed $contents
     * @throws Zend_Auth_Storage_Exception If writing $contents to storage is impossible
     * @return void
     */
    public function write($contents)
    {
    	$cache_key = sprintf(self::STORAGE_SESSION_CACHE_KEY,$this->_zadminauth);
    	Zing_Cache_ZingGlobalCache::setCache($cache_key,$contents,self::CACHE_INSTANCE,self::STORAGE_SESSION_CACHE_EXPIRE);
    }

    /**
     * Clears contents from storage
     *
     * @throws Zend_Auth_Storage_Exception If clearing contents from storage is impossible
     * @return void
     */
    public function clear()
    {
    	$cache_key = sprintf(self::STORAGE_SESSION_CACHE_KEY,$this->_zadminauth);
    	Zing_Cache_ZingGlobalCache::deleteCache($cache_key,self::CACHE_INSTANCE);
    }
    public function create()
    {
    	$this->_zadminauth = Zing_Auth_Util::genSessionKey();
    	return $this->_zadminauth;
    }
    public function setZAdminAuthKey($zadmin_key)
    {
    	$this->_zadminauth = $zadmin_key;
    }
}
?>
