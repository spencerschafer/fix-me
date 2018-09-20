import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import java.io.*;
import java.net.*;

public class Broker {

    public static void main(String[] args) throws IOException {

        System.out.println("Broker Started.");
        String hostName = "localhost";
        int portNumber = 5000;

        try (
                Socket brokerSocket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(brokerSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(brokerSocket.getInputStream()));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
        ) {
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("Router: " + in.readLine());
            }
        } catch (UnknownHostException e) {
            System.err.println("Broker: Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Broker: Couldn't get I/O for the connection to " +
                    hostName);
            System.exit(1);
        }
    }
}
