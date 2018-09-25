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
	private static final int BROKER_PORT = 5000;
	private static final int MARKET_PORT = 5001;
	private static HashMap<Integer, Socket> table = new HashMap<Integer, Socket>();

	RouterServer() {
		try {
			System.out.println("Router Started.");
			ServerSocket marketServerSocket = new ServerSocket(MARKET_PORT);
			Socket marketSocket = marketServerSocket.accept();
			createNewListing(ID, marketSocket);
			System.out.println("Market Connected.");

			ServerSocket brokerServerSocket = new ServerSocket(BROKER_PORT);
			Socket brokerSocket = brokerServerSocket.accept();
			createNewListing(ID, brokerSocket);
			System.out.println(brokerServerSocket.toString() + " Connected.");




			//INPUT FROM BROKER TO SERVER
			//Creates a Reader that receives objects from the location specified upon initialisation.
			//i.e new BufferedReader(new InputStreamReader(System.in, true) receives input from the std.in
			//i.e new BufferedReader(new InputStreamReader(Socket.getInputStream(), true) receives input from Socket's input stream
			BufferedReader input = new BufferedReader(new InputStreamReader(table.get(0).getInputStream()));

			//OUTPUT FROM ROUTER TO MARKET
			//Creates a PrintWriter that sends an object to the location specified upon initialisation.
			//i.e new PrintWriter(System.out, true) will send output to std.out
			//i.e new PrintWriter(Socket.getOutputStream(), true) will send output to the Socket's output stream
			PrintWriter output = new PrintWriter(marketSocket.getOutputStream(), true);


			//validating checksum
			String brokerInput = input.readLine();
			try {
				if (!(new ValidateMessage(brokerInput).validateChecksum())) {
					throw new IOException();
				}
			} catch (IOException e) {
				System.err.println("Invalid ValidateMessage.");
				System.exit(5001);
			}
			output.println(brokerInput);
		} catch (Exception e) {
			System.out.println("Router: Exception caught when trying to listen on port "
					+ BROKER_PORT + " or listening for a connection");
		}
	}

	void createNewListing(int ID, Socket socket) {
		table.put(ID, socket);
		ID++;
	}
}

