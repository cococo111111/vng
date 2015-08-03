package vng.bankinggateway.model.util;

import java.sql.Statement;

public class SQLUtil {
	
	public static void closeStatement(Statement stmt){
		if(stmt != null){
			try {
				stmt.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	public static boolean isDuplicate(Exception e){
		if("MySQLIntegrityConstraintViolationException".equals(e.getClass().getSimpleName())){
			if(e.getMessage().startsWith("Duplicate entry"))
				return true;
		}
		return false;
	}
}
