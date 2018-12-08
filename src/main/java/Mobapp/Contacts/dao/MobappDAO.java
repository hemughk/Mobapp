package Mobapp.Contacts.dao;

import java.util.List;

import Mobapp.Contacts.model.Person;
import Mobapp.Contacts.model.Usercontact;
import Mobapp.Contacts.model.Usergroup;

public interface MobappDAO {
	List<Usergroup> getUsergroup();
	List<Usercontact> getUserdetail(String uid);
	Person getPersonById(int id);
	void removePerson(int id);
	List<Person> listPersons();
	void updatePerson(Person p);
	void addPerson(Person p);
}
