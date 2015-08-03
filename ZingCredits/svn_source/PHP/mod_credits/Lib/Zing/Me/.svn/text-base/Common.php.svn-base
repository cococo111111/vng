<?php
/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of Common
 *
 * @author root
 */
class Utilcommon {
   
	static function convert_datetime($h,$m,$s,$str) {
		$str=trim($str);
		list($date, $time) = explode(' ', $str);
		list($day,$month,$year) = explode('/', $date);
		list($hour, $minute, $second) = explode(':', $time);
		$timestamp = mktime($h,$m,$s, $month, $day, $year);
		return $timestamp;
	}

	static function getNameFromId($id,$arr) {
		for($i=0;$i<count($arr);$i++)
			if(intval($arr[$i]['id'])==intval($id))
				return $arr[$i]['name'];
	}
}
?>
