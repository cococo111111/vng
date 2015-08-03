<?php
class Zing_Util_DataFormat {
    //put your code here
    public static function hex2str($hexstr) {
        $hexstr = str_replace(' ', '', $hexstr);
        $hexstr = str_replace('\x', '', $hexstr);
        $retstr = pack('H*', $hexstr);
        return $retstr;
    }
    public static function hexstr($string) {
        $hexstr = unpack('H*', $string);
        return array_shift($hexstr);
    }
    
    public static function formatNumber($num, $sep){
    	$dsp = "";
    	while(strlen($num) > 3){
    		$dsp = $sep . substr($num,-3) . $dsp;
    		$num = substr($num, 0, -3);
    	}	
    	$dsp = $num . $dsp;
    	return $dsp;
    }
}
?>