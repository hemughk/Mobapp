package Mobapp.Contacts.model;

import java.io.Serializable;

public class User1 implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String userid;
	private String indate;
	private String intime;
	
	private String outdate;
	private String outtime;
	private String timelimit;
	private String month;
	private String remTime;
	private String devId;
	private Integer minhours;
	private String status;

	
	

	public String getIndate() {
		return indate;
	}
	public void setIndate(String indate) {
		this.indate = indate;
	}
	public String getIntime() {
		return intime;
	}
	public void setIntime(String intime) {
		this.intime = intime;
	}
	public String getOutdate() {
		return outdate;
	}
	public void setOutdate(String outdate) {
		this.outdate = outdate;
	}
	public String getOuttime() {
		return outtime;
	}
	public void setOuttime(String outtime) {
		this.outtime = outtime;
	}
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getRemTime() {
		return remTime;
	}
	public void setRemTime(String remTime) {
		this.remTime = remTime;
	}
	public String getDevId() {
		return devId;
	}
	public void setDevId(String devId) {
		this.devId = devId;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getTimelimit() {
		return timelimit;
	}
	public void setTimelimit(String timelimit) {
		this.timelimit = timelimit;
	}
	public Integer getMinhours() {
		return minhours;
	}
	public void setMinhours(Integer minhours) {
		this.minhours = minhours;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
