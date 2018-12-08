package Mobapp.Contacts.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import Mobapp.Contacts.model.Person;
import Mobapp.Contacts.model.Usercontact;
import Mobapp.Contacts.model.Usergroup;


@Repository
public class MobappDAOImpl implements MobappDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(MobappDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public void addPerson(Person p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(p);
		logger.info("Person saved successfully, Person Details="+p);
	}

	@Override
	public void updatePerson(Person p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(p);
		logger.info("Person updated successfully, Person Details="+p);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> listPersons() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Person> personsList = session.createQuery("from Person").list();
		for(Person p : personsList){
			logger.info("Person List::"+p);
		}
		return personsList;
	}

	@Override
	public Person getPersonById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Person p = (Person) session.load(Person.class, new Integer(id));
		logger.info("Person loaded successfully, Person details="+p);
		return p;
	}

	@Override
	public void removePerson(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Person p = (Person) session.load(Person.class, new Integer(id));
		if(null != p){
			session.delete(p);
		}
		logger.info("Person deleted successfully, person details="+p);
	}
	

	public List<Usercontact> listUsercontacts() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Usercontact> usercontactsList = session.createQuery("from Person").list();
		for(Usercontact uc : usercontactsList){
			logger.info("usercontactsList::"+uc);
		}
		return usercontactsList;
	}

	@Override
	public List<Usergroup> getUsergroup() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Usergroup> usergroupList = session.createQuery("from Usergroup").list();
		return usergroupList;
	}

	@Override
	public List<Usercontact> getUserdetail(String uid) {
		Session session = this.sessionFactory.getCurrentSession();
		 Query query= session.createQuery("from Usergroup  where userId=:userId");
		 query.setParameter("userId", Integer.parseInt(uid));
		 List<Usercontact> usercontacts=((Usergroup) query.list().get(0)).getUsercontacts();
		 //List<Usercontact> usercontactList
		return usercontacts;
	}


}
