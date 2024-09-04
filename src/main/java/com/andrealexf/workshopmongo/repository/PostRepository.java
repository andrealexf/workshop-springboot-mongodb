package com.andrealexf.workshopmongo.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.andrealexf.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")//0 é o primeiro parâmetro do método, 'i' ignora maiusculas e min
	List<Post> searchTitle(String text);

	List<Post> findByTitleContainingIgnoreCase(String text);//ignoreCase ignora maiusculas e minusculas
	
}
