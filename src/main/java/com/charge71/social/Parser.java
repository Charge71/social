package com.charge71.social;

import com.charge71.social.operations.Operation;

/**
 * Parses the input string and return an operation.
 * 
 * @author Diego Bardari
 *
 */
public interface Parser {

	/**
	 * Parses the given input returning an operation to execute.
	 * 
	 * @param input
	 *            The string to parse
	 * @return an operation to execute or null for unsupported operations
	 */
	public Operation parse(String input);

}
