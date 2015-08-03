<?php
	require_once APPLICATION_PATH.'/models/Reversal.php';
	class AutoReversal{
		public static function reversal(){
			$reversal = new Reversal();
	        $rows = $reversal->getReversal();
	        if($rows == null){
	            return;
	        }
	        foreach ($rows as $row){
	            $tx = new T_Transaction();
	            $tx->accountNo = $row['accountNo'];
	            $tx->userNo = $row['accountNo'];
	            $tx->tti = $row['tx_type'];
	            $tx->amount = $row['amount'];
	            $tx->invoiceNo = $row['invoiceNo'];
	            $tx->userID = $row['userID'];
	            $tx->txTime = $row['time'];
	            $tx->merchantID = "00000000";
	            $txApp = new Zing_App_Tranx();
	            $response = $txApp->request($tx);
	            if($response != null && $response->code == 0){
	            	$reversal->clearReversal($tx);
	            }
	        }
		}
	}
	AutoReversal::reversal();
?>