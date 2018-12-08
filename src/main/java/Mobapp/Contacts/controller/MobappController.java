package Mobapp.Contacts.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import Mobapp.Contacts.model.Person;
import Mobapp.Contacts.model.Usercontact;
import Mobapp.Contacts.model.Userdetail;
import Mobapp.Contacts.service.MobappService;
import flexjson.JSONSerializer;


@Controller
public class MobappController {
	
	private MobappService mobappService;
	
	@Autowired(required=true)
	@Qualifier(value="mobappService")
	public void setMobappService(MobappService ms){
		this.mobappService = ms;
	}
	
	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	public String listPersons(Model model) {
		model.addAttribute("person", new Person());
		model.addAttribute("listPersons", this.mobappService.listPersons());
		return "person";
	}
	
	//For add and update person both
	@RequestMapping(value= "/person/add", method = RequestMethod.POST)
	public String addPerson(@ModelAttribute("person") Person p){
		
		if(p.getId() == 0){
			//new person, add it
			this.mobappService.addPerson(p);
		}else{
			//existing person, call update
			this.mobappService.updatePerson(p);
		}
		
		return "redirect:/persons";
		
	}
	
	@RequestMapping("/remove/{id}")
    public String removePerson(@PathVariable("id") int id){
		
        this.mobappService.removePerson(id);
        return "redirect:/persons";
    }
 
    @RequestMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person", this.mobappService.getPersonById(id));
        model.addAttribute("listPersons", this.mobappService.listPersons());
        return "person";
    }
    
	

	/*This is for getting user groups*/
	@RequestMapping(value="/usergroupdata",method = RequestMethod.GET,produces="application/json")
	@SuppressWarnings( { "unchecked", "rawtypes" })	
	public @ResponseBody String getUsergroups(){
	    List usergrouplist = new ArrayList();
	    Map usergroupMap = new HashMap();
	    usergrouplist = mobappService.getUsergroup();
	    String jsonString=null;
	   // usergroupMap.put("usergroplist", usergrouplist);
	    jsonString = new JSONSerializer()
		.serialize(usergrouplist);
	    return jsonString;
	}
	
	/*This is for getting user contacts*/
	@RequestMapping(value="/userdetails",method = RequestMethod.GET,produces="application/json")
	@SuppressWarnings( { "unchecked", "rawtypes" })	
	public @ResponseBody String getUserdetails(@PathParam("uId") String uId){
	    List<Userdetail> usercontactist = new ArrayList();
	    List<Usercontact>  userdetaillist= mobappService.getUserdetail(uId);
	    for(Usercontact uc:userdetaillist){
	    	Userdetail ud=new Userdetail();
	    	ud.setAddress(uc.getAddress());
	    	ud.setName(uc.getName());
	    	ud.setPhone(uc.getPhone());
	    	usercontactist.add(ud);
	    }
	    String jsonString=null;
	    jsonString = new JSONSerializer()
		.serialize(usercontactist);
	    
	    return jsonString;
	 
	}
 }
