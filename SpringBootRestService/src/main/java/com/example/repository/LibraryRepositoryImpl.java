package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.entityLibrary.Library;

public class LibraryRepositoryImpl implements LibraryRepositoryCustom{

	@Autowired 
	LibraryRepository repository;
	
	@Override
	public List<Library> findAllByAuthor(String authorName) {
		// TODO Auto-generated method stub
		List<Library>bookswithAuthor = new ArrayList<Library>();
		List<Library>books = repository.findAll();
		for(Library item : books)
		{
			if(item.getAuthor().equalsIgnoreCase(authorName))
			{
				bookswithAuthor.add(item);
			}
		}
		return bookswithAuthor;
	}

}
