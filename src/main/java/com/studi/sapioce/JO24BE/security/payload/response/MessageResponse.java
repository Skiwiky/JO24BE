package com.studi.sapioce.JO24BE.security.payload.response;

public class MessageResponse {
	 private String message;

	    /**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

		public MessageResponse(String message) {
	        this.message = message;
	    }
}
