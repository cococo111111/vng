package com.vng.mvas.models;

import java.io.Serializable;
import java.util.Date;

public class Sending implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public Sending() {
		super();
	}
	private long ID;
	private String USER_ID;
    private String serviceId;
    private String MOBILE_OPERATOR;
    private String COMMAND_CODE;
    private int CONTENT_TYPE;
    private String INFO;
    private Date SUBMIT_DATE;
    private Date DONE_DATE;
    private int MESSAGE_TYPE;
    private int PROCESS_RESULT;
    private String REQUEST_ID;
    private String MESSAGE_ID;
    private int TOTAL_SEGMENTS;
    private int RETRIES_NUM;
    private String NOTES;
    private int CPId;
    
	    
		public String getUSER_ID() {
			return USER_ID;
		}
		public void setUSER_ID(String uSER_ID) {
			USER_ID = uSER_ID;
		}
		public String getServiceId() {
			return serviceId;
		}
		public void setServiceId(String serviceId) {
			this.serviceId = serviceId;
		}
		public String getMOBILE_OPERATOR() {
			return MOBILE_OPERATOR;
		}
		public void setMOBILE_OPERATOR(String mOBILE_OPERATOR) {
			MOBILE_OPERATOR = mOBILE_OPERATOR;
		}
		public String getCOMMAND_CODE() {
			return COMMAND_CODE;
		}
		public void setCOMMAND_CODE(String cOMMAND_CODE) {
			COMMAND_CODE = cOMMAND_CODE;
		}
		public int getCONTENT_TYPE() {
			return CONTENT_TYPE;
		}
		public void setCONTENT_TYPE(int cONTENT_TYPE) {
			CONTENT_TYPE = cONTENT_TYPE;
		}
		public String getINFO() {
			return INFO;
		}
		public void setINFO(String iNFO) {
			INFO = iNFO;
		}
		public Date getSUBMIT_DATE() {
			return SUBMIT_DATE;
		}
		public void setSUBMIT_DATE(Date sUBMIT_DATE) {
			SUBMIT_DATE = sUBMIT_DATE;
		}
		public Date getDONE_DATE() {
			return DONE_DATE;
		}
		public void setDONE_DATE(Date dONE_DATE) {
			DONE_DATE = dONE_DATE;
		}
		public int getMESSAGE_TYPE() {
			return MESSAGE_TYPE;
		}
		public void setMESSAGE_TYPE(int mESSAGE_TYPE) {
			MESSAGE_TYPE = mESSAGE_TYPE;
		}
		public String getREQUEST_ID() {
			return REQUEST_ID;
		}
		public void setREQUEST_ID(String rEQUEST_ID) {
			REQUEST_ID = rEQUEST_ID;
		}
		public String getMESSAGE_ID() {
			return MESSAGE_ID;
		}
		public void setMESSAGE_ID(String mESSAGE_ID) {
			MESSAGE_ID = mESSAGE_ID;
		}
		public int getTOTAL_SEGMENTS() {
			return TOTAL_SEGMENTS;
		}
		public void setTOTAL_SEGMENTS(int tOTAL_SEGMENTS) {
			TOTAL_SEGMENTS = tOTAL_SEGMENTS;
		}
		public int getRETRIES_NUM() {
			return RETRIES_NUM;
		}
		public void setRETRIES_NUM(int rETRIES_NUM) {
			RETRIES_NUM = rETRIES_NUM;
		}
		public String getNOTES() {
			return NOTES;
		}
		public void setNOTES(String nOTES) {
			NOTES = nOTES;
		}
		public int getCPId() {
			return CPId;
		}
		public void setCPId(int cPId) {
			CPId = cPId;
		}
		public long getID() {
			return ID;
		}
		public void setID(long iD) {
			ID = iD;
		}
		public int getPROCESS_RESULT() {
			return PROCESS_RESULT;
		}
		public void setPROCESS_RESULT(int pROCESS_RESULT) {
			PROCESS_RESULT = pROCESS_RESULT;
		}
	    
		@Override
		public String toString() {
			return "Sending [ID=" + ID + ", USER_ID=" + USER_ID
					+ ", serviceId=" + serviceId + ", MOBILE_OPERATOR="
					+ MOBILE_OPERATOR + ", COMMAND_CODE=" + COMMAND_CODE
					+ ", CONTENT_TYPE=" + CONTENT_TYPE + ", INFO=" + INFO
					+ ", SUBMIT_DATE=" + SUBMIT_DATE + ", DONE_DATE="
					+ DONE_DATE + ", MESSAGE_TYPE=" + MESSAGE_TYPE
					+ ", PROCESS_RESULT=" + PROCESS_RESULT + ", REQUEST_ID="
					+ REQUEST_ID + ", MESSAGE_ID=" + MESSAGE_ID
					+ ", TOTAL_SEGMENTS=" + TOTAL_SEGMENTS + ", RETRIES_NUM="
					+ RETRIES_NUM + ", NOTES=" + NOTES + ", CPId=" + CPId + "]";
		}

}
