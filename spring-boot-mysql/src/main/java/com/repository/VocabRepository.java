package com.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.entity.Vocabulary;

@Repository
@Transactional
public interface VocabRepository extends JpaRepository<Vocabulary, Integer> {

	// @Modifying
	// @Query(value = "delete from vocab t where t.word = :word", nativeQuery = true)
	// void deleteByWord(@Param("word") String word);

	@Query(value = "SELECT v FROM Vocabulary v WHERE v.word IN :words")
	List<Vocabulary> findVocabByWordList(@Param("words") List<String> words);
	
	@Modifying
	@Query(value = "insert into Users (name, age, email, status) values (:name, :age, :email, :status)", nativeQuery = true)
	void insertUser(@Param("name") String name, @Param("age") Integer age, 
	  @Param("status") Integer status, @Param("email") String email);

}
