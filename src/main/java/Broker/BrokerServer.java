package Broker;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

class BrokerServer {

    private static final int BROKER_PORT = 5000;
    private static final String HOSTNAME = "localhost";


    BrokerServer(String FIX_MESSAGE) {
        Socket brokerSocket = null;

        try {
            brokerSocket = new Socket(HOSTNAME, BROKER_PORT);
            System.out.println("Broker Started.");
        } catch (IOException e) {
            System.err.println("Unable to established a connection to " + HOSTNAME + " on port: " + BROKER_PORT);
            System.exit(5000);
        }

        try {
            PrintWriter output = new PrintWriter(brokerSocket.getOutputStream(), true);
            output.println(FIX_MESSAGE);
        } catch (IOException e) {
            System.err.println("Unable to create PrintWriter in class BrokerServer");
            System.exit(5000);
        }
    }
}
