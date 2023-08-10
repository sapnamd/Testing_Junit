package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entityLibrary.Library;

public interface LibraryRepository extends JpaRepository<Library, String>,LibraryRepositoryCustom {

}
