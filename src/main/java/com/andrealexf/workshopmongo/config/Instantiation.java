package com.andrealexf.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.andrealexf.workshopmongo.domain.Post;
import com.andrealexf.workshopmongo.domain.User;
import com.andrealexf.workshopmongo.dto.AuthorDTO;
import com.andrealexf.workshopmongo.dto.CommentDTO;
import com.andrealexf.workshopmongo.repository.PostRepository;
import com.andrealexf.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	

	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();//limpa a coleção 
		postRepository.deleteAll();

		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post p1 = new Post(null, sdf.parse("21/03/2018"), "Partiu Viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
		Post p2 = new Post(null, sdf.parse("21/03/2018"), "Bom dia", "Acordei agora", new AuthorDTO(maria));

		CommentDTO c1 = new CommentDTO("Boa viagem mano!", sdf.parse("21/03/2018"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveite", sdf.parse("21/03/2018"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Vai lá", sdf.parse("21/03/2018"), new AuthorDTO(alex));
		
		p1.setComments(Arrays.asList(c1, c3));//no video está: .getComments().addAll(Arrays.asList(c1, c3))
		p2.setComments(Arrays.asList(c2));
		
		postRepository.saveAll(Arrays.asList(p1,p2));
		
		maria.getPosts().addAll(Arrays.asList(p1,p2));
		userRepository.save(maria);
	}

	
}
