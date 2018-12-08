package Mobapp.Contacts.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usercontacts database table.
 * 
 */
@Entity
@Table(name="usercontacts")
@NamedQuery(name="Usercontact.findAll", query="SELECT u FROM Usercontact u")
public class Usercontact implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="uc_id")
	private int ucId;

	private String address;

	private String name;

	private String phone;

	//bi-directional many-to-one association to Usergroup
	@ManyToOne
	@JoinColumn(name="user_id")
	private Usergroup usergroup;

	public Usercontact() {
	}

	public int getUcId() {
		return this.ucId;
	}

	public void setUcId(int ucId) {
		this.ucId = ucId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Usergroup getUsergroup() {
		return this.usergroup;
	}

	public void setUsergroup(Usergroup usergroup) {
		this.usergroup = usergroup;
	}

}