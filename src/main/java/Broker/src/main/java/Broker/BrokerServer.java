package Broker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class BrokerServer {

	private  int broker_ID;
    private static final int BROKER_PORT = 5000;
    private static final String HOSTNAME = "localhost";


    BrokerServer(String FIX_MESSAGE) {
        Socket brokerSocket = null;

        try {
            brokerSocket = new Socket(HOSTNAME, BROKER_PORT);
            System.out.println("Broker Started.");
        } catch (Exception e) {
            System.err.println("Broker was unable to established a connection to " + HOSTNAME + " on port: " + BROKER_PORT);
            System.exit(5000);
        }

        try {
            PrintWriter output = new PrintWriter(brokerSocket.getOutputStream(), true);
            output.println(FIX_MESSAGE);

            String line;
			BufferedReader input = new BufferedReader(new InputStreamReader(brokerSocket.getInputStream()));
			line = input.readLine();
			broker_ID = Integer.parseInt(line);
            System.out.println("Broker ID: " + broker_ID);

            line = input.readLine();
            if (line.equalsIgnoreCase("valid")) {
				System.out.print("Market executed Broker (ID:" + broker_ID + ") trade: ");
				System.out.println(FIX_MESSAGE);
			}
			else if (line.equalsIgnoreCase("invalid")) {
				System.out.print("Market rejected Broker (ID:" + broker_ID + ") trade: ");
				System.out.println(FIX_MESSAGE);
			}
        } catch (Exception e) {
            System.err.println("Broker was unable to create PrintWriter in class Broker.BrokerServer");
            System.exit(1);
        }
    }
}
