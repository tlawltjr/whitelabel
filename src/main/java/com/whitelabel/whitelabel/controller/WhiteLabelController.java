package com.whitelabel.whitelabel.controller;

import java.net.http.HttpClient;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import com.google.gson.Gson;
import com.whitelabel.whitelabel.dto.WLjmtDTO;

@Controller
public class WhiteLabelController {

	@GetMapping("/whitelabel/listGuest")
	public void list() {

	}

	@GetMapping("/whitelabel/listAPI")
	public void getMapping() {

	}

	@SuppressWarnings("unchecked")
	@PostMapping("/whitelabel/listAPI")
	public void load_save(@RequestParam("search") String search, Model model) throws ParseException{
	        String requestResult = search;
	       
	        RestTemplate restTemplate = new RestTemplate();

	        HttpHeaders header = new HttpHeaders();
	        HttpEntity<String> entity = new HttpEntity<String>(header);

	        String url = "https://openapi.gg.go.kr/PlaceThatDoATasteyFoodSt";
	        UriComponents uri = UriComponentsBuilder
	                            .fromHttpUrl(url)
	                            .queryParam("key", "f57dd50ef40140fb95b379122a94d4f3")
	                            .queryParam("type", "json")
	                            .queryParam("pIndex", "1")
	                            .queryParam("pSize", "50")
	                            .queryParam("SIGUN_NM", requestResult)
	                            .build(false);

	        
	       // ResponseEntity<String> response = restTemplate.exchange(uri.toUriString(), HttpMethod.POST, entity, String.class);
	        
	        String jsonStr = restTemplate.getForObject(uri.toUriString(), String.class);
	        
	        JSONParser jparser = new JSONParser();
	        
	        JSONObject jobj = (JSONObject)jparser.parse(jsonStr);
	        
	        JSONArray jarr = (JSONArray) jobj.get("PlaceThatDoATasteyFoodSt");
	        System.out.println("jarr-------"+jarr);
	        
	        for(int i = 1; i<jarr.size();i++) {
	        	
	        	JSONObject json = (JSONObject) jarr.get(i);
	        	System.out.println("json==========="+json);
	        	
	        	JSONArray getRow = (JSONArray) json.get("row");
	        	System.out.println("get row =========="+getRow);
	        	
	        	for(int j = 0; j<getRow.size();j++) {
	        		JSONObject rowJson = (JSONObject) getRow.get(j);
	        		System.out.println("sigunNM-=========="+rowJson.get("SIGUN_NM"));
	        		
	        	WLjmtDTO dto = WLjmtDTO.builder()
	    	            .SIGUN_NM((String)rowJson.get("SIGUN_NM"))
	    	            .RESTRT_NM((String) rowJson.get("RESTRT_NM"))
	    	            .REPRSNT_FOOD_NM((String) rowJson.get("REPRSNT_FOOD_NM"))
	    	            .TASTFDPLC_TELNO((String) rowJson.get("TASTFDPLC_TELNO"))
	    	            .REFINE_LOTNO_ADDR((String) rowJson.get("REFINE_LOTNO_ADDR"))
	    	            .REFINE_ROADNM_ADDR((String) rowJson.get("REFINE_ROADNM_ADDR"))
	    	            .REFINE_ZIP_CD((String) rowJson.get("REFINE_ZIP_CD"))
	    	            .build();
	        	
	        	List<WLjmtDTO> result = new ArrayList<>();
	        	
	        	result.add(dto);
	        	
	        	model.addAttribute("getAPI",result);
	        	
	        	}
	        }
	    } 
}
