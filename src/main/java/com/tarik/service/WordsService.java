package com.tarik.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarik.dto.WordDto;
import com.tarik.dto.WordDtoIU;
import com.tarik.model.Words;
import com.tarik.repository.WordsRepository;

@Service
public class WordsService {

	@Autowired
	WordsRepository wordsRepository;

	public WordDto saveNewWord(String turkish, String english) {
		Words word = new Words();
		word.setTurkish(turkish);
		word.setEnglish(english);
		word.setDesteNo(1);
		LocalDate today = LocalDate.now();
		ZoneId zoneId = ZoneId.systemDefault();
		Date date = Date.from(today.atStartOfDay(zoneId).toInstant());

		word.setEklenmeTarihi(date);

		wordsRepository.save(word);

		WordDto wordDto = new WordDto();
		BeanUtils.copyProperties(word, wordDto);
		return wordDto;
	}

	public List<WordDto> getAllWords() {
		List<Words> wordsList = wordsRepository.findAll();
		return wordsList.stream().map(word -> {
			WordDto dto = new WordDto();
			BeanUtils.copyProperties(word, dto);
			return dto;
		}).toList();
	}

	public WordDto updateWordTranslation(Long id, WordDtoIU wordDtoIU) {
		Optional<Words> optional = wordsRepository.findById(id);
		if (optional.isPresent()) {
			Words word = new Words();
			BeanUtils.copyProperties(optional.get(), word);
			word.setTurkish(wordDtoIU.getTurkish());
			word.setEnglish(wordDtoIU.getEnglish());
			wordsRepository.save(word);
			WordDto wordDto = new WordDto();
			BeanUtils.copyProperties(word, wordDto);
			return wordDto;
		}
		return null;
	}

	public WordDto raiseTheWordStack(Long id) {
		Optional<Words> optional = wordsRepository.findById(id);
		Words word = new Words();
		if (optional.isPresent()) {
			WordDto wordDto = new WordDto();
			BeanUtils.copyProperties(optional.get(), word);
			if (optional.get().getDesteNo() < 8) {
				word.setDesteNo(optional.get().getDesteNo() + 1);
			} else {
				word.setDesteNo(8);
			}
			wordsRepository.save(word);
			BeanUtils.copyProperties(word, wordDto);
			return wordDto;
		}
		return null;
	}

	public WordDto dropTheWordStack(Long id) {
		Optional<Words> optional = wordsRepository.findById(id);
		Words word = new Words();
		if (optional.isPresent()) {
			WordDto wordDto = new WordDto();
			BeanUtils.copyProperties(optional.get(), word);
			if (optional.get().getDesteNo() > 1) {
				word.setDesteNo(optional.get().getDesteNo() - 1);
			} else {
				word.setDesteNo(1);
			}
			wordsRepository.save(word);
			BeanUtils.copyProperties(word, wordDto);
			return wordDto;
		}
		return null;
	}

	public List<WordDto> getTodayList() {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean deleteWordById(Long id) {
		Optional<Words> optional = wordsRepository.findById(id);
		if (optional.isPresent()) {
			Words word = optional.get();
			wordsRepository.delete(word);
			return true;
		}
		return false;
	}

}
