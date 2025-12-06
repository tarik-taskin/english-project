package com.tarik.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WordDto { 
	Long wordID; 
	String english; 
	String turkish; 

	Date eklenmeTarihi; 

	Integer desteNo;
}
