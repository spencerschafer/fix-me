package Broker;

class Message {

    private String FIX_MESSAGE;
    private String INSTRUMENT;
    private String ORDER;
    private String MARKET_ID;
    private int QUANTITY;
    private int PRICE;

    //Usage: <MARKET_ID> <INSTRUMENT> <QUANTITY <PRICE> <BUY> or <SELL>
    Message(String[] args) {

        MARKET_ID = args[0].toUpperCase();
        INSTRUMENT = args[1].toUpperCase();

        try {
            QUANTITY = Integer.parseInt(args[2]);
        } catch (Exception e) {
            System.err.println("Invalid quantity.(" + QUANTITY+ ").");
            System.out.println("Usage: <MARKET_ID> <INSTRUMENT> <QUANTITY <PRICE> <BUY> or <SELL>");
            System.exit(2);
        }

        try {
            PRICE = Integer.parseInt(args[3]);
        } catch (Exception e) {
            System.err.println("Invalid price (" + PRICE + ").");
            System.out.println("Usage: <MARKET_ID> <INSTRUMENT> <QUANTITY <PRICE> <BUY> or <SELL>");
            System.exit(3);
        }

        try {
            ORDER = args[4].toUpperCase();
            if (!(ORDER.equalsIgnoreCase("BUY") || ORDER.equalsIgnoreCase("SELL"))) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.err.println("Invalid order (" + ORDER + ").");
            System.out.println("Usage: <MARKET_ID> <INSTRUMENT> <QUANTITY <PRICE> <BUY> or <SELL>");
            System.exit(4);
        }

        FIX_MESSAGE = new CreateChecksum(MARKET_ID, INSTRUMENT, QUANTITY, PRICE, ORDER).getMessage();
    }

    String getMessage() {
        return FIX_MESSAGE;
    }
}
