package Mobapp.Contacts.service;

import java.util.List;

import Mobapp.Contacts.model.Person;
import Mobapp.Contacts.model.Usercontact;
import Mobapp.Contacts.model.Usergroup;

public interface MobappService {
	List<Usergroup> getUsergroup();
	List<Usercontact> getUserdetail(String uid);
	void addPerson(Person p);
	void updatePerson(Person p);
	List<Person> listPersons();
	Person getPersonById(int id);
	void removePerson(int id);
}
