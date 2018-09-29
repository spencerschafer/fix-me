package Router;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;


class RouterServer {
	private static int ID = 0;
	private int BROKER_ID;
	private int MARKET_ID;
	private static final int BROKER_PORT = 5000;
	private static final int MARKET_PORT = 5001;
	private static HashMap<Integer, Socket> table = new HashMap<Integer, Socket>();

	RouterServer() {
		try {
			System.out.println("Router Started.");
			ServerSocket marketServerSocket = new ServerSocket(MARKET_PORT);
			Socket marketSocket = marketServerSocket.accept();
			MARKET_ID = ID;
			createNewListing(ID, marketSocket);

			PrintWriter marketOutput = new PrintWriter(table.get(0).getOutputStream(), true);
			BufferedReader marketInput = new BufferedReader(new InputStreamReader(table.get(0).getInputStream()));

			marketOutput.println(MARKET_ID);
			System.out.println("Market Connected.");

			ServerSocket brokerServerSocket = new ServerSocket(BROKER_PORT);
			Socket brokerSocket = brokerServerSocket.accept();
			BROKER_ID = ID;
			createNewListing(ID, brokerSocket);

			PrintWriter brokerOutput = new PrintWriter(table.get(1).getOutputStream(), true);
			BufferedReader brokerInput = new BufferedReader(new InputStreamReader(table.get(1).getInputStream()));

			brokerOutput.println(BROKER_ID);
			System.out.println("Broker Connected.\n");

			//validating checksum
			String brokerInputLine = brokerInput.readLine();
			System.out.println("Message received from Broker: " + brokerInputLine);
			try {
				if (!(new ValidateMessage(brokerInputLine).validateChecksum(0))) {
					brokerOutput.println("invalid");
					throw new IOException();
				}
			} catch (IOException e) {
				System.err.println("Invalid message.");
				System.exit(1);
			}

			//sending message
			marketOutput.println(BROKER_ID);
			marketOutput.println(brokerInputLine);
			System.out.println("Message sent to Market: " + brokerInputLine);


			String line =  marketInput.readLine();
			System.out.println("Message received from Market: " + line);
			brokerOutput.println(line);
			System.out.println("Message sent to Broker: " + line);

		} catch (Exception e) {
			System.out.println("Exception caught when trying to listen on port "
					+ BROKER_PORT + " or listening for a connection");
			System.exit(1);
		}
	}

	private void createNewListing(int ID, Socket socket) {
		table.put(ID, socket);
		RouterServer.ID++;
	}
}

