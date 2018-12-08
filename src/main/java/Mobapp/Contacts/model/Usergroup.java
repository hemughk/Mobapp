package Mobapp.Contacts.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the usergroup database table.
 * 
 */
@Entity
@Table(name="usergroup")
@NamedQuery(name="Usergroup.findAll", query="SELECT u FROM Usergroup u")
public class Usergroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_id")
	private int userId;

	@Column(name="user_group")
	private String userGroup;

	//bi-directional many-to-one association to Usercontact
	@OneToMany(fetch = FetchType.EAGER,mappedBy="usergroup")
	private List<Usercontact> usercontacts;

	public Usergroup() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserGroup() {
		return this.userGroup;
	}

	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
	}

	public List<Usercontact> getUsercontacts() {
		return this.usercontacts;
	}

	public void setUsercontacts(List<Usercontact> usercontacts) {
		this.usercontacts = usercontacts;
	}

	public Usercontact addUsercontact(Usercontact usercontact) {
		getUsercontacts().add(usercontact);
		usercontact.setUsergroup(this);

		return usercontact;
	}

	public Usercontact removeUsercontact(Usercontact usercontact) {
		getUsercontacts().remove(usercontact);
		usercontact.setUsergroup(null);

		return usercontact;
	}

}