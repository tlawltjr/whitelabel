package com.whitelabel.whitelabel.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.whitelabel.whitelabel.dto.WLjmtDTO;

@Service
public interface GetAPIService {
	
	Map<String, Object> getJSON(WLjmtDTO jmtDTO) throws Exception;
}
