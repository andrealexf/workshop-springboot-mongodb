package com.andrealexf.workshopmongo.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andrealexf.workshopmongo.domain.User;
import com.andrealexf.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {//controlador rest

	@Autowired
	private UserService service;//service (dentro do rest)
	
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
}
