package com.tasosmartidis.rest_api_tutorial.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tasosmartidis.rest_api_tutorial.data.DaoMongo;
import com.tasosmartidis.rest_api_tutorial.data.DiaryEntry;


@RestController
@RequestMapping("/service/geek-diaries")
public class DiaryService {
	
	@Autowired
	DaoMongo dao; 
	
	@RequestMapping(value="/entry/{entryId}", method=RequestMethod.GET)
	public ResponseEntity<DiaryEntry> getDiaryEntry(@PathVariable String entryId) {
		DiaryEntry diaryEntry = dao.getDiaryEntry(entryId);
		
		return new ResponseEntity<DiaryEntry>(diaryEntry,HttpStatus.OK); 
	}
	
	@RequestMapping(value="/entry/", method=RequestMethod.POST)
	public ResponseEntity<DiaryEntry> createDiaryEntry(@RequestBody DiaryEntry newEntry) { 
		 DiaryEntry newDiaryEntry = dao.createDiaryEntry(newEntry);

		return new ResponseEntity<DiaryEntry>(newDiaryEntry,HttpStatus.OK); 
	}
	
	@RequestMapping(value="/entry/", method=RequestMethod.PUT)
	public ResponseEntity<DiaryEntry> updateDiaryEntry(@RequestBody DiaryEntry newEntry) { 
		 DiaryEntry updatedDiaryEntry = dao.updateDiaryEntry(newEntry);

		return new ResponseEntity<DiaryEntry>(updatedDiaryEntry,HttpStatus.OK); 
	}
	
	@RequestMapping(value="/entry/{entryId}", method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteDiaryEntry(@PathVariable String entryId) { 
		 String deletedDiaryEntry = dao.deleteDiaryEntry(entryId);

		return new ResponseEntity<String>(deletedDiaryEntry,HttpStatus.OK); 
	}
	
	@RequestMapping(value="/entry/", method=RequestMethod.GET) 
	public ResponseEntity<Map<String, DiaryEntry>> getDiaryEntries() { 
		Map<String, DiaryEntry> diaryEntries = dao.getAllDiaryEntries();

		return new ResponseEntity<Map<String, DiaryEntry>>(diaryEntries,HttpStatus.OK); 
	}
}
