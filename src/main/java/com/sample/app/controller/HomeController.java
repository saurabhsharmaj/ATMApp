package com.sample.app.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HomeController {

	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping("/atms")
    public @ResponseBody ResponseEntity<String> getAtms() { 
    	return restTemplate.exchange("/locator/atms/", HttpMethod.GET, null, String.class);
    }
	
	
	@RequestMapping("/atms/{city}")
    public @ResponseBody ResponseEntity<String> getAtmsByCity(@PathVariable String city) { 
    	String json= restTemplate.exchange("/locator/atms/", HttpMethod.GET, null, String.class).getBody();
    	json =json.substring(json.indexOf(",")+1,json.length());
    	JSONArray jsonArray = new JSONArray(json);
    	List<JSONObject> list =StreamSupport.stream(jsonArray.spliterator(), false)
    	  .map(val -> (JSONObject) val)
    	  .filter(val -> ((JSONObject)val.get("address")).get("city").toString().equalsIgnoreCase(city))
    	  .collect(Collectors.toList());
    	return new ResponseEntity<String>(list.toString(), HttpStatus.OK);
    }
	
}
