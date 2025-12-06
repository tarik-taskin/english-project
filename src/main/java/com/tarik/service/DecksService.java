package com.tarik.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarik.dto.DeckDto;
import com.tarik.model.Decks;
import com.tarik.repository.DecsRepository;

@Service
public class DecksService {
	@Autowired
	DecsRepository decksRepository;

	public List<DeckDto> getAllDecks() {
		List<Decks> decks = decksRepository.findAll();
		return decks.stream().map(deck -> {
			DeckDto dto = new DeckDto();
			BeanUtils.copyProperties(deck, dto);
			return dto;
		}).toList();
	}

	public Boolean updateDecksLastRepeatDate(Long id) {
		Optional<Decks> optional = decksRepository.findById(id);
		if (optional.isPresent() && id >= 1 && id <= 8) {
			Decks deck = new Decks();
			BeanUtils.copyProperties(optional.get(), deck);
			LocalDate today = LocalDate.now();
			ZoneId zoneId = ZoneId.systemDefault();
			Date date = Date.from(today.atStartOfDay(zoneId).toInstant());
			deck.setSonTekrarTarihi(date);
			decksRepository.save(deck);
			return true;
		}
		return false;
	}
}