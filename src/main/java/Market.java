import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Market {
    private static final int MARKET_PORT = 5001;
    private static final String HOSTNAME = "localhost";

    public static void main(String[] args) {

        System.out.println("Market Started.");
        try {
            Socket marketSocket = new Socket(HOSTNAME, MARKET_PORT);

            //input
            PrintWriter output = new PrintWriter(System.out, true);

            //output
            BufferedReader input = new BufferedReader(new InputStreamReader(marketSocket.getInputStream()));

            String marketInput;
            while((marketInput = input.readLine()) != null) {
                System.out.print("Messaged received from Router: ");
                output.println(marketInput);
            }
        } catch (UnknownHostException e) {
            System.err.println("Market: Don't know about host " + HOSTNAME);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Market: Couldn't get I/O for the connection to " + HOSTNAME);
            e.printStackTrace();
            System.exit(1);
        }
    }
}
