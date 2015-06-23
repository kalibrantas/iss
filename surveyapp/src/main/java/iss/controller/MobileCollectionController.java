package iss.controller;


import iss.message.Respondent;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MobileCollectionController extends AbstractISSController{
	
	@RequestMapping(value="/saveanswer", method=RequestMethod.POST, consumes="application/json", produces="text/plain")
	public @ResponseBody String saveanswer(@RequestBody Respondent r){
		System.out.println(r.getQuestionnaire_answer().get(0).getName());
		rd.saveRespondent(r);
		return "save";
	}

}
