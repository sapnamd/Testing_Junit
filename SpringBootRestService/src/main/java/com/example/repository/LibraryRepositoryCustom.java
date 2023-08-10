package com.example.repository;

import java.util.List;

import com.example.entityLibrary.Library;

public interface LibraryRepositoryCustom {

	List<Library> findAllByAuthor(String authorName);
}
