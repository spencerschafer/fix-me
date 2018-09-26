package Market;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class MarketServer {

    private int BROKER_ID;
    private static final int MARKET_PORT = 5001;
    private static final String HOSTNAME = "localhost";

    private int quantity;

    MarketServer(String instrument, int quantity) {
        this.quantity = quantity;
        Socket marketSocket = null;

        try {
            marketSocket = new Socket(HOSTNAME, MARKET_PORT);
            System.out.println("Market Started.");
        } catch (IOException e) {
            System.err.println("Market was unable to established a connection to " + HOSTNAME + " on port: " + MARKET_PORT);
            System.exit(1);
        }

        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(marketSocket.getInputStream()));
            System.out.println("Market ID: " + input.readLine());

            PrintWriter output = new PrintWriter(marketSocket.getOutputStream(), true);

            //validating checksum
            try {
                BROKER_ID = Integer.parseInt(input.readLine());
            } catch (Exception e) {
                System.err.println("Invalid broker ID.");
                System.exit(1);
            }
            String marketInput = input.readLine();
            try {
                if (!(new ValidateMessage(marketInput).validateMessage(instrument, quantity))) {
                    output.println("invalid");
                    throw new IOException();
                }
            } catch (IOException e) {
                System.err.println("Invalid message.");
                System.exit(1);
            }

            // Executing order
            String[] message = marketInput.split("\\|");
            int temp_quantity = Integer.parseInt(message[2]);
            String order = message[4];
            if (order.equalsIgnoreCase("BUY")) {
                this.quantity -= temp_quantity;
                System.out.println("Broker (ID:" + BROKER_ID + ") bought (" + temp_quantity +
                        ") units of " + instrument + ".");
            }
            else if (order.equalsIgnoreCase("SELL")) {
                this.quantity += temp_quantity;
                System.out.println("Broker (ID:" + BROKER_ID + ") sold (" + temp_quantity +
                        ") units of " + instrument + ".");
            }

            System.out.println("Instrument units remaining: " + this.quantity);
            System.out.println("Trade executed: " + marketInput);
            output.println("valid");

        } catch (IOException e) {
            System.err.println("Market was unable to create PrintWriter in class MarketServer");
            System.exit(1);
        }
    }


}
