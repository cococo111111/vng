<?php


class CreditImporter
{
	private $backupDir='/data/zingcredits/exports/';
	private $backupDay='';//'2011-05-07';//place empty for getting from previous day.format:YYYY-MM-DD
	private $token_separate="\t";
	private $db;
	
	public function initDB($host,$username,$password,$dbName,$port){
		try{
                $this->db =new mysqli($host,$username,$password,$dbName,$port);
		$this->db->query('SET NAMES utf8');
		}
		catch(Exception $e){
		var_dump($e);die();
		}
	}
	private function appendLog($tr){
		$logFile='log/credits_importer_'.$this->getTodayTime().'.log';
		//make file if not exist
		if(!file_exists($logFile)){			
			$ourFileHandle = fopen($logFile, 'w') or die("can't open file");
			fclose($ourFileHandle);
		}
		file_put_contents($logFile, $this->getTodayTimeinMinus()."\t".$tr."\n", FILE_APPEND | LOCK_EX);
	}
	
	public function exec(){
		self::appendLog('begin exec');
		$this->makeBackupDay();
		self::appendLog('time:'.$this->backupDay);
		$backupFiles=$this->getBackupFiles();
		$this->recoverSummaryFiles($backupFiles[0]);
		//$this->recoverDetailFiles($backupFiles[1]);
		self::appendLog('end exec');
	}
	private function makeBackupDay(){
		if(count($_SERVER['argv'])==3){
			$this->backupDay = $_SERVER['argv'][2];
		}
		if($this->backupDay==''){
			$this->backupDay = date('Y-m-d', strtotime("now -1 day"));//ex:2001-10-20
		}
		
	}
	private function getTodayTime(){
		return date('Y-m-d', strtotime("now"));
	}
	private function getTodayTimeinMinus(){
		return date('Y-m-d H:i:s', strtotime("now"));
	}
	/**
	*recover summary files
	*/
	private function recoverSummaryFiles($summaryFiles){
		self::appendLog('begin recover summary files');
		foreach($summaryFiles as $summaryFile){
			$this->recoverSummaryFile($summaryFile);
		}
		self::appendLog('end recover summary files');
	}
	/**
	*recover detail files
	*/
	private function recoverDetailFiles($detailFiles){
		self::appendLog('begin recover transaction files');
		foreach($detailFiles as $detailFile){
			$this->recoverDetailFile($detailFile);
		}
		self::appendLog('end recover transaction files');
	}



	/**
	*recover one summary file
	*/
	private function recoverSummaryFile($summaryFile){
		self::appendLog('begin recover file '.$summaryFile);
		$file = fopen($this->backupDir.$summaryFile, 'r') or exit("Unable to open file!");
		$totalLine=0;
		$totalSuccess=0;
		while(!feof($file)){
			$line = fgets($file);
			if(!$line){
			continue;
			}
			if($this->saveSummaryLineToDB($line)){
				$totalSuccess++;
			}
			$totalLine++;
		}
		fclose($file);
		self::appendLog('total: '.$totalLine."\tsuccess: ".$totalSuccess."\tfail:".($totalLine-$totalSuccess));
		self::appendLog('end recover file '.$summaryFile);
	}
	/**
	*recover one detail file
	*/
	private function recoverDetailFile($detailFile){
		$time=$this->getTimeFromFileName($detailFile);
		self::appendLog('begin recover file '.$detailFile);
		$file = fopen($this->backupDir.$detailFile, 'r') or exit("Unable to open file!");
		$totalLine=0;
		$totalSuccess=0;
		while(!feof($file)){
			$line = fgets($file);
			if(!$line){
			continue;
			}
			if($this->saveDetailLineToDB($line,$time)){
				$totalSuccess++;
			}
			$totalLine++;
		}
		fclose($file);
		self::appendLog('total: '.$totalLine."\tsuccess: ".$totalSuccess."\tfail:".($totalLine-$totalSuccess));
		self::appendLog('end recover file '.$detailFile);
	}
	private function removeWord($array,$word,$wordreplace){
		for($i=0;$i<count($array);$i++){
			$array[$i]=str_replace($word,$wordreplace,$array[$i]);
		}
		return $array;
	}
	private function saveSummaryLineToDB($line){
		$data = explode($this->token_separate,$line);		
		if(count($data)!=9){
			return false;
		}
		$data=self::removeWord($data,"'","\"");
		try{
		$query="insert into apps_summary(appID,analyticDate,totalTranx,totalSuccessTranx,totalFailureTranx,totalTimeoutTranx,totalNetworkFailTranx,amount,totalUsers)values('".$data[0]."','".$data[1]."','".$data[2]."','".$data[3]."','".$data[4]."','".$data[5]."','".$data[6]."','".$data[7]."','".$data[8]."')";
		$this->db->query($query);
		}catch(Exception $e){
			self::appendLog($e);
			return false;
		}
		return true;

	}
	private function saveDetailLineToDB($line,$time){
		$data = explode($this->token_separate,$line);
		$data=self::removeWord($data,"'","\"");
		//truong hop la zing pay
		if(count($data)==4){
			try{		
				$query="insert into transactions_".$this->getTablePrefix()
."(appID,userName,refID,amount,txTime)values('".$data[0]."','".$data[1]."','".$data[2]."','".$data[3]."','".$time."')";
				$this->db->query($query);
			}catch(Exception $e){
				return false;
			}
			return true;
		}
		//truong hop la cac app thanh toan khong co field message
		if(count($data)==11){
			$data[11]='';
		}
		//truong hop la cac app thanh toan
		if(count($data)==12){
			try{		
				$query="insert into transactions_".$this->getTablePrefix()
."(appID,userID,refID,amount,txID,txTime,itemIDs,itemNames,itemPrices,itemQuantities,resultCode,message)values('".$data[0]."','".$data[1]."','".$data[2]."','".$data[3]."','".$data[4]."','".$data[5]."','".$data[6]."','".$data[7]."','".$data[8]."','".$data[9]."','".$data[10]."','".$data[11]."')";
				$this->db->query($query);
			}catch(Exception $e){
				return false;
			}
		}
		
		return true;
	}
	/**
	* divide backup file name to summary and detail
	* return array(length must be 2):
	*	 array[0]:summary file names
	*	 array[1]:detail file names
	*/	
	private function getBackupFiles(){		
		$summaryFiles=array();
		$detailFiles=array();
		try{
		if(!is_dir($this->backupDir)){
			return array($summaryFiles,$detailFiles);
		}
		if($backupDir = opendir($this->backupDir)) {
			while (($file = readdir($backupDir)) !== false) {
				if(is_dir($this->backupDir.''.$file)){
					if($dir = opendir($this->backupDir.''.$file)) {
						while (($f = readdir($dir)) !== false){
							if(strpos($f,$this->backupDay.'_summary')){
							$summaryFiles[]=$file.'/'.$f;
							continue;
							}
							if(strpos($f,$this->backupDay)){
								$detailFiles[]=$file.'/'.$f;
							}				
						}
						closedir($dir);
					}		
				
				
				}
			}
			closedir($backupDir);
		}
		}
		catch(Exception $e){
			var_dump($e);
		}
		//var_dump($summaryFiles,$detailFiles);die();
		return array($summaryFiles,$detailFiles);
	}
	private function getTablePrefix(){
		$fromDate=strtotime($this->backupDay);
		$fromMonth = date('m',$fromDate);
		$fromYear = date('Y',$fromDate);
		return $fromYear."".$fromMonth;

	}
	private function getTimeFromFileName($fileName){
		$data = explode("_",$fileName);
		return $data[1];
	}
	

}
if(count($_SERVER['argv'])>1){
	switch($_SERVER['argv'][1]){
		case 'development':
			$HOST="10.199.18.64";
			$PORT="3306";
			$USERNAME="dev";
			$PASSWORD="YSBBAAABAQDCQS7yBx";
			$DBNAME="test";
			break;
		case 'staging':
			$HOST="10.30.22.140";
			$PORT="3306";
			$USERNAME="staging";
			$PASSWORD="urubHnJAL8UAES";
			$DBNAME="test";
			break;

		case 'production':
			$HOST="10.30.22.155";
			$PORT="3307";
			$USERNAME="report";
			$PASSWORD="i9FTdUvK2EZDj";
			$DBNAME="zingcredits_app";
			break;

		default:
			echo "wrong enviroment\n";
			die();
	}
}
else{
	echo "wrong parameter\n";
	die();
}
//php CreditImporter.php development 2011-05-10
// /zserver/php/bin/php -c /zserver/php/etc/php.ini CreditImporter.php development 2011-05-10
// /zserver/php/bin/php -c /zserver/php/etc/php.ini CreditImporter.php staging 2011-05-10

$creditImpoter=new CreditImporter();
$creditImpoter->initDB($HOST,$USERNAME,$PASSWORD,$DBNAME,$PORT);
$creditImpoter->exec();
?>
