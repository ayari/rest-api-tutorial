package com.tasosmartidis.rest_api_tutorial.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Document(collection="entries") 
public class DiaryEntry {

	@Id
	private String entryId;
	private String entryTitle;
	private String entryText;
	
	public String getEntryId() {
		return entryId;
	}
	public void setEntryId(String entryId) {
		this.entryId = entryId;
	}
	public String getEntryTitle() {
		return entryTitle;
	}
	public void setEntryTitle(String entryTitle) {
		this.entryTitle = entryTitle;
	}
	public String getEntryText() {
		return entryText;
	}
	public void setEntryText(String entryText) {
		this.entryText = entryText;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((entryId == null) ? 0 : entryId.hashCode());
		result = prime * result
				+ ((entryTitle == null) ? 0 : entryTitle.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DiaryEntry other = (DiaryEntry) obj;
		if (entryId == null) {
			if (other.entryId != null)
				return false;
		} else if (!entryId.equals(other.entryId))
			return false;
		if (entryTitle == null) {
			if (other.entryTitle != null)
				return false;
		} else if (!entryTitle.equals(other.entryTitle))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		String mappedJson=null;		
		
		try{
			ObjectMapper mapper = new ObjectMapper();
			ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
			mappedJson = objectWriter.writeValueAsString(this);
		}catch(Exception ex) {
			ex.printStackTrace();
		} 
		
		return mappedJson;
	}
	
}
