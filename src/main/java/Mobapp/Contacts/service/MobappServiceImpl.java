package Mobapp.Contacts.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Mobapp.Contacts.dao.MobappDAO;
import Mobapp.Contacts.model.Person;
import Mobapp.Contacts.model.Usercontact;
import Mobapp.Contacts.model.Usergroup;


@Service
public class MobappServiceImpl implements MobappService {
	
	private MobappDAO mobappDAO;
	

	@Override
	@Transactional
	public void addPerson(Person p) {
		this.mobappDAO.addPerson(p);
	}

	@Override
	@Transactional
	public void updatePerson(Person p) {
		this.mobappDAO.updatePerson(p);
	}

	@Override
	@Transactional
	public List<Person> listPersons() {
		return this.mobappDAO.listPersons();
	}

	@Override
	@Transactional
	public Person getPersonById(int id) {
		return this.mobappDAO.getPersonById(id);
	}

	@Override
	@Transactional
	public void removePerson(int id) {
		this.mobappDAO.removePerson(id);
	}
	

	public void setMobappDAO(MobappDAO mobappDAO) {
		this.mobappDAO = mobappDAO;
	}

	@Override
	@Transactional
	public List<Usergroup> getUsergroup() {
		return this.mobappDAO.getUsergroup();
	}
	
	@Override
	@Transactional
	public List<Usercontact> getUserdetail(String uid) {
		return this.mobappDAO.getUserdetail(uid);
	}


}
