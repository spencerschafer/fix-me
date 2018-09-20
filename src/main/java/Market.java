import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Market {

    public static void main(String[] args) throws IOException {

        System.out.println("Market Started.");
        String hostName = "localhost";
        int portNumber = 5001;

        try (
                Socket marketSocket = new Socket(hostName, portNumber);
//                PrintWriter out = new PrintWriter(marketSocket.getOutputStream(), true);
//                BufferedReader in = new BufferedReader(new InputStreamReader(marketSocket.getInputStream()));
//                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
        ) {
//            String userInput;
//            while ((userInput = stdIn.readLine()) != null) {
//                out.println(userInput);
//                System.out.println("Broker: " + in.readLine());
//            }
        } catch (UnknownHostException e) {
            System.err.println("Market: Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Market: Couldn't get I/O for the connection to " +
                    hostName);
            System.exit(1);
        }
    }
}
