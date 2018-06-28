package com.tgt.profanity.utils;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tgt.profanity.helpers.*;


public class WordParser {

	 static String swearBySpace = null;
	    private static WordParser instance = null;
	    private static Set<String> swearWords = new LinkedHashSet<String>();
	    ProcessWords processWords = ProcessWords.getInstance();

	    protected WordParser() {};
	    public static WordParser getInstance() {
	        if (instance == null) {
	            instance = new WordParser();
	        }
	        return instance;
	    }

	    	    
	    //To get the  swear words
	    public String getSwearWords(String data) {
	        String[] words = data.split(Constants.SPACE);
	        StringBuilder filteredWords = new StringBuilder();
	        try {
	            if (words.length > 0) {
	                int prevSwearIndex = 0;
	                for (int i = 0; i < words.length; i++) {
	                    String word = words[i];
	                    ValidateSwearBySpace(word, i, prevSwearIndex);
	                    String result = processWords.getSwearWord(word.trim());
	                    if (result.trim().length() > 0) {
	                        filteredWords.append(result + " ");
	                    }
	                }
	            } else if (data.trim().length() > 11) {
	                String result = processWords.getSwearWord(data.trim());
	                if (result.trim().length() > 1) {
	                    filteredWords.append(result + " ");
	                }
	            }

	            if (swearWords.size() > 0) {
	                for (String _data : swearWords) {
	                    if (_data.trim().length() > 1) {
	                        filteredWords.append(_data + " ");
	                    }
	                }
	            }
	            return filteredWords.toString();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return "";
	    }

	    Pattern Spacepattern = Pattern.compile("\\s");

	    
	    // To handle spaces in the word.
	    public void ValidateSwearBySpace(String word, int currentIndex, int prevSwearIndex) {
	        try {
	            Matcher matcher = Spacepattern.matcher(word);
	            boolean spacefound = matcher.find();
	            if (word.length() <= 2) {
	                if (prevSwearIndex == 0) {
	                    prevSwearIndex = currentIndex;
	                }
	                prevSwearIndex++;
	                if (prevSwearIndex - currentIndex == 1 && !spacefound) {
	                    if (swearBySpace == null) {
	                        swearBySpace = word.trim();
	                    } else {
	                        swearBySpace = swearBySpace + word.trim();
	                    }
	                }
	            } else if (swearBySpace != null) {
	                prevSwearIndex = 0;
	                String result = processWords.getSwearWord(swearBySpace.trim());
	                if (result != null && result.trim().length() > 0) {
	                    swearWords.add(swearBySpace);
	                }
	                swearBySpace = null;
	            }
	        } catch (Exception exception) {
	            exception.printStackTrace();
	        }
	    }
	    
	    //Clean the swear words.
	    public void resetSwearWords(){
	        swearWords = new LinkedHashSet<String>();
	    }
}
