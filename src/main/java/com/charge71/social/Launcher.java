package com.charge71.social;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.charge71.social.impl.OperationPlayerImpl;
import com.charge71.social.impl.ParserImpl;
import com.charge71.social.operations.Operation;

/**
 * Main class of the application. Reads the input, get the operation from the
 * parser and passes it to the player.
 * 
 * @author Diego Bardari
 *
 */
public class Launcher {

	private static final Logger logger = LoggerFactory.getLogger(Launcher.class);

	public static void main(String[] args) throws Exception {

		// create new parser
		Parser parser = new ParserImpl();

		// create new operation player
		try (OperationPlayer op = new OperationPlayerImpl("social-pu")) {

			// scan the input line
			try (Scanner scanner = new Scanner(System.in)) {
				while (true) {
					System.out.print("> ");
					String input = scanner.nextLine();
					logger.debug("Input: " + input);
					if (input.equals("=")) {
						// exit
						System.out.println("Goodbye.");
						break;
					} else {
						// parse operation
						Operation operation = parser.parse(input);
						if (operation != null) {
							// execute the operation
							List<String> list = op.play(operation);
							// print the lines returned after the execution
							for (String output : list) {
								System.out.println(output);
							}
						} else {
							// operation was null
							System.out.println("Unknown command");
						}
					}
				}
			}

		}

	}

}
