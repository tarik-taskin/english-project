package com.tarik.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tarik.dto.WordDto;
import com.tarik.dto.WordDtoIU;
import com.tarik.service.WordsService;

@RequestMapping("/word")
@RestController
public class WordController {

	@Autowired
	WordsService wordsService;

	@PostMapping(path = "/save")
	public WordDto saveNewWord(@RequestBody WordDtoIU wordDtoIU) {
		return wordsService.saveNewWord(wordDtoIU.getTurkish(), wordDtoIU.getEnglish());
	}

	@GetMapping(path = "/getAll")
	public List<WordDto> getAllWords() {
		return wordsService.getAllWords();
	}

	@PutMapping("update/{id}")
	public WordDto updateWordTranslation(@PathVariable Long id, @RequestBody WordDtoIU wordDtoIU) {
		return wordsService.updateWordTranslation(id, wordDtoIU);
	}

	@PutMapping("raiseDeck/{id}")
	public WordDto raiseTheWordStack(@PathVariable Long id) {
		return wordsService.raiseTheWordStack(id);
	}

	@PutMapping("/dropDeck/{id}")
	public WordDto dropTheWordStack(@PathVariable Long id) {
		return wordsService.dropTheWordStack(id);
	}

	@DeleteMapping("/delete/{id}")
	public Boolean deleteWordById(@PathVariable Long id) {
		return wordsService.deleteWordById(id);
	}

}
