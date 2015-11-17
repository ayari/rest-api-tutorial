package com.tasosmartidis.rest_api_tutorial.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tasosmartidis.rest_api_tutorial.config.SpringMongoConfig;


@Repository("daoMongo")
@Transactional
public class DaoMongo implements Dao {

	private MongoOperations mongoOperations;
	
	public DaoMongo() {
		mongoOperations = loadMongoConfiguration();
	}
	
	public DiaryEntry createDiaryEntry(DiaryEntry newEntry) {		
		// The DiaryEntry instance will have the ID assigned to it automatically
		mongoOperations.save(newEntry);
		
		return newEntry;
	}

	public DiaryEntry getDiaryEntry(String entryId) {
		// query to search diary entries. We will retrieve entries with their specified unique ID 
    	Query searchQuery = new Query(); 
    	searchQuery.addCriteria(Criteria.where("entryId").is(entryId)); 
    	
    	// retrieve the specified DiaryEntry from database
		return mongoOperations.findOne(searchQuery,DiaryEntry.class);     	
	}

	public DiaryEntry updateDiaryEntry(DiaryEntry updatedEntry) {
		
		// If entry with the same id exists, it updates the entry
    	mongoOperations.save(updatedEntry);
    	
    	// note that we could also use 'Update' object and e.g., its 'set' method
    	// to update individual fields of an entry
    	    	
    	return getDiaryEntry(updatedEntry.getEntryId());
	}

	public String deleteDiaryEntry(String entryId) {
		Query searchQuery = new Query(); 
    	searchQuery.addCriteria(Criteria.where("entryId").is(entryId));
    	
    	// delete the specified DiaryEntry from database
		mongoOperations.remove(searchQuery,DiaryEntry.class); 
		
		return entryId;
	}

	public Map<String, DiaryEntry> getAllDiaryEntries() {
		List<DiaryEntry> allEntries = mongoOperations.findAll(DiaryEntry.class); 
		return putEntriesToMap(allEntries);
	}
	
	private Map<String, DiaryEntry> putEntriesToMap(List<DiaryEntry> allEntries) {
		Map<String, DiaryEntry> mapWithEntries = new HashMap<>();
		
		for(DiaryEntry entry : allEntries) {
			mapWithEntries.put(entry.getEntryId(), entry);
		}
		
		return mapWithEntries;
	}

	private MongoOperations loadMongoConfiguration() {
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = 
	             new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) applicationContext.getBean("mongoTemplate");
		
		return mongoOperation;
	}

}
