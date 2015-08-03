<?php

/**
 * Reporter Database Connection
 *
 * @author nhutnh
 */
class AdminReportHandler {
    private $db;
    function  __construct() {
	 $config = Zend_Registry::get('configuration');
	 $this->db = Zend_Db::factory('Mysqli', array(
	    'host'     => $config->report->database->host,
            'port'     => $config->report->database->port,
	    'username' => $config->report->database->username,
	    'password' => $config->report->database->password,
	    'dbname'   => $config->report->database->dbname
	));
	$this->db->query('SET NAMES utf8');
    }

 
 
	public function getCreditsAdmin($adminID,$password){
		try{			
			$query='select * from credits_admin where adminID=? and adminPassword=?';
			$result = $this->db->fetchAll($query,array($adminID,($password)));
		}  catch (Exception $e){            
		    throw $e;
		}
		return $result;
	    }
	public function changePass($adminID,$oldPass,$newPass){
		try{			
			$query='update credits_admin set adminPassword=? where adminID=? and adminPassword=?';
			$statement = $this->db->prepare($query);
			$statement->execute(array(($newPass),$adminID,($oldPass)));
		}  catch (Exception $e){            
		    throw $e;
		}
	}
	
}
?>
