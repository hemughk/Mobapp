package Mobapp.Contacts.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.PathParam;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import Mobapp.Contacts.model.Person;
import Mobapp.Contacts.model.User;
import Mobapp.Contacts.model.User1;
import Mobapp.Contacts.model.Userinfo;
import Mobapp.Contacts.service.PersonService;
import flexjson.JSONSerializer;
import util.Mobutil;


@Controller
public class PersonController {
	
	private PersonService personService;
	
	@Autowired(required=true)
	@Qualifier(value="personService")
	public void setPersonService(PersonService ps){
		this.personService = ps;
	}
	
    
    @RequestMapping(value="/saveUinfo",method = RequestMethod.POST,
			consumes="application/json",produces="application/json")
	@SuppressWarnings( { "unchecked", "rawtypes" })	
	public @ResponseBody String saveUinfo(@RequestBody User1 user1) {
    	 String mapperval="";
		 Map map = new HashMap();
		 
    	Userinfo userinfo=new Userinfo();
    	userinfo.setDid(user1.getDevId());
    	userinfo.setHourscomplete(user1.getTimelimit());
    	personService.addUserinfo(userinfo);
    	personService.saveUserTimeData(user1);
    	map.put("hourscomplete", user1.getTimelimit());
    	 ObjectMapper mapper = new ObjectMapper();
	     try {
			mapperval=mapper.writeValueAsString(map);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	     return mapperval;
    }
    
	@RequestMapping(value="/inTime",method = RequestMethod.POST,
			consumes="application/json",produces="application/json")
	@SuppressWarnings( { "unchecked", "rawtypes" })	
	public @ResponseBody String postTime(@RequestBody User1 user1) {
		 String str="success";
		 String mapperval="";
		 Map map = new HashMap();
		 String notifi="";
		 String s=null;
	     String hourscomplete=null;

	    try {
	       User user=personService.saveUserTimeData(user1);
	       String retStr=0 +" : "+0+" : "+
 				 0 + " secs";
 		   user1.setRemTime(retStr);

	       if(user!=null){
	       if(user.getUserinfo()!=null && user.getUserinfo().getHourscomplete()!=null){
	        hourscomplete=user.getUserinfo().getHourscomplete();
	       }else{
	    	   hourscomplete="8:15";  
	       }
 		      notifi=calcoutTime(user1.getIntime(),hourscomplete);
	      }
 		 
	       map.put("userval", user1);
	       map.put("notifi", notifi);
	       map.put("uinfo", false);
	       map.put("hourscomplete", hourscomplete);
	       
	      ObjectMapper mapper = new ObjectMapper();
	      mapperval=mapper.writeValueAsString(map);

	    } catch (Exception e) {
	      System.out.println("Error in PostTime Service");
	      e.printStackTrace();
	    }
	   return mapperval;
	}
	
	@RequestMapping(value="/outTime",method = RequestMethod.POST,
			consumes="application/json",produces="application/json")
	@SuppressWarnings( { "unchecked", "rawtypes" })	
	public @ResponseBody String postOutTime(@RequestBody User1 user) {
		 String str="success";
		 String mapperval="";
		 Map map = new HashMap();
		 String s=null;
	    try {
	    	 String retStr=0 +" : "+0+" : "+0;
	         retStr=personService.saveUserOutTimeData(user);
	       //If outdate already saved.All the values should show empty
	         if(retStr==null){
	    	   user.setIndate("");
	    	   user.setIntime("");
	    	   user.setRemTime("");
	       }else{
	       user.setRemTime(retStr+" secs");
	       }
	       map.put("userval", user);
	      ObjectMapper mapper = new ObjectMapper();
	      mapperval=mapper.writeValueAsString(map);

	    } catch (Exception e) {
	      System.out.println("Error in PostTime Service");
	      e.printStackTrace();
	    }
	   return mapperval;
	}
	
	@RequestMapping(value="/all",method = RequestMethod.GET,produces="application/json")
	@SuppressWarnings( { "unchecked", "rawtypes" })	
	public @ResponseBody String getUsers(@PathParam("indate") String date,@PathParam("devId") String devId) {
	    List userlist = new ArrayList();
	    Map userMap = new HashMap();
	    String jsonString=null;
	    try {
	    	userlist = personService.getUserTimeData(date,devId);
	    	userMap.put("getUsersResult", userlist);
	    	 jsonString = new JSONSerializer()
					.serialize(userlist);
	    } catch (Exception e) {
	      System.out.println("Error in getUsersData");
	      e.printStackTrace();
	    }
	    
	   return jsonString;
	}
	
	@RequestMapping(value="/datewise",method = RequestMethod.GET,produces="application/json")
	@SuppressWarnings( { "unchecked", "rawtypes" })	
	public @ResponseBody String getUsers_dw(@PathParam("indate") String date,
			@PathParam("intime") String time,@PathParam("devId") String devId) {
	    List userlist = new ArrayList();
        String retStr="";
	    User user=new User();
	    User1 user1=new User1();
	    Map userMap = new HashMap();
	    String jsonString=null;
	    try {
	    	userlist = personService.getUserTimeData_dw(date,devId);
	    	if(userlist.size()>0){
	    		user=(User) userlist.get(0);

	    		  final String dateStart = user.getIndate()+" "+user.getIntime();
	    		  final String dateStop = date+" "+time;
	    		  final DateTimeFormatter format = DateTimeFormat.forPattern("MM/dd/yyyy hh:mm:ss a");
	    		  final DateTime dt1 = format.parseDateTime(dateStart);
	    		  final DateTime dt2 = format.parseDateTime(dateStop);
	    		  retStr=Mobutil.getTimediff(dt1, dt2);
	    		  if(null==user.getOutdate() || ("".equals(user.getOutdate())) ){
		    		  user1.setIndate(user.getIndate());
		    		  user1.setIntime(user.getIntime());
		    		  user1.setRemTime(retStr+" secs");
	    		  }else{
	    			  user1.setIndate("");
		    		  user1.setIntime("");
		    		  user1.setRemTime("");
		    		  user1.setOutdate(user.getOutdate());
	    		  }
	    	}
	    	userMap.put("getRemtime", user1);
	    	ObjectMapper mapper = new ObjectMapper();
	    	jsonString=mapper.writeValueAsString(userMap);
	    } catch (Exception e) {
	      System.out.println("Error in getUsersData");
	      e.printStackTrace();
	    }
	   return jsonString;
	}
	

	private String calcoutTime(String intime,String dbtime){
		String[] time= dbtime.split(":");
		Calendar now = Calendar.getInstance();
		 now.add(Calendar.HOUR, Integer.valueOf(time[0]));
		 now.add(Calendar.MINUTE,Integer.valueOf(time[1]));
		 String outtime=now.get(Calendar.HOUR_OF_DAY) + ":"
	        + now.get(Calendar.MINUTE) + ":" + now.get(Calendar.SECOND);
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");
		 System.out.println(dateFormat.format(now.getTime()));
		return dateFormat.format(now.getTime());
		
	}

}
