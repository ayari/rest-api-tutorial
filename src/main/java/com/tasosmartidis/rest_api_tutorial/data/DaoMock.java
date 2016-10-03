package com.tasosmartidis.rest_api_tutorial.data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("daoMock")
@Transactional
public class DaoMock implements Dao {

	private static final Map<String, DiaryEntry> diaryEntries = new HashMap<String, DiaryEntry>();
		
	public DiaryEntry createDiaryEntry(DiaryEntry newEntry) {
		//set our own unique diary id
		newEntry.setEntryId("diary-entry-"+UUID.randomUUID().toString());
		
		diaryEntries.put(newEntry.getEntryId(),newEntry);
		
		if(diaryEntries.containsValue(newEntry))
			return newEntry;
		else
			throw new RuntimeException();
	}

	public DiaryEntry getDiaryEntry(String entryId) {
		return diaryEntries.get(entryId);
	}

	public DiaryEntry updateDiaryEntry(DiaryEntry updatedEntry) {
		diaryEntries.replace(updatedEntry.getEntryId(), updatedEntry);
		
		if(diaryEntries.containsValue(updatedEntry))
			return updatedEntry;
		else
			throw new RuntimeException();
	}

	public String deleteDiaryEntry(String entryId) {
		diaryEntries.remove(entryId);
		
		if(diaryEntries.containsKey(entryId))
			throw new RuntimeException();
		else
			return entryId;
	}

	public Map<String, DiaryEntry> getAllDiaryEntries() {
		return diaryEntries;
	}
 
}
