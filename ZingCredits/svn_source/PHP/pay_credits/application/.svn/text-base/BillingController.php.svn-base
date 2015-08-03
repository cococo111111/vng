<?php
$GLOBALS['THRIFT_ROOT'] = ROOT_PATH.'/library/Apache/Thrift';
require_once $GLOBALS['THRIFT_ROOT'].'/packages/zingme_payment_type/zingme_payment_type_types.php';
require_once APPLICATION_PATH . '/pg_fe/TokenGenerator.php';
require_once APPLICATION_PATH . '/pg_fe/Billing.php';
require_once APPLICATION_PATH . '/pg_fe/Warmup.php';
require_once ROOT_PATH . '/util/DataFormat.php';
class BillingController extends Zend_Controller_Action {
    const DILIMITER = "-";
    public function init(){
        if(!Zend_Registry::isRegistered('logger')){
            require_once ROOT_PATH . '/util/Logger.php';
            $logger = Logger::getLogger();
            Zend_Registry::set('logger', $logger);
        }
    }

    private function getTransaction(){
        $tx = new vng_zingme_payment_thrift_T_Transaction();
        $tx->userID = $this->_getParam('userID');
        $tx->orderNo = $this->_getParam('billNo');
        $tx->agentID = $this->_getParam('appID');
        $tx->itemCount = 1;
        $tx->itemIDs = $this->_getParam('itemIDs');
        $tx->itemQuantities = $this->_getParam('quantities');
        $tx->amounts = $this->_getParam('prices');
        $tx->amount = $this->_getParam('amount');
        $tx->txTime = $this->_getParam('txTime');
        $tx->amount = $this->_getParam('amount');
        $tx->mac = $this->_getParam('mac');
        return $tx;
    }

    public function requestformAction() {
        $tokenObj = new TokenGenerator();
        try{
            $userID = $this->_getParam("userID");
            $token = $tokenObj->getToken($userID);
            $this->view->pToken = DataFormat::hexstr($token->pToken);
            $this->view->timestamp = $token->timestamp;

            $warmupObj = new Warmup();
            $warmupObj->warmup($userID);
        }catch(Exception $e){
            print_r($e);
        }
        $this->view->formaction = "/billing/bill";
        $this->render('billingex');
    }
    public function billAction() {
        $tx = $this->getTransaction();
        $tx->tti = 22;
        $tx->accountNo = $tx->userID;
        $token = new vng_zingme_payment_thrift_T_Token();
        $token->pToken = DataFormat::hex2str($this->_getParam("pToken"));
        $token->timestamp = $this->_getParam("timestamp");

        $billing = new Billing($tx, $token);
        try{
            $resCode = $billing->bill();
        }catch(Exception $e){
            print_r($e);
        }
        
        $url = $this->_getParam("resURL");
        $url .= "?userID=".$this->_getParam("userID");
        $url .= "&billNo=".$tx->orderNo;
        $url .= "&appID=".$this->_getParam("appID");
        $url .= "&txTime=".$this->_getParam("txTime");
        $url .= "&itemIDs=".$tx->itemIDs;
        $url .= "&quantities=".$tx->itemQuantities;
        $url .= "&prices=".$tx->amounts;
        $url .= "&amount=".$this->_getParam("amount");
        $url .= "&state=" . $resCode->code;
        $url .= "&balance=" . $resCode->mxuBalance;
        $url .= "&refNo=" . $resCode->refNo;
        $this->_redirect($url);
    }

    public function indexAction() {
        $this->render('billing');
    }
    public function stateAction() {
        $date = new Zend_Date('2010-03-15 00:00:00');

        $date1 = new Zend_Date();
        require_once APPLICATION_PATH.'/models/Billing.php';
        require_once ROOT_PATH.'/util/Period.php';
        $period = new Period($date, $date1);
        $userID = $this->_getParam('userID');
        $accountNo = $this->_getParam('userNo');
        $bill = new Billing();
        $rows = $bill->getStatement($userID, $accountNo, $period);
        $this->view->rows = $rows;
        
        $this->render('billing');
    }
    public function invoiceAction() {
        require_once APPLICATION_PATH.'/models/InvoiceItem.php';
        $invoiceNo = $this->_getParam('invoiceNo');
        $invItem = new InvoiceItem();
        $items = $invItem->getItems($invoiceNo);
        $this->view->items = $items;
        
        $this->render('invoice');
    }
    private function groupInvoice($invoices){
    	$arrInvoices = array();
    	$invoiceNo = null;
    	$i=-1;
    	foreach ($invoices as $invoice){
    		if($invoice['invoiceNo'] != $invoiceNo){
    			$i++;
    			$invoiceNo = $invoice['invoiceNo'];
	       		$arrInvoices[$i]['time'] = $invoice['time'];
	       		$arrInvoices[$i]['invoiceNo'] = $invoiceNo;
	       		$arrInvoices[$i]['tx_type'] = $invoice['tx_type'];
	       		$arrInvoices[$i]['name'] = $invoice['name'];
	       		$arrInvoices[$i]['amount'] = $invoice['amount'];
				$arrInvoices[$i]['items'] = array();	       		
	       		$item = array();
	       		$item['itemID'] =  $invoice['itemID'];
	       		$item['quantity'] =  $invoice['quantity'];
	       		$item['price'] =  $invoice['price'];
	       		$arrInvoices[$i]['items'][] = $item;
    		}else{
    			$item = array();
    			$item['itemID'] =  $invoice['itemID'];
	       		$item['quantity'] =  $invoice['quantity'];
	       		$item['price'] =  $invoice['price'];
	       		$arrInvoices[$i]['items'][] = $item;
    		}
	    }
	    return $arrInvoices;
    }
    public function invoicesAction(){
    	$command = $this->_getParam('command');
	    if($command != null && $command != ''){
	    	$userID = $this->_getParam('userID');
	    	$userNo = $this->_getParam('userNo');
	     	$fromStr = $this->_getParam('fromDate');
            $toStr = $this->_getParam('toDate');
            $part = 'dd/MM/YYYY';
            $fromDate = $fromStr == null? new Zend_Date(): new Zend_Date($fromStr, $part);
            $toDate = $toStr==null? new Zend_Date() : new Zend_Date($toStr, $part);
            if($fromDate->compare($toDate, $part, $fromDate->getLocale()) > 0){
            	$this->view->errMsg = "Invalid Period";
            	$this->render('invoices');
            	return;
            }
			require_once APPLICATION_PATH.'/models/Invoice.php';
	    	require_once ROOT_PATH.'/util/Period.php';
			$period = new Period($fromDate, $toDate);
	        $invObj = new Invoice();
	        $invoices = $invObj->getInvoices($userID, $userNo, $period);
	        $arrInvoices = $this->groupInvoice($invoices);
	        $this->view->invoices = $arrInvoices;
    	}
        $this->render('invoices');
    }
 	public function historyAction() {
        $command = $this->_getParam('command');
        if($command != null && $command != ''){
            $userID = $this->_getParam('userID');
            $userNo = $this->_getParam('userNo');
            $fromStr = $this->_getParam('fromDate');
            $toStr = $this->_getParam('toDate');
            $part = 'dd/MM/YYYY';
            $fromDate = $fromStr == null? new Zend_Date(): new Zend_Date($fromStr, $part);
            $toDate = $toStr==null? new Zend_Date() : new Zend_Date($toStr, $part);
            if($fromDate->compare($toDate, $part, $fromDate->getLocale()) > 0){
            	$this->view->errMsg = "Invalid Period";
            	$this->render('history');
            	return;
            }
            require_once APPLICATION_PATH.'/models/AccountHistory.php';
            //require_once ROOT_PATH.'/util/Period.php';
            $hisObj = new AccountHistory();
            $period = new Zing_Util_Period($fromDate, $toDate);
            $hisRows = $hisObj->getStatement($userID, $userNo, $period);
            $this->view->statements = $hisRows;
        }
        $this->render('history');
    }
}

