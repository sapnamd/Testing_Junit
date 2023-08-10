package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.entity.AddResponse;
import com.example.entityLibrary.Library;
import com.example.repository.LibraryRepository;
import com.example.service.LibraryService;

@RestController
public class LibraryController {

	@Autowired
	LibraryRepository repository;
	
	@Autowired
	LibraryService libraryService;
	
	//private static final Logger logger= LoggerFactory.getLogger(LibraryController.class);
	//to check wheather book already exist or not. its a business logic hence code is in service class
	
	
	@PostMapping("/addBook")
	//if it is a requestbody use @RequestBody annotation
	public ResponseEntity addBookImplementation(@RequestBody Library library)
	{
		//to concatinate isbn+aisle=id;
		String id=libraryService.buildId(library.getIsbn(),library.getAisle());//dependenciesMock
		AddResponse ad =new AddResponse();
		
		if(!libraryService.checkBookAlreadyExist(id))//Mock
		{
		
			//logger.info("Book do not exist so creating one");
		library.setId(id);
		repository.save(library);//Mock
		//to create http header
		HttpHeaders headers = new HttpHeaders();
		headers.add("unique", id);
		ad.setMsg("Success Book is Added");
		ad.setId(id);
		//ad its an object to convert java to json and to avoid confussion classname is given AddResponse.
		return new ResponseEntity<AddResponse>(ad,headers,HttpStatus.CREATED);
		
	}
	else
	{
		//logger.info("Book exist so skipping creation");
		ad.setMsg("Book Already Exits");
		ad.setId(id);
	
		return new ResponseEntity<AddResponse>(ad,HttpStatus.ACCEPTED);
	}
	}
	
	@GetMapping("/getBooks/{id}")
	public Library getBookById(@PathVariable(value="id")String id)
	{
		try {
		Library lib = repository.findById(id).get();
		return lib;
	}
		catch(Exception e) {
			//ResponseStatusException is to rase exceptions.
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		}
	}
	
	@GetMapping("getBooks/author")
	//if it is a queryparameter use @RequestParam annotation
	public List<Library> getBookByAuthorName(@RequestParam(value="authorname")String authorname)
	{
		return repository.findAllByAuthor(authorname);
	}
	
	@PutMapping("/updateBook/{id}")
	//if it is a path use @Pathparameter annotation
	public ResponseEntity<Library> updateBook(@PathVariable(value="id")String id,@RequestBody Library library)
	{
		//Library existingBook = repository.findById(id).get();
		Library existingBook = libraryService.getBookById(id);
		
		existingBook.setAisle(library.getAisle());
		existingBook.setAuthor(library.getAuthor());
		existingBook.setBook_name(library.getBook_name());
		repository.save(existingBook);
		return new ResponseEntity<Library>(existingBook,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteBook")
	public ResponseEntity<String> deleteBookById(@RequestBody Library library)
	{
		//Library libdelete = repository.findById(library.getId()).get();
		Library libdelete = libraryService.getBookById(library.getId());
		
		repository.delete(libdelete);
		//logger.info("Book deleted successfully ");
		return new ResponseEntity<>("Book is deleted ", HttpStatus.CREATED);
	}
}

