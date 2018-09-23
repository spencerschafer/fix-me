import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Router {
    private static final int BROKER_PORT = 5000;
    private static final int MARKET_PORT = 5001;


    public static void main(String[] args) {
        System.out.println("Router Started.");
        try {
            ServerSocket brokerServerSocket = new ServerSocket(BROKER_PORT);
            Socket brokerSocket = brokerServerSocket.accept();
            System.out.println("Broker Connected.");


            ServerSocket marketServerSocket = new ServerSocket(MARKET_PORT);
            Socket marketSocket = marketServerSocket.accept();
            System.out.println("Market Connected.");


            //INPUT FROM BROKER TO SERVER
            //Creates a Reader that receives objects from the location specified upon initialisation.
            //i.e new BufferedReader(new InputStreamReader(System.in, true) receives input from the std.in
            //i.e new BufferedReader(new InputStreamReader(Socket.getInputStream(), true) receives input from Socket's input stream
            BufferedReader brokerSocketInput = new BufferedReader(new InputStreamReader(brokerSocket.getInputStream()));

            //OUTPUT FROM ROUTER TO MARKET
            //Creates a PrintWriter that sends an object to the location specified upon initialisation.
            //i.e new PrintWriter(System.out, true) will send output to std.out
            //i.e new PrintWriter(Socket.getOutputStream(), true) will send output to the Socket's output stream
            PrintWriter serverSocketOutput = new PrintWriter(marketSocket.getOutputStream(), true);


            String brokerInputLine;
            while ((brokerInputLine = brokerSocketInput.readLine()) != null) {
                serverSocketOutput.println(brokerInputLine);
                System.out.println("Message sent to Market: " + brokerInputLine);
            }
        }
         catch (Exception e) {
            System.out.println("Router: Exception caught when trying to listen on port "
                    + BROKER_PORT + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
