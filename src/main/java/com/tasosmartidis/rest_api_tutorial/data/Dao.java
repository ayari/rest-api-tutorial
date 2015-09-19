package com.tasosmartidis.rest_api_tutorial.data;

import java.util.Map;

public interface Dao {

	public DiaryEntry createDiaryEntry(DiaryEntry newEntry);
	
	public DiaryEntry getDiaryEntry(String entryId);
	
	public DiaryEntry updateDiaryEntry(DiaryEntry updatedEntry);
	
	public String deleteDiaryEntry(String entryId);
	
	public Map<String,DiaryEntry> getAllDiaryEntries();
}
