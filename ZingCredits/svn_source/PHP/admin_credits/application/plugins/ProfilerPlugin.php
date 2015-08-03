<?php
class ProfilerPlugin extends Zend_Controller_Plugin_Abstract
{
	private static $_time_start_render = 0;
	private static $_time_end_render = 0;
	public function dispatchLoopStartup(Zend_Controller_Request_Abstract $request)
	{ 		
				
		if(Globals::isDebug())
		{
			self::$_time_start_render = gettimeofday(true);
		}
		
	}

	public function dispatchLoopShutdown()
	{  			
		if(Globals::isDebug())
		{
			
			$print = "";					
			self::$_time_end_render = gettimeofday(true);			
			
			$print = $this->dumpPageRenderProfiler(self::$_time_start_render, self::$_time_end_render);	
			
			$print .= $this->dumpCacheProfiler();

			$print .= '<br><br>' . ProfilerLog::dumpLog();
			
			$print .= $this->dumpDbProfiler();
			
			$this->getResponse()->appendBody($print);
		}		
	}
	
	
	/////////////////////// private functions ///////////////////////////////
	
	private function dumpPageRenderProfiler($start_time, $end_time)
	{
		$print = "";
		
		$diff = ($end_time - $start_time);
		
		$print .= '<br><table border=1 cellspacing="5" cellpadding="5" style="border-collapse:collapse">';
		$print .= '<tr><td><b>page render time : ' . $diff . ' secs</b></td></tr>';
		$print .= "</table><br>";
		return $print;
	}
	
	private function dumpCacheProfiler()
	{
		$print = "";
		
		$print .= Zing_Cache_ZingGlobalCache::getAllProfilerData();
		
		$print .= '<br><br><table border=1 cellspacing="5" cellpadding="5" style="border-collapse:collapse">'
					. "<tr><th colspan=4 bgcolor='#dddddd'>Caching Profiler</th></tr>"
					. "<tr><th width=50>No.</th><th>Key</th><th>Result</th><th>Time elapsed in secs</th>";
					
			
			$print .= "<tr><td colspan='4' align='left'><b>total hits : " . CacheProfiler::getTotalHitsCache() . "</b></td></tr>";
			$print .= "<tr><td colspan='4' align='left'><b>total percent hits : " . CacheProfiler::getPercentHitsCache(). " %</b></td></tr>";
			$print .= "<tr><td colspan='4' align='left'><b>total misses : " . CacheProfiler::getTotalMissesCache() . "</tr>";
			$print .= "<tr><td colspan='4' align='left'><b>total percent misses : " . CacheProfiler::getPercentMissesCache() . " %</b></td></tr>";
			$print .= "<tr><td colspan='4' align='left'><b>total ellapsed time by cache : " . number_format(CacheProfiler::getTotalEllapsedTime(),9) . " secs</b></td></tr>";
			
			$cache_profile = CacheProfiler::getCacheProfiles();
			for($i=0;$i<count($cache_profile);$i++)
			{
				$profiler = $cache_profile[$i];
				$print .= "<tr><td>" . ($i+1) . "</td><td>" . $profiler->key . "</td><td>" 
						. ($profiler->result == "1" ? "<font color='green'>hit cache</font>" : "<font color='red'>miss cache</font>") 
						. "</td><td>" . number_format($profiler->ellapsedtime,9) . "</td></tr>";				 
			}
			$print .= "</table><br><br>";
		
		return $print;
	}
	
	private function dumpDbProfiler()
	{
		$print = "";
		$arr = Globals::$arrDB;
		if(is_array($arr) && count($arr) > 0)
		{
			$print .= '<br><br><table border=1 cellspacing="5" cellpadding="5" style="border-collapse:collapse">'
					. "<tr><th colspan=3 bgcolor='#dddddd'>Database Profiler</th></tr>"
					. "<tr><th width=50>No.</th><th>Query</th><th>Time elapsed in secs</th>";
			if(is_array($arr) && count($arr) > 0)
			{
				foreach($arr as $key => $value)
				{
					$count = 1;
					$profiler = $value->getProfiler();
					$print .= "<tr><td colspan='3' align='left'><b>debug profiler for db " . $key . "</b> --- ";
					$print .= "Total query : " . $profiler->getTotalNumQueries() . " ---- Total time elapsed : " 
							. number_format($profiler->getTotalElapsedSecs(),9) . " seconds"; 
					$print .= "</td></tr>";
					$profiler_arr = $profiler->getQueryProfiles();
					if(is_array($profiler_arr) && count($profiler_arr) > 0)
					{ 
						foreach ($profiler_arr as $query)
						{
							$print .= "<tr><td>" . $count++ ."</td><td align='left'>".$query->getQuery() ."</td><td align='left'>" . number_format($query->getElapsedSecs(),9) . "</td></tr>";				 	    
				    	}
					}			
				}
			}
			$print .= '</table><br><br><br>';			
		}
		return $print;
	}
	

}
?>
