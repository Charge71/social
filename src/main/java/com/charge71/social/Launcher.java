package com.charge71.social;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

		Parser parser = new Parser();

		try (OperationPlayer op = new OperationPlayer("social-pu")) {

			try (Scanner scanner = new Scanner(System.in)) {
				while (true) {
					System.out.print("> ");
					String input = scanner.nextLine();
					logger.debug("Input: " + input);
					if (input.equals("=")) {
						System.out.println("Goodbye.");
						break;
					} else {
						Operation operation = parser.parse(input);
						if (operation != null) {
							List<String> list = op.play(operation);
							for (String output : list) {
								System.out.println(output);
							}
						} else {
							System.out.println("Unknown command");
						}
					}
				}
			}

		}

	}

}
