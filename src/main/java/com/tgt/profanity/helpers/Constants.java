package com.tgt.profanity.helpers;


public interface Constants {

	String ENGLISH_ALPHABETS = "abcdefghijklmnopqrstuvwxyz";
    String UTF_8="UTF8";
    String SPACE = "\\ ";
    String COMMA = ",";
    String WORD_PATTERN = "[^a-z A-Z 0-9]";
    String MULTIPLE_SPACE_TAB_NEW_LINE="  +|   +|\t|\r|\n";
    String INVALID_DATA="Invalid Data";
    String RegExForSpecialChar = "^[a-zA-Z-]+$";
    
    //Constants for unit testing
	String BadWord1="{\"reviewComment\":\"SEX HEllo\"}";  
	String LeetWord ="{\"reviewComment\":\" HEllo $ex \"}";  
	String WordWithSpaces ="{\"reviewComment\":\" HEllo F U C K testing \"}"; 
	String WordWithSpecialChars ="{\"reviewComment\":\"bad word S.e.x with space testing \"}";  
	String JsonWithOutValue ="{\"reviewComment\":}";  
    String WordWithOutBadWord = "{\"reviewComment\":\"I dont have any bad word\"}";  
    
            
    enum STATUS {
        SUCCESS,FAILURE
    }
	
    interface Resources {
        String PROFANITY_EN_FILE = "com/tgt/profanity/data/words_en.properties";
        
    }
}
