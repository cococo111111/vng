<?php

/**
 * Reporter Database Connection
 *
 * @author nhutnh
 */
class Reporter {

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


	public function summary($appID, $startDate, $endDate){
		try{
			$query='select appID,SUM(totalTranx) as totalTranx,SUM(totalSuccessTranx) as totalSuccessTranx,SUM(totalFailureTranx) as totalFailureTranx,SUM(totalTimeoutTranx) as totalTimeoutTranx,SUM(totalNetworkFailTranx) as totalNetworkFailTranx,SUM(amount) as amount from apps_summary where appID=? and analyticDate>=? and analyticDate<=? order by analyticDate';
			$result = $this->db->fetchAll($query,array($appID, $startDate, $endDate));
		}  catch (Exception $e){            
		    throw $e;
		}
		if(is_null($result[0]['appID'])){
		return array();
		}
		return $result;
	    }
	public function summaryDaily($appID, $startDate, $endDate){
		try{
			$query='select * from apps_summary where appID=? and analyticDate>=? and analyticDate<=? order by analyticDate';
			//var_dump($startDate, $endDate);die();
			$result = $this->db->fetchAll($query,array($appID, $startDate, $endDate));
		}  catch (Exception $e){            
		    throw $e;
		}
		return $result;
	    }

 	public function getDetailTranx($refID,$startDate, $endDate){
		try{
			$months=$this->getTablePrefix($startDate, $endDate);
			$query='select * from transactions_'.$months[0].' where refID=?';
			$result = $this->db->fetchAll($query,array($refID));
		}  catch (Exception $e){            
		    throw $e;
		}
		return $result;
	    }
	public function getTransByApp($appID, $startDate, $endDate,$index,$count){
		try{
			$months=$this->getTablePrefix($startDate, $endDate);

			$groupTransactions=array();			
			foreach($months as $month){
				$query='select * from transactions_'.$month.' where appID=?  and txTime>=? and txTime<=? order by txTime limit ?,?';
				
				$a= $this->db->fetchAll($query,array($appID, $startDate, $endDate,$index,$count));
$groupTransactions =$a;
//var_dump($query,$appID, $startDate, $endDate,$index,$count);die();
			
}
			
		}  catch (Exception $e){            
		    throw $e;
		}
		return $groupTransactions;
	    }
public function getTransByUser($userID,$userName, $startDate, $endDate,$index,$count){
		try{
			$months=$this->getTablePrefix($startDate, $endDate);			
			$groupTransactions=array();	
			foreach($months as $month){
				$query='select * from transactions_'.$month.' where (userName=? and appID="zing") or (userID=? and appID !="zing") and txTime>=? and txTime<=? order by txTime limit ?,?';
				
				$a= $this->db->fetchAll($query,array($userName,$userID, $startDate, $endDate,$index,$count));
$groupTransactions[] =$a;

			
}
			
		}  catch (Exception $e){   
		    throw $e;
		}
		return $groupTransactions;
	    }




	
	private function getTablePrefix($fromDate,$toDate){
		$fromDate=strtotime($fromDate);
		$toDate=strtotime($toDate);
		$DEP_START_TIME = 1301590800;
		if ($fromDate > $toDate || $toDate < $DEP_START_TIME) {
            		return -1;//unvalid
		}
		if ($fromDate >= time()) {
           		$fromDate=time();
        	}
		if ($fromDate < $DEP_START_TIME) {
		    $fromDate = $DEP_START_TIME;
		}
		if ($toDate > time()) {
           		$toDate=time();
        	}

		$fromMonth = date('n',$fromDate);
		$fromYear = date('Y',$fromDate);

		$toMonth = date('n',$toDate);
		$toYear = date('Y',$toDate);
		if($fromYear!=$toYear){
			return -1;
		}
		$months = array();

		for(;$fromMonth<=$toMonth;$fromMonth++){
			$months[]=$fromYear.($fromMonth<10?"0".$fromMonth:$fromMonth);
		}
		return $months;
        
	}
}
?>
