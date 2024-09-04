package com.andrealexf.workshopmongo.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.andrealexf.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")//se o texto perntence ao título
	//0 é o primeiro parâmetro do método, 'i' ignora maiusculas e min
	List<Post> searchTitle(String text);

	List<Post> findByTitleContainingIgnoreCase(String text);//ignoreCase ignora maiusculas e minusculas
	
	
	@Query("{ $and: [ { date: {$gte: ?1} }, { date: { $lte: ?2} } , { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")//comments.text = dentro texto de cada comentário
	// $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } 
	//or -> texto no título ou no corpo
	//and -> data entre minima e máxima
	//gte -> greater or equal 
	//lte -> less or equal
	List<Post> fullSearch(String text, Date minDate, Date maxDate); //txt = 0, minDat = 1, maxDat = 2
}
