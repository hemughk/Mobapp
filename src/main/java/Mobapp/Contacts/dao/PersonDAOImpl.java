package Mobapp.Contacts.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import Mobapp.Contacts.model.Person;
import Mobapp.Contacts.model.User;
import Mobapp.Contacts.model.User1;
import Mobapp.Contacts.model.Userinfo;
import util.Mobutil;

@Repository
public class PersonDAOImpl implements PersonDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonDAOImpl.class);

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
	
	@Override
	public void addUserinfo(Userinfo u) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(u);
		logger.info("Userinfo saved successfully");
	}

	
	@Override
	public User saveUserTimeData(User1 user1){
		 User client = null; 
		 Session session = this.sessionFactory.getCurrentSession();
		 Query query= session.createQuery("from User where indate=:indate and userinfo.did=:did");
	   	 query.setParameter("indate", user1.getIndate());
 	     query.setParameter("did", user1.getDevId());
 	     Userinfo userinfo=(Userinfo) session.get(Userinfo.class, new String(user1.getDevId()));
 	     if(null!=userinfo){
    	 List list=query.list();
    	 if(list.size()>0){
			  client  = (User) list.get(0);
			 }else{
				client  =new User(); 
			 } 
      	 client.setIntime(user1.getIntime());
    	 String str=user1.getIndate();
    	 client.setIndate(str);
	     String date[]=str.split("/");
    	 client.setMonth(date[0]);
    	 client.setOutdate("");
    	 client.setOuttime("");
    	 client.setUserinfo(userinfo);
    	 session.merge(client);
 	     }
    	  return client;
	}
	
	   public String saveUserOutTimeData(User1 user){
		  	 User client; 
		  	Session session = this.sessionFactory.getCurrentSession();
		  	String retStr=null;
		    	 Query query =session.createQuery("from User where indate=:indate and userinfo.did=:did");
		    	 query.setParameter("indate", user.getIndate());
		 	     query.setParameter("did", user.getDevId());
		    	 List list=query.list();
		    	 if(list.size()>0){
					  client  = (User) list.get(0);
					  final String dateStart = client.getIndate()+" "+client.getIntime();
		    		  final String dateStop = user.getIndate()+" "+user.getIntime();
		    		  final DateTimeFormatter format = DateTimeFormat.forPattern("MM/dd/yyyy hh:mm:ss a");
		    		  final DateTime dt1 = format.parseDateTime(dateStart);
		    		  final DateTime dt2 = format.parseDateTime(dateStop);
		    		  retStr=Mobutil.getTimediff(dt1, dt2);
		    		  client.setTimespent(retStr);
		    		  String[] minhours=retStr.split(":");
		    		  Integer timecomplete=Integer.valueOf(minhours[0].trim());
		    		 
		    		  if(timecomplete>5){
			    		  client.setOutdate(user.getIndate());
			    		  client.setOuttime(user.getIntime());
			    		  session.merge(client);
		    		  }else{
			    		  client.setOutdate("");
			    		  client.setOuttime("");
			    		  user.setMinhours(timecomplete);
		    		  }
		    		  user.setIndate(client.getIndate());
		    		  user.setIntime(client.getIntime());
					 }
		
		     	 return retStr;
		     }  
	   
		@Override
		public List getUserTimeData(String date, String did) {
			Session session = this.sessionFactory.getCurrentSession();
			List<User> users = new ArrayList();
			 try{
				String[] dat= date.split("/");
		   	    Query query =session.createQuery("from User where userinfo.did=:did");
		   	   query.setParameter("did", did);
		   	   users=query.list();
			 }catch (Exception ex) {
				logger.info("Error in getUserTimeData" + ex);
				System.out.println(ex);
			} 
			return users;
		}

		@Override
		public List getUserTimeData_dw(String date, String devId) {
			 List<User> users = new ArrayList();
			 Session session = this.sessionFactory.getCurrentSession();
			 Query query =session.createQuery("from User where indate=:date and userinfo.did=:did");
			    query.setParameter("date", date);
			    query.setParameter("did", devId);
			    users=query.list();
				return users; 
		}
		
		

}
