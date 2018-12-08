package Mobapp.Contacts.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Mobapp.Contacts.dao.PersonDAO;
import Mobapp.Contacts.model.Person;
import Mobapp.Contacts.model.User;
import Mobapp.Contacts.model.User1;
import Mobapp.Contacts.model.Userinfo;



@Service
public class PersonServiceImpl implements PersonService {
	
	private PersonDAO personDAO;

	public void setPersonDAO(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	@Override
	@Transactional
	public void addPerson(Person p) {
		this.personDAO.addPerson(p);
	}

	@Override
	@Transactional
	public void updatePerson(Person p) {
		this.personDAO.updatePerson(p);
	}

	@Override
	@Transactional
	public List<Person> listPersons() {
		return this.personDAO.listPersons();
	}

	@Override
	@Transactional
	public Person getPersonById(int id) {
		return this.personDAO.getPersonById(id);
	}

	@Override
	@Transactional
	public void removePerson(int id) {
		this.personDAO.removePerson(id);
	}
	
	@Override
	@Transactional
	public User saveUserTimeData(User1 user) {
		return this.personDAO.saveUserTimeData(user);
	}
	
	@Override
	@Transactional
	public String saveUserOutTimeData(User1 user) {
		return this.personDAO.saveUserOutTimeData(user);
	}

	@Override
	@Transactional
	public List getUserTimeData(String date,String did) {
		return this.personDAO.getUserTimeData(date,did);
	}

	@Override
	@Transactional
	public List getUserTimeData_dw(String date, String devId) {
		return this.personDAO.getUserTimeData_dw(date,devId);
	}

	@Override
	@Transactional
	public void addUserinfo(Userinfo u) {
		this.personDAO.addUserinfo(u);
	}
}
