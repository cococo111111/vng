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

 	public function getAllAdmin(){
		try{			
			$query='select * from report_admin';
			$result = $this->db->fetchAll($query);
		}  catch (Exception $e){            
		    throw $e;
		}
		return $result;
	    }
 	public function getAllCreditsAdmin(){
	try{			
		$query='select * from credits_admin';
		$result = $this->db->fetchAll($query);
	}  catch (Exception $e){            
	    throw $e;
	}
	return $result;
    }
	public function getAllAdminApp($adminID){
		try{			
			$query='select * from report_admin_app where adminID=?';
			$result = $this->db->fetchAll($query,array($adminID));
		}  catch (Exception $e){            
		    throw $e;
		}
		return $result;
	    }
	public function getAdmin($adminID){
		try{			
			$query='select * from report_admin where adminID=?';
			$result = $this->db->fetchAll($query,array($adminID));
		}  catch (Exception $e){            
		    throw $e;
		}
		return $result[0];
	    }
	public function setposition($appID,$position){
		try{			
			$query='insert into apps_sort values(?,?) ON DUPLICATE KEY UPDATE position=?';
			$statement = $this->db->prepare($query);
			$statement->execute(array($appID,$position,$position));
		}  catch (Exception $e){  
		    throw $e;
		}
	    }
	public function getposition($appID){
		try{			
			$query='select position from apps_sort where appID=?';
			$result = $this->db->fetchAll($query,array($appID));
			return $result[0];
		}  catch (Exception $e){         
		    throw $e;
		}
	    }
	public function getAllPosition(){
		try{			
			$query='select appID,position from apps_sort';
			$result = $this->db->fetchAll($query);
			return $result;
		}  catch (Exception $e){         
		    throw $e;
		}
	    }
	public function getCreditsAdmin($adminID){
		try{			
			$query='select * from credits_admin where adminID=?';
			$result = $this->db->fetchAll($query,array($adminID));
		}  catch (Exception $e){            
		    throw $e;
		}
		return $result[0];
	    }
	public function insertAdmin($userData){
		try{			
			$query='insert into report_admin(adminID,adminName,adminPassword,adminflg)values(?,?,?,?)';
			$statement = $this->db->prepare($query);
			$statement->execute(array($userData['txtUserName'],$userData["txtName"],md5($userData["txtPass"]),isset($userData['group'])?$userData['group']:"0"));
			if(!isset($userData['appid'])){
			return;
			}
			$q='insert into report_admin_app(adminID,adminAppID) values';
			$i=1;
			$c=count($userData['appid']);
			foreach($userData['appid'] as $appID){			
				$q.='("'.$userData['txtUserName'].'","'.$appID.'")';
				if($i<$c){
				$q.=',';
				}
				$i++;
			}
			$statement = $this->db->prepare($q);
			$statement->execute();
		}  catch (Exception $e){            
		    throw $e;
		}
	}
	public function insertCreditsAdmin($userData){
		try{			
			$query='insert into credits_admin(adminID,adminName,adminPassword)values(?,?,?)';
			$statement = $this->db->prepare($query);
			$statement->execute(array($userData['txtUserName'],$userData["txtName"],md5($userData["txtPass"])));
		
		}  catch (Exception $e){            
		    throw $e;
		}
	}

	public function updateAdmin($userData){
		try{			
			$query='update report_admin set adminName=?,adminflg=? where adminID=?';
			$statement = $this->db->prepare($query);
			$statement->execute(array($userData['txtName'],isset($userData['group'])?$userData['group']:"0",$userData["txtUserName"]));
			
			$query='delete from report_admin_app where adminID=?';
			$statement = $this->db->prepare($query);
			$statement->execute(array($userData["txtUserName"]));
			if(!isset($userData['appid'])){
			return;
			}
			$q='insert into report_admin_app(adminID,adminAppID) values';
			$i=1;
			$c=count($userData['appid']);
			foreach($userData['appid'] as $appID){			
				$q.='("'.$userData['txtUserName'].'","'.$appID.'")';
				if($i<$c){
				$q.=',';
				}
				$i++;
			}
			$statement = $this->db->prepare($q);
			$statement->execute();
		}  catch (Exception $e){            
		    throw $e;
		}
	}
	public function updateCreditsAdmin($userData){
		try{			
			$query='update credits_admin set adminName=? where adminID=?';
			$statement = $this->db->prepare($query);
			$statement->execute(array($userData['txtName'],$userData["txtUserName"]));
		}  catch (Exception $e){            
		    throw $e;
		}
	}
	public function resetpass($resetpass,$adminID){
		try{			
			$query='update report_admin set adminPassword=? where adminID=?';
			$statement = $this->db->prepare($query);
			$statement->execute(array(md5($resetpass),$adminID));
		}  catch (Exception $e){            
		    throw $e;
		}
	}
	public function resetCreditspass($resetpass,$adminID){
		try{			
			$query='update credits_admin set adminPassword=? where adminID=?';
			$statement = $this->db->prepare($query);
			$statement->execute(array(md5($resetpass),$adminID));
		}  catch (Exception $e){            
		    throw $e;
		}
	}
	public function deleteAdmin($adminID){
		try{			
			$query='delete from report_admin where adminID=?';
			$statement = $this->db->prepare($query);
			$statement->execute(array($adminID));
			$query='delete from report_admin_app where adminID=?';
			$statement = $this->db->prepare($query);
			$statement->execute(array($adminID));
		}  catch (Exception $e){            
		    throw $e;
		}
	}
	public function deleteCreditsAdmin($adminID){
		try{			
			$query='delete from credits_admin where adminID=?';
			$statement = $this->db->prepare($query);
			$statement->execute(array($adminID));			
		}  catch (Exception $e){            
		    throw $e;
		}
	}
	
}
?>
