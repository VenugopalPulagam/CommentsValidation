package com.tgt.profanity.models;

public class ProfanityResponse {
	
	String status;
    String message;   
    boolean isBadWord;
    String filteredBadWords;

    public String getFilteredBadWords() {
		return filteredBadWords;
	}

	public void setFilteredBadWords(String filteredBadWords) {
		this.filteredBadWords = filteredBadWords;
	}

	public boolean isBadWord() {
		return isBadWord;
	}

	public void setBadWord(boolean isBadWord) {
		this.isBadWord = isBadWord;
	}

	public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
   
}
