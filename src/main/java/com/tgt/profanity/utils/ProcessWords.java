package com.tgt.profanity.utils;

import com.tgt.profanity.helpers.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcessWords {

    public static ProcessWords instance = null;

    static Pattern pattern = Pattern.compile(Constants.WORD_PATTERN);

    protected ProcessWords() {
    }

    ;

    public static ProcessWords getInstance() {
        if (instance == null) {
            instance = new ProcessWords();
        }
        return instance;
    }

    //Removes spaces from string
    public static String getOnlyStrings(String s) {
        Matcher matcher = pattern.matcher(s);
        String text = matcher.replaceAll(" ");
        return text;
    }
    //Filter stopping word
    public void filterStopingWord(String word, StringBuilder filteredWords) {
        try {
            BaseResource baseResource =  new ProfanityResource();
            if (word.length() > 1) {
                if (isValidWord(word, baseResource)) {
                    filteredWords.append(word.trim() + " ");
                }
            } else if (word.length() > 1) {
                filteredWords.append(word.trim() + " ");
            }
        } catch (Exception e) {

        }
    }

    //Retrieve swear word and return as string
    public String getSwearWord(String word) {
        StringBuilder swearWords = new StringBuilder();
        try {
            BaseResource baseResource =  new ProfanityResource();
            if (!isValidWord(word, baseResource)) {
                if (word.trim().length() > 1) {
                    swearWords.append(word + " ");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return swearWords.toString();
    }

    //Verifies the given word with the swear word db(properties) file
    private boolean isSwearWord(String word,  BaseResource baseResource)
    { 
    	boolean valid = true;
    	if (word != null && word.trim().length() > 1) {
            char firstCharater = word.charAt(0);
             if (baseResource instanceof ProfanityResource) {
                if (Character.isLetter((char) firstCharater)) {
                    String searchProp = String.valueOf(word.charAt(0));
                    String words = baseResource.getProperties(searchProp);
                    if (words != null && words.contains(word.toLowerCase())) {
                        valid = false;
                    }
                }
            }
        }
    	return valid;
    }
    
    //Validates the swear word,To check for special characters and conversions
    private boolean isValidWord(String word, BaseResource baseResource) {
        boolean valid = true;
        try {
            word = word.toUpperCase();
            String strWithoutSPChar = null;
            String leetWord = null;
            boolean hasSpecChars = false;
            
           if(!word.matches(Constants.RegExForSpecialChar))
           {  
        	   hasSpecChars=true;
        	   strWithoutSPChar = word.replaceAll("[^a-zA-Z_]", "");
        	   leetWord = word;
        	   int wLength = word.length();
        	   for (int i = 0; i <wLength ; i++) {
        		   String subWord = word.substring(i, i+1);
        	         if (!Constants.ENGLISH_ALPHABETS.toUpperCase().contains(subWord)) {
        	        	 
        	        	 String replaceLetter = baseResource.getProperties(subWord);
        	        	 if(replaceLetter!=null && !replaceLetter.isEmpty())
        	        	 {
        	        	 leetWord = leetWord.replace(subWord, replaceLetter.toUpperCase().trim()); 
        	        	 }
        	         }
        	   }
           }
        	   
        	   if(hasSpecChars) {
        	   if(strWithoutSPChar!=null && !strWithoutSPChar.isEmpty())
        	   {
        		 valid=isSwearWord(strWithoutSPChar,   baseResource);
        	   }
        	   
        	   if(valid && (leetWord!=null && !leetWord.isEmpty()))
        	   {
        		   valid=isSwearWord(leetWord, baseResource);
        	   }
           }
        	   if(valid)
        	   {
        		   valid=isSwearWord(word, baseResource);
        	   }

            
        } catch (Exception e) {
            e.printStackTrace();
        }      
        return valid;
    }
}
