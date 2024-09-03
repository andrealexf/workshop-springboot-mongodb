package com.andrealexf.workshopmongo.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andrealexf.workshopmongo.domain.Post;
import com.andrealexf.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {//controlador rest

	@Autowired
	private PostService service;//service (dentro do rest)
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id){
		
		Post user = service.findById(id);
		return ResponseEntity.ok().body(user);
	}
	
}
