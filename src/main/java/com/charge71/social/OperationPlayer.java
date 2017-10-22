package com.charge71.social;

import java.util.List;

import com.charge71.social.operations.Operation;

/**
 * Manages operations execution.
 * 
 * @author Diego Bardari
 *
 */
public interface OperationPlayer extends AutoCloseable {

	/**
	 * Executes the given operation and return al list of string to be printed.
	 * 
	 * @param op
	 *            the operation to execute
	 * @return a list of strings to print out
	 */
	public List<String> play(Operation op);

}
