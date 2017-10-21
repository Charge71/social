package com.charge71.social;

import org.apache.commons.lang3.StringUtils;

import com.charge71.social.operations.Operation;
import com.charge71.social.operations.OperationFollow;
import com.charge71.social.operations.OperationPost;
import com.charge71.social.operations.OperationRead;
import com.charge71.social.operations.OperationWall;

/**
 * Class used to parse the input string and return an operation.
 * 
 * @author Diego Bardari
 *
 */
class Parser {

	protected Operation parse(String input) {

		if (input.indexOf(" ") == -1) {
			OperationRead operationRead = new OperationRead();
			operationRead.setUser(input);
			return operationRead;
		} else if (input.indexOf(" -> ") != -1) {
			String[] split = StringUtils.splitByWholeSeparator(input, " -> ", 2);
			OperationPost operationPost = new OperationPost();
			operationPost.setUser(split[0]);
			operationPost.setMessage(split[1]);
			return operationPost;
		} else if (input.indexOf(" follows ") != -1) {
			String[] split = StringUtils.splitByWholeSeparator(input, " follows ", 2);
			OperationFollow operationFollow = new OperationFollow();
			operationFollow.setUser(split[0]);
			operationFollow.setSubscription(split[1]);
			return operationFollow;
		} else if (input.indexOf(" wall") != -1) {
			String user = StringUtils.substringBefore(input, " wall");
			OperationWall operationWall = new OperationWall();
			operationWall.setUser(user);
			return operationWall;
		}
		return null;
	}

}
