package com.andrealexf.workshopmongo.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andrealexf.workshopmongo.domain.User;
import com.andrealexf.workshopmongo.dto.UserDTO;
import com.andrealexf.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {//controlador rest

	@Autowired
	private UserService service;//service (dentro do rest)
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll(){
		
		List<User> list = service.findAll();
		List<UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)).toList();//expressão lambda para converter de Usr para User Dto
		//.collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id){
		
		User user = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(user));
	}
}
