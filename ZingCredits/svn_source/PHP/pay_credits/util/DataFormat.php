<?php
/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of DataFormatType
 *
 * @author root
 */
class DataFormat {
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
}
?>
