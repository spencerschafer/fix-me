package Broker;

class CreateMessage {

    private String FIX_MESSAGE;
    private String instrument;
    private String order;
    private String market_ID;
    private int quantity;
    private int price;
    private int checksum;

    //Usage: <market_ID> <instrument> <quantity <price> <BUY> or <SELL>
    CreateMessage(String[] args) {

        market_ID = args[0];
        instrument = args[1].toUpperCase();

        try {
            quantity = Integer.parseInt(args[2]);
            if (quantity <= 0) {
            	throw new Exception();
			}
        } catch (Exception e) {
            System.err.println("Invalid quantity(" + args[2] + ").");
            System.out.println("Usage: <market_ID> <instrument> <quantity> <price> <BUY> or <SELL>");
            System.exit(2);
        }

        try {
            price = Integer.parseInt(args[3]);
        } catch (Exception e) {
            System.err.println("Invalid price (" + args[3] + ").");
            System.out.println("Usage: <market_ID> <instrument> <quantity> <price> <BUY> or <SELL>");
            System.exit(3);
        }

        try {
            order = args[4].toUpperCase();
            if (!(order.equalsIgnoreCase("BUY") || order.equalsIgnoreCase("SELL"))) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.err.println("Invalid order (" + args[4] + ").");
            System.out.println("Usage: <market_ID> <instrument> <quantity> <price> <BUY> or <SELL>");
            System.exit(4);
        }

        //Step 1: Creating initial message without checksum
        FIX_MESSAGE = market_ID + "|" + instrument + "|" + quantity + "|" + price + "|" + order + "|";

        //Step 3: Calculating checksum
        for (char character: FIX_MESSAGE.toCharArray()) {
            checksum += character;
        }
        checksum %= 256;

        //Step 3: Adding checksum to message in String form
        FIX_MESSAGE += String.format("%03d", checksum) + "|";
    }

    String getMessage() {
        return FIX_MESSAGE;
    }
}
