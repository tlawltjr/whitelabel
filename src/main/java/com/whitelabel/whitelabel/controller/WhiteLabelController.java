package com.whitelabel.whitelabel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.whitelabel.whitelabel.dto.WLjmtDTO;
import com.whitelabel.whitelabel.service.GetAPIService;

@Controller
public class WhiteLabelController {

	@Qualifier("getAPI")
	@Autowired(required = true)
	private GetAPIService getAPIService;
	
	@GetMapping("/whitelabel/listGuest")
	public void list() {

	}

	@GetMapping("/whitelabel/listAPI")
	public void getMapping() {

	}
	
	@PostMapping("/whitelabel/listAPI")
	public void jmt(@RequestParam("search") String search, Model model) throws ParseException{
	       
		getAPIService.getAPI(search, model);
			
	    } 
}
