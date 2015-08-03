<?php
class service{
	var $aData = '';
	function service(){
		$this->aData["requestId"] =  rand();
		$this->aData["userIP"] = $_SERVER['REMOTE_ADDR'];
		$this->aData["clientType"] = $_SERVER['HTTP_USER_AGENT'];
		$this->aData["url"] =  'http://10.30.17.193/Passport45/PassportService.asmx?WSDL';
		$this->aData["wsAccount"] = 'passport';
		$this->aData["wsPassword"] = 'passport';
		$this->aData["productId"] = '38';		
		
	}	
	function getResultService($serviceName='',$body='',$sFunctionName='RequestService'){	
		$this->aData["serviceName"] = $serviceName;
		$this->aData["body"] = $body;	
		try{		
			$oClient = new SoapClient($this->aData["url"]);
			$oReturn = $oClient->__soapCall($sFunctionName,array($this->aData));		
			$result = $oReturn->RequestServiceResult->string;
			
		}catch (SoapFault $fault){}
		return $result;
	}	
	function getpassportid($aInfo=''){	
		$serviceName = "GET_PASSPORTID";	
		$body = array(
			array('ACC',strtolower(trim($aInfo['ACC'])))
		);		
		return $this->getResultService($serviceName,$body);
	}
}
$idx = new service();
$result = $idx->getpassportid(array('ACC'=>'namtv01'));
print_r('<pre>');
print_r($result);
?>