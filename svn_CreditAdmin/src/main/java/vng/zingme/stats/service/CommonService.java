package vng.zingme.stats.service;

/**
 * @author sonhoang
 *
 */
public interface CommonService {

	
	/**
	 * @param date
	 * @return String yyyy-MM-dd hh:MM:ss
	 */
	public String getEndDateOfMonth(String date); 

	/**
	 * @param date
	 * @return String yyyy-MM-dd hh:MM:ss
	 */
	public  String getFirstDateOfNextMonth(String date); 
	
	/**
	 * @param date
	 * @return String yyyy-MM-dd
	 */
	public String getYesTerDay(String date);
	
	/**
	 * @param date
	 * @return String yyyy-MM-dd hh:MM
	 */
	public String getYesTerDay2(String date);
	
	/**
	 * @param date
	 * @return yyyy-MM-dd
	 */
	public String getToday(String date);
	
	/**
	 * @param date
	 * @return yyyy-MM-dd hh:MM
	 */
	public String getCurrentTime(String date);
	
	/**
	 * @param date
	 * @return yyyy-MM-dd hh:MM
	 */
	public String getFirstDateOfMonth(String date);
	
	/**
	 * @param date
	 * @return
	 */
	public long convertDate2Long(String format, String time);
	
	/**
	 * @param fileName
	 * @param data
	 */
	//public void exportCvsFile(String fileName, List<ReportDetail> data);
	
}
