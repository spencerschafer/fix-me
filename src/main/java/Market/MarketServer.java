package Market;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class MarketServer {

	private static final int MARKET_PORT = 5001;
	private static final String HOSTNAME = "localhost";


	MarketServer(String instrument, int quantity) {
		Socket marketSocket = null;

		try {
			marketSocket = new Socket(HOSTNAME, MARKET_PORT);
			System.out.println("Market Started.");
		} catch (IOException e) {
			System.err.println("Market was unable to established a connection to " + HOSTNAME + " on port: " + MARKET_PORT);
			System.exit(5001);
		}

		try {
			PrintWriter output = new PrintWriter(System.out, true);
			BufferedReader input = new BufferedReader(new InputStreamReader(marketSocket.getInputStream()));

			//validating checksum
			String marketInput = input.readLine();
			try {
				if (!(new ValidateMessage(marketInput).validateMessage(instrument, quantity))) {
					throw new IOException();
				}
			} catch (IOException e) {
				System.err.println("Invalid ValidateMessage.");
				System.exit(5001);
			}

			while (marketInput != null) {
				output.println(marketInput);
				marketInput = input.readLine();
			}
		} catch (IOException e) {
			System.err.println("Market was unable to create PrintWriter in class MarketServer");
			System.exit(5001);
		}
	}
}
