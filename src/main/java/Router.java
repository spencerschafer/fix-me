import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Router {
    public static void main(String[] args) throws IOException {

        System.out.println("Router Started.");
        int portNumber = 5000;

        try (
                ServerSocket brokerSocket = new ServerSocket(portNumber);
                Socket clientSocket = brokerSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
            System.out.println("Broker Connected.");

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                out.println(inputLine);
                System.out.println("Broker: " + inputLine);
            }
        } catch (IOException e) {
            System.out.println("Router: Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
