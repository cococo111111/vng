<?php
/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of Period
 *
 * @author root
 */
class Zing_Util_Period {
    //put your code here
    const DEFAULT_PATTERN = 'yyyy-MM-dd HH:mm:ss';
    private $start;
    private $end;
    function  __construct($start = null, $end = null) {
        if($start == null && $end == null){
            $this->setStartDate(new Zend_Date());
            $this->setEndDate(new Zend_Date());
        }else if($start == null){
            $this->setStartDate($end);
            $this->setEndDate($end);
        }else if($end == null){
            $this->setStartDate($start);
            $this->setEndDate($start);
        }else{
            $this->setStartDate($start);
            $this->setEndDate($end);
        }
    }
    public function getStartDate(){
        return $this->start;
    }
    public function getEndDate(){
        return $this->end;
    }

    public function setStartDate($start){
        $start->setHour(0);
        $start->setMinute(0);
        $start->setSecond(0);
        $start->setMilliSecond(0);
        $this->start = $start;
    }

    public function setEndDate($end){
        $end->setHour(23);
        $end->setMinute(59);
        $end->setSecond(59);
        $end->setMilliSecond(999);
        $this->end = $end;
    }
}
?>
