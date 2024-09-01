package com.andrealexf.workshopmongo.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.andrealexf.workshopmongo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{//tipo do obj (User) e o tipo do ID (String)

	
}
