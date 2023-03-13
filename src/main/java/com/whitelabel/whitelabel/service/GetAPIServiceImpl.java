package com.whitelabel.whitelabel.service;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.whitelabel.whitelabel.dto.WLjmtDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class GetAPIServiceImpl implements GetAPIService{

	@Override
	public Map<String, Object> getJSON(WLjmtDTO jmtDTO) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

/*

	private String getUrlForJSON(String callUrl) {
		
		StringBuilder sb = new StringBuilder();
		URLConnection urlConn = null;
		InputStreamReader in = null;
		
		//Json 결과값이 저장되는 변수
		String json = "";
		
		//SSL 적용된 사이트일 경우, 데이터 증명을 위해 사용
		HostnameVerifier allHostsValid = new HostnameVerifier() {
			@Override
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		
		try {
			// 웹 사이트 접속을 위한 URL 피싱
			URL url = new URL(callUrl);
			
			//접속
			urlConn = url.openConnection();
			
			//접속하면 , 응답을 60초( 60 * 10000ms)동안 기다림
			if (urlConn != null) {
				urlConn.setReadTimeout(60 * 1000);
			}
			if(urlConn != null && urlConn.getInputStream() != null) {
				in = new InputStreamReader(urlConn.getInputStream(), Charset.forName("UTF-8"));
				
				BufferedReader bufferedReader = new BufferedReader(in);
				
				//주어진 문자 입력 스트림 inputStream에 대해 기본 크기의 버퍼를 갖는 객체를 생성
				if (bufferedReader != null) {
					int cp;
					while ((cp = bufferedReader.read()) != -1) {
						sb.append((char) cp);
					}
					bufferedReader.close();
				}
			
			}
			in.close();
		} catch (Exception e) {
			throw new RuntimeException("Exception URL : " + callUrl, e);
		}
		json = sb.toString(); // json 결과 저장
		
		
		return json;
	}

	// 교통사고 정보 가져오기 pDTO 교통사고 정보 가져오기 위한 파라미터들, jSOn결과를 Map 형태로 변환 결과
	@Override
	public Map<String, Object> getJSON(WLjmtDTO jmtDTO) throws Exception {

		
		//json 읽은 값을 controller에 전달하기 위한 결과 변수
		Map<String, Object> rMap = new HashMap<String, Object>();
		
		//JSON결과 받아오기
		String json = getUrlForJSON(jmtDTO.getUrl());
		
		//String  변수의 문자열을 json 형태의 데이터 구조로 변경하기 위한 객체를 메모리에 올림
		JSONParser parser = new JSONParser();
		
		//String 변수의 문자열을 json 형태의 데이터 구조로 변경하기 위해 자바 최상위 Object 변환
		Object obj = parser.parse(json);
		
		//변환된 Object 객체를 json 데이터 구조로 변경
		JSONObject jsonObject = (JSONObject) obj;
	
		//요청한 파라미터 가져오기
		String reqYYYYMM = (String) jsonObject.get("reqYYYYMM");
		
		rMap.put("reqYYYYMM", reqYYYYMM); //데이터 저장
		
		//요청한 파라미터 가져오기
		String reqAcode = (String) jsonObject.get("reqAcode");
		
		rMap.put("reqAcode", reqAcode); //데이터 저장
		
		//요청한 파라미터 가져오기
		long recordCnt = (Long) jsonObject.get("recordCnt");
		
		rMap.put("recordCnt", recordCnt); //데이터 저장
		
		//json에[ 저장된 배열형태 데이터
		JSONArray resArr = (JSONArray) jsonObject.get("res");
		
		//JSON 배열에 저장된 데이터를 List<AccStatDTO> 구조로 변경하기 위해 메모리에 올림
		List<WLjmtDTO> rList = new ArrayList<WLjmtDTO>();
		
		//각 레코드마다 DTO로 저장
		WLjmtDTO rDTO = null;
		
		for (int i = 0; i < resArr.size(); i++) {
			JSONObject result = (JSONObject) resArr.get(i);
			
			rDTO = new WLjmtDTO(); //데이터 저장을 위해 메모리 올림
			
			//json 데이터를 dto에 저장
			rDTO.set((String)result.get("yyyymm"));
			rDTO.setA_code((String)result.get("a_code"));
			rDTO.setA_name((String)result.get("a_name"));
			rDTO.setStat_a((String)result.get("stat_a"));
			rDTO.setStat_b((String)result.get("stat_b"));
			
			//저장된 DTO를 list에 저장
			rList.add(rDTO);
		}
		//Controller에 저장할 데이터 저장
		rMap.put("res", rList);
		
		log.info(this.getClass().getName() + ".getAccStatForJSON end! ");
		
		return rMap;
	}
	*/
}
