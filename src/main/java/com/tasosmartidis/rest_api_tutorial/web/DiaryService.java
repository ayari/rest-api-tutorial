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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
 
@RestController
@RequestMapping("/service/geek-diaries")
@Api(value = "/service/geek-diaries", description = "CRUD operations on the Geek Diaries datastore")
public class DiaryService {
	
	@Autowired
	DaoMongo dao; 
	
	@RequestMapping(value="/entry/{entryId}", method=RequestMethod.GET)
	@ApiOperation(value = "Retrieves a diary entry",
			notes = "The specified id is used to retrieve and return the diary entry",
			response = DiaryEntry.class)
	public ResponseEntity<DiaryEntry> getDiaryEntry(@PathVariable String entryId) {
		DiaryEntry diaryEntry = dao.getDiaryEntry(entryId);
		
		return new ResponseEntity<DiaryEntry>(diaryEntry,HttpStatus.OK); 
	}
	
	@RequestMapping(value="/entry/", method=RequestMethod.POST)
	@ApiOperation(value = "Creates a new diary entry that will be persisted in the database", 
			notes = "On success the newly created diary entry will be returned to the user along with the HTTP code",
			response = DiaryEntry.class)
	public ResponseEntity<DiaryEntry> createDiaryEntry(@RequestBody DiaryEntry newEntry) { 
		 DiaryEntry newDiaryEntry = dao.createDiaryEntry(newEntry);

		return new ResponseEntity<DiaryEntry>(newDiaryEntry,HttpStatus.OK); 
	}
	
	@RequestMapping(value="/entry/", method=RequestMethod.PUT)
	@ApiOperation(value = "Updates diary entry if attributes have changed", 
			notes = "On success the updated diary entry will be returned to the user along with the HTTP code",
			response = DiaryEntry.class)
	public ResponseEntity<DiaryEntry> updateDiaryEntry(@RequestBody DiaryEntry newEntry) { 
		 DiaryEntry updatedDiaryEntry = dao.updateDiaryEntry(newEntry);

		return new ResponseEntity<DiaryEntry>(updatedDiaryEntry,HttpStatus.OK); 
	}
	
	@RequestMapping(value="/entry/{entryId}", method=RequestMethod.DELETE)
	@ApiOperation(value = "Deletes a specified diary entry from the database", 
			notes = "Removes a diary entry based on its specified and unique id. On success the id is returned to requester",
			response = String.class)
	public ResponseEntity<String> deleteDiaryEntry(@PathVariable String entryId) { 
		 String deletedDiaryEntry = dao.deleteDiaryEntry(entryId);

		return new ResponseEntity<String>(deletedDiaryEntry,HttpStatus.OK); 
	}
	
	@RequestMapping(value="/entry/", method=RequestMethod.GET) 
	@ApiOperation(value = "Retrieves all diary entries persisted in the database", 
			response = DiaryEntry.class,
		    responseContainer = "List")
	public ResponseEntity<Map<String, DiaryEntry>> getDiaryEntries() { 
		Map<String, DiaryEntry> diaryEntries = dao.getAllDiaryEntries();

		return new ResponseEntity<Map<String, DiaryEntry>>(diaryEntries,HttpStatus.OK); 
	}
}
