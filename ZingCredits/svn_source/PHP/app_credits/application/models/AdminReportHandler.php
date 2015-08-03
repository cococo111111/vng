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

 	
	public function getAdmin($adminID,$password){
		try{			
			$query='select * from report_admin where adminID=? and adminPassword=?';
			$result = $this->db->fetchAll($query,array($adminID,md5($password)));
		}  catch (Exception $e){            
		    throw $e;
		}
		return $result;
	    }
	public function getAdminApp($adminID){
		try{			
			$query='select * from report_admin_app where adminID=? order by adminAppID ';
			$result = $this->db->fetchAll($query,array($adminID));
		}  catch (Exception $e){            
		    throw $e;
		}
		return $result;
	    }
	public function changePass($adminID,$oldPass,$newPass){
		try{			
			$query='update report_admin set adminPassword=? where adminID=? and adminPassword=?';
			$statement = $this->db->prepare($query);
			$statement->execute(array(md5($newPass),$adminID,md5($oldPass)));
		}  catch (Exception $e){            
		    throw $e;
		}
	}
	
}
?>
