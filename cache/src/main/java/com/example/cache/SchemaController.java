package com.example.cache;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchemaException;

@RestController
@RequestMapping(path = "/employees")
public class SchemaController {

	@Autowired
	JsonSchemaValidator jsonSchemaValidator;
	@Autowired
	CacheApplication cacheApplication;
	Exception exception;
	

	@PostMapping(path = "/test", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> addEmployee(@RequestBody Employee employee) {
		ObjectMapper mapper = new ObjectMapper();

		try {

			String jsonInString = mapper.writeValueAsString(employee);
			try {
				new CacheApplication();
				try {
					System.out.println("filepath"+cacheApplication.getProperties("filepath").getValue().toString());
					jsonSchemaValidator.validate(jsonInString,
							cacheApplication.getProperties("filepath").getValue().toString());
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (JsonSchemaException e) {
				return new ResponseEntity<Object>(new Exception("", "", e.getMessage()), HttpStatus.OK);

			}

			System.out.println(mapper);
		

	
			return new ResponseEntity<Object>(new AppResonse("Success", "00", "Schema Validate Successfully."), HttpStatus.OK);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(new Exception("500", "Internal Error Message", "Internal Error Message"), HttpStatus.OK);

	}
}