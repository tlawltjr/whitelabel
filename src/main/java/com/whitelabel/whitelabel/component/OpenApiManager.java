package com.whitelabel.whitelabel.component;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class OpenApiManager {

	private final String base_url = "https://openapi.gg.go.kr";
    private final String apiUri = "/PlaceThatDoATasteyFoodSt";
    private final String serviceKey = "key=f57dd50ef40140fb95b379122a94d4f3";
    private final String defaultQueryParam = "type=json";
    private final String pIndex = "pIndex=1";
    private final String pSize = "pSize=50";
    private final String sigunName = "SIGUN_NM";


    private String makeUrl() throws UnsupportedEncodingException {
        return base_url +
                apiUri +
                serviceKey +
                defaultQueryParam +
                pIndex +
                pSize +
                sigunName;
    }

    public ResponseEntity<?> fetch() throws UnsupportedEncodingException {
        System.out.println(makeUrl());
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<?> entity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<Map> resultMap = restTemplate.exchange(makeUrl(), HttpMethod.GET, entity, Map.class);
        System.out.println(resultMap.getBody());
        return resultMap;

    }
}
