package com.andrealexf.workshopmongo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andrealexf.workshopmongo.domain.Post;
import com.andrealexf.workshopmongo.repository.PostRepository;
import com.andrealexf.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired 
	private PostRepository repo;
	
	public Post findById(String id) {
		
		Optional<Post> user = repo.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
}
