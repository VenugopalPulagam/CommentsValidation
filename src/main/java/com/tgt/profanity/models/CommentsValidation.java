package com.tgt.profanity.models;

import java.io.Serializable;

public class CommentsValidation implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String reviewComment;
	
	
	public String getReviewComment() {
		return reviewComment;
	}

	public void setReviewComment(String reviewComment) {
		this.reviewComment = reviewComment;
	}

}
