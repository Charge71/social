package com.charge71.social.operations;

/**
 * Operation executed when a new post is created.
 * 
 * @author Diego Bardari
 *
 */
public class OperationPost extends Operation {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OperationPost [message=");
		builder.append(message);
		builder.append(", getUser()=");
		builder.append(getUser());
		builder.append("]");
		return builder.toString();
	}

}
