package com.whitelabel.whitelabel.service;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.whitelabel.whitelabel.dto.WLjmtDTO;

@Service
public interface GetAPIService {

	
	void getAPI(String search, Model model) throws ParseException;
		
	
}
