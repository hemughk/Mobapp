package Mobapp.Contacts.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity(name = "User")
@Table(name = "user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userid;

	private String indate;

	private String intime;

	private String month;

	private String outdate;

	private String outtime;

	private String timelimit;
	
	private String timespent;
	
	//private String did;

	//bi-directional many-to-one association to Userinfo
	@ManyToOne
	@JoinColumn(name="DID")
	private Userinfo userinfo;

	public User() {
	}

	public int getUserid() {
		return this.userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getIndate() {
		return this.indate;
	}

	public void setIndate(String indate) {
		this.indate = indate;
	}

	public String getIntime() {
		return this.intime;
	}

	public void setIntime(String intime) {
		this.intime = intime;
	}

	public String getMonth() {
		return this.month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getOutdate() {
		return this.outdate;
	}

	public void setOutdate(String outdate) {
		this.outdate = outdate;
	}

	public String getOuttime() {
		return this.outtime;
	}

	public void setOuttime(String outtime) {
		this.outtime = outtime;
	}

	public String getTimelimit() {
		return this.timelimit;
	}

	public void setTimelimit(String timelimit) {
		this.timelimit = timelimit;
	}

	public Userinfo getUserinfo() {
		return this.userinfo;
	}

	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}

	public String getTimespent() {
		return timespent;
	}

	public void setTimespent(String timespent) {
		this.timespent = timespent;
	}

/*	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}*/

}