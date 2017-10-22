package com.charge71.social.impl;

import org.apache.commons.lang3.StringUtils;

import com.charge71.social.Parser;
import com.charge71.social.operations.Operation;
import com.charge71.social.operations.OperationFollow;
import com.charge71.social.operations.OperationPost;
import com.charge71.social.operations.OperationRead;
import com.charge71.social.operations.OperationWall;

public class ParserImpl implements Parser {

	public Operation parse(String input) {

		if (input.indexOf(" ") == -1) {
			// no spaces means it's a user name -> read
			OperationRead operationRead = new OperationRead();
			operationRead.setUser(input);
			return operationRead;
		} else if (input.indexOf(" -> ") != -1) {
			// the arrow means a new post
			String[] split = StringUtils.splitByWholeSeparator(input, " -> ", 2);
			OperationPost operationPost = new OperationPost();
			// before the arrow is the user name
			operationPost.setUser(split[0]);
			// after the arrow the message
			operationPost.setMessage(split[1]);
			return operationPost;
		} else if (input.indexOf(" follows ") != -1) {
			// follows keyword -> follow
			String[] split = StringUtils.splitByWholeSeparator(input, " follows ", 2);
			OperationFollow operationFollow = new OperationFollow();
			// before follow the username
			operationFollow.setUser(split[0]);
			// after follow the followed user
			operationFollow.setSubscription(split[1]);
			return operationFollow;
		} else if (input.indexOf(" wall") != -1) {
			// wall keyword -> show wall
			String user = StringUtils.substringBefore(input, " wall");
			OperationWall operationWall = new OperationWall();
			// before wall is the user name
			operationWall.setUser(user);
			return operationWall;
		}
		// unknown operation
		return null;
	}

}
