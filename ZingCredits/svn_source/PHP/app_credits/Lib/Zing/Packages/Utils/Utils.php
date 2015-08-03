<?php
/*
 * Author: Khanv
 */

class Utils
{ 
	//Get an array of start/end second number of each day from the beginning day of system.. 
	public static function getFromToInDay()
	{
		$dateLength = 24*60*60;//in second
//		//start_date is a sunday..
//		$firstSunday = strtotime(START_DATE);//in second
//		$firstSaturday = strtotime("next saturday",$firstSunday);//in second
//		
//		$currentSecond = time();
//		$lastSunday = strtotime("last sunday",$currentSecond);//in second
//
//		$sunday = $firstSunday;
//		$saturday = $firstSaturday;

		//start_date is a sunday..
		$startDateInSecs = strtotime(START_DATE);//in second
		$tomorrowInSecs = strtotime("tomorrow");
		
		$startEndArr = array();
		$currDateInSecs = $startDateInSecs;
		while($currDateInSecs<$tomorrowInSecs)
		{			
			$tmp = new stdClass();
			$tmp->startInSecs = $currDateInSecs;
			$tmp->endInSecs = $currDateInSecs+($dateLength-1);
			$startEndArr[count($startEndArr)] = $tmp;
			$currDateInSecs += $dateLength;
		}
		return $startEndArr;
	}
	
	public static function getFromToInDayRange($userStartDate,$userEndDate)
	{
		$dateLength = 24*60*60;//in second
//		//start_date is a sunday..
//		$firstSunday = strtotime(START_DATE);//in second
//		$firstSaturday = strtotime("next saturday",$firstSunday);//in second
//		
//		$currentSecond = time();
//		$lastSunday = strtotime("last sunday",$currentSecond);//in second
//
//		$sunday = $firstSunday;
//		$saturday = $firstSaturday;

		//start_date is a sunday..
		$startDateInSecs = $userStartDate;//in second
		$tomorrowInSecs = $userEndDate;
		
		$startEndArr = array();
		$currDateInSecs = $startDateInSecs;
		while($currDateInSecs<$tomorrowInSecs)
		{			
			$tmp = new stdClass();
			$tmp->startInSecs = $currDateInSecs;
			$tmp->endInSecs = $currDateInSecs+($dateLength-1);
			$startEndArr[count($startEndArr)] = $tmp;
			$currDateInSecs += $dateLength;
		}
		return $startEndArr;
	}	
	
	public static function getAllSystem()
	{
//Comment : 1		
		$result = array();
		$item = new stdClass();
		$item->systemId = COMMENT_SYSTEM_ID;
		$item->systemName = "Comment";
		$result[0]=$item;
		
		return $result;
	}
	
	public static function getAllApp()
	{
//Feed : 107
//Photo-Album : 100
//Blog : 23
//Photo : 28
//Q&A : 71		
		$result = array();
		$item = new stdClass();
		$item->appId = FEED_APP_ID;
		$item->appName = "Feed";
		$result[0]=$item;
		
		$item = new stdClass();
		$item->appId = PHOTO_ALBUM_APP_ID;
		$item->appName = "Album";
		$result[1]=$item;
		
		$item = new stdClass();
		$item->appId = BLOG_APP_ID;
		$item->appName = "Blog";
		$result[2]=$item;
		
		$item = new stdClass();
		$item->appId = PHOTO_APP_ID;
		$item->appName = "Photo";
		$result[3]=$item;
		
		$item = new stdClass();
		$item->appId = QUEST_ASK_APP_ID;
		$item->appName = "Q&A";
		$result[4]=$item;
		
		return $result;
	}
}