package com.andrealexf.workshopmongo.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.andrealexf.workshopmongo.domain.Post;
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
	
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody UserDTO objDTO){
		
		User obj = service.fromDTO(objDTO);//passa de DTO para user
		
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		//dá o endereço do novo objeto que foi inserido
		return ResponseEntity.created(uri).body(obj);//created gera o 501 -> quando cria algo
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<User> update(@PathVariable String id, @RequestBody UserDTO objDTO){
		
		User obj = service.fromDTO(objDTO);
		
		obj.setId(id);//o Id da URL que vai ser qula será editado
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/{id}/posts")
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id){
		
		User user = service.findById(id);//encontra o user pelo Id e depois pega o post de tal user
		return ResponseEntity.ok().body(user.getPosts());
	}
}
