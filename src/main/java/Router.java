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


            PrintWriter brokerOutput = new PrintWriter(brokerSocket.getOutputStream(), true);
            BufferedReader brokerInput = new BufferedReader(new InputStreamReader(brokerSocket.getInputStream()));

            PrintWriter marketOutput = new PrintWriter(marketSocket.getOutputStream(), true);
            BufferedReader marketInput = new BufferedReader(new InputStreamReader(marketSocket.getInputStream()));
            String brokerInputLine;
            String marketInputLine;
            while ((brokerInputLine = brokerInput.readLine()) != null && (marketInputLine = marketInput.readLine()) != null) {
                System.out.println("-");
                brokerOutput.println(brokerInputLine);
                System.out.println("Broker: " + brokerInputLine);
                marketOutput.println(marketInputLine);
                System.out.println("Market: " + marketInputLine);
                System.out.println("--");
            }
        } catch (
                IOException e) {
            System.out.println("Router: Exception caught when trying to listen on port "
                    + BROKER_PORT + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
