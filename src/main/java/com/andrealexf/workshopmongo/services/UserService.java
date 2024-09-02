package com.andrealexf.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andrealexf.workshopmongo.domain.User;
import com.andrealexf.workshopmongo.repository.UserRepository;
import com.andrealexf.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired //injeção de dependência
	private UserRepository repo;//service acessa do repository
	
	
	public List<User> findAll(){
		
		return repo.findAll();
	}
	
	public User findById(String id) {
		
		Optional<User> user = repo.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
		
	}
}
