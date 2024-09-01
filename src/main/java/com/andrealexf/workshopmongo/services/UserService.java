package com.andrealexf.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andrealexf.workshopmongo.domain.User;
import com.andrealexf.workshopmongo.repository.UserRepository;

@Service
public class UserService {

	@Autowired //injeção de dependência
	private UserRepository repo;//service acessa do repository
	
	
	public List<User> findAll(){
		
		return repo.findAll();
	}
}
