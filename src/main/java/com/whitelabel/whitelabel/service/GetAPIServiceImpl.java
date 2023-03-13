package com.whitelabel.whitelabel.service;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.whitelabel.whitelabel.dto.WLjmtDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Qualifier("getAPI")
@Service
public class GetAPIServiceImpl implements GetAPIService{
	
	@Override
	public void getAPI(String search, Model model) throws ParseException {
		
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
                            .query("SIGUN_NM=" + requestResult)
                            .build(false);

        System.out.println("uri------"+uri);
        
       ResponseEntity<String> response = restTemplate.exchange(uri.toUriString(), HttpMethod.POST, entity, String.class);
        
        String jsonStr = restTemplate.getForObject(uri.toUriString(), String.class);
        
        System.out.println("jsonStr-----------"+jsonStr);
        
        JSONParser jparser = new JSONParser();
        
        JSONObject jobj = (JSONObject)jparser.parse(jsonStr);
        
        System.out.println("jobj--------------"+jobj);
        
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
