package com.charge71.social.operations;

/**
 * Operation executed to read a user wall, including the posts from the users he
 * follows and his own posts.
 * 
 * @author Diego Bardari
 *
 */
public class OperationWall extends Operation {

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OperationWall [getUser()=");
		builder.append(getUser());
		builder.append("]");
		return builder.toString();
	}

}
