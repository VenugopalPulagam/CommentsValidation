package com.tgt.profanity.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.tgt.profanity.utils.*;
import com.tgt.profanity.models.*;


@RestController
@RequestMapping("/v1/RCValidations")
public class CommentsValidationController {
		
	//Controller to read the user input and identify the bad words.
	@RequestMapping(value="/CommentsValidation", method= RequestMethod.POST, produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity validateComments(@RequestBody CommentsValidation inputJsonObj) {
		
		ProfanityResponse response = new ProfanityResponse();
		
		//Check if object has expected values if not return as bad request.
		if(inputJsonObj.getReviewComment()==null ||inputJsonObj.getReviewComment().isEmpty())
		{
			return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
		}
						
		String reviewComment = inputJsonObj.getReviewComment();
				
		try {
			
		
		StringBuilder finalBadWords = new StringBuilder();
		
		WordParser wordParser = WordParser.getInstance();
		
		//To retrieve the bad words
		finalBadWords.append(wordParser.getSwearWords(reviewComment));
		
		//Check if any bad words identified in the given word.
		boolean isBadWordExists=(finalBadWords!=null && !finalBadWords.toString().equals("")) ? true : false;
                
        wordParser.resetSwearWords();
        
        response.setBadWord(isBadWordExists);        
        response.setFilteredBadWords(finalBadWords.toString());
        response.setStatus("Success");
        
        return new ResponseEntity(response, HttpStatus.OK);
        
		}
		catch(Exception ex)
		{
			return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
