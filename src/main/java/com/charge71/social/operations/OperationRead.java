package com.charge71.social.operations;

/**
 * Operation executed to read user posts.
 * 
 * @author Diego Bardari
 *
 */
public class OperationRead extends Operation {

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OperationRead [getUser()=");
		builder.append(getUser());
		builder.append("]");
		return builder.toString();
	}

}
