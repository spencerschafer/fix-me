import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import java.io.*;
import java.net.*;

public class Broker {
    private static final int BROKER_PORT = 5000;
    private static final String HOSTNAME = "localhost";

    public static void main(String[] args) {
        System.out.println("Broker Started.");
        try {
            Socket brokerSocket = new Socket(HOSTNAME, BROKER_PORT);

            //input
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

            //output
            PrintWriter output = new PrintWriter(brokerSocket.getOutputStream(), true);

            String userInput;
            while ((userInput = input.readLine()) != null) {
                output.println(userInput);
            }
        } catch (UnknownHostException e) {
            System.err.println("Broker: Don't know about host " + HOSTNAME);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Broker: Couldn't get I/O for the connection to " + HOSTNAME);
            e.printStackTrace();
            System.exit(1);
        }
    }
}
