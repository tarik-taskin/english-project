package com.tarik.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tarik.model.Words;

@Repository
public interface WordsRepository extends JpaRepository<Words, Long>{
	List<Words> findByTurkish(String turkish);
	List<Words> findByEnglish(String english);
}
