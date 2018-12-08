package Mobapp.Contacts.dao;

import java.util.List;

import Mobapp.Contacts.model.Person;
import Mobapp.Contacts.model.User;
import Mobapp.Contacts.model.User1;
import Mobapp.Contacts.model.Userinfo;

public interface PersonDAO {

	public void addPerson(Person p);
	public void updatePerson(Person p);
	public List<Person> listPersons();
	public Person getPersonById(int id);
	public void removePerson(int id);
	User saveUserTimeData(User1 user);
	String saveUserOutTimeData(User1 user);
	List getUserTimeData(String date, String did);
	List getUserTimeData_dw(String date, String devId);
	void addUserinfo(Userinfo u);
	
}
