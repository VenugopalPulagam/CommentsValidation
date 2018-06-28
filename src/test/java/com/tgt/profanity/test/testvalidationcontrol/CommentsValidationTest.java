package com.tgt.profanity.test.testvalidationcontrol;

import com.tgt.profanity.controllers.*;
import com.tgt.profanity.helpers.Constants;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.*;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.hamcrest.Matchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;



@RunWith(SpringJUnit4ClassRunner.class)
public class CommentsValidationTest {

	private MockMvc mockMvc;
	
	
	@InjectMocks
	private CommentsValidationController commentsvalidation;
	
	@Before
	 public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(commentsvalidation)
                .build();
        }
	
	@Test
	public void CommentsValidateTest() throws Exception{
		
		
		       //Unit test to identify if bad word exists in  a given word.
		        mockMvc.perform(post("/v1/RCValidations/CommentsValidation").content(Constants.BadWord1)
				  .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(status().isOk())
		        .andExpect(jsonPath("$.badWord", Matchers.is(true)))
                .andExpect(jsonPath("$.filteredBadWords", Matchers.is("SEX  ")));
		
		       //Unit test to identify if bad leet word exists in  a given word.
				mockMvc.perform(post("/v1/RCValidations/CommentsValidation").content(Constants.LeetWord)
						  .contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.badWord", Matchers.is(true)))
		        .andExpect(jsonPath("$.filteredBadWords", Matchers.is("$ex  ")));
				
				
		        //Unit test to identify if bad  word with spaces is identified		
				mockMvc.perform(post("/v1/RCValidations/CommentsValidation").content(Constants.WordWithSpaces)
						  .contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.badWord", Matchers.is(true)))
		        .andExpect(jsonPath("$.filteredBadWords", Matchers.is("FUCK ")));
				
				
				//Unit test to identify if bad word with special characters								
				mockMvc.perform(post("/v1/RCValidations/CommentsValidation").content(Constants.WordWithSpecialChars)
						  .contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.badWord", Matchers.is(true)))
		        .andExpect(jsonPath("$.filteredBadWords", Matchers.is("S.e.x  ")));
				
				//Unit test to test 400 bad request error				
				mockMvc.perform(post("/v1/RCValidations/CommentsValidation").content(Constants.JsonWithOutValue)
						  .contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
				
				//Unit test to check if no bad word exists in the given sentence
				mockMvc.perform(post("/v1/RCValidations/CommentsValidation").content(Constants.WordWithOutBadWord)
						  .contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.badWord", Matchers.is(false)))
		        .andExpect(jsonPath("$.filteredBadWords", Matchers.is("")));
				

	}

}
