package Mobapp.Contacts.model;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.List;


/**
 * The persistent class for the userinfo database table.
 * 
 */
@Entity(name = "Userinfo")
@Table(name = "userinfo")
public class Userinfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	private String did;

	private String hourscomplete;

	private String name;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="userinfo")
	private List<User> users;

	public Userinfo() {
	}

	public String getDid() {
		return this.did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public String getHourscomplete() {
		return this.hourscomplete;
	}

	public void setHourscomplete(String hourscomplete) {
		this.hourscomplete = hourscomplete;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}