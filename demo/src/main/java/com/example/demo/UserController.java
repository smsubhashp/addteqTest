package com.example.demo;


import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	UserService UserService;

	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE ,value="/userName")  
	public UserDTO getUserNameFromFile() throws IOError, FileNotFoundException{  
		return UserService.getUserNameFromFile();  
	}  


	@PostMapping(produces=MediaType.APPLICATION_JSON_VALUE,value="/save") 
	public ResponseEntity<Object> saveUserNameToDB() throws IOError, FileNotFoundException{ 
		UserDTO userDTO = UserService.saveUserNameToDB();
		 return new ResponseEntity<>("Saved :  "+userDTO.getUserName() , HttpStatus.OK);
	}
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE ,value="/userNameDB")  
	public List<UserDTO> getAllUserNameFromDB(){  
		return UserService.getAllUserNameFromDB();  
	}  


}
