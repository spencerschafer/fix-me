package Broker;

class Message {

    private String FIX_MESSAGE;
    private String instrument;
    private String order;
    private String market_ID;
    private int quantity;
    private int price;

    //Usage: <MARKET_ID> <INSTRUMENT> <QUANTITY <PRICE> <BUY> or <SELL>
    Message(String[] args) {

        market_ID = args[0].toUpperCase();
        instrument = args[1].toUpperCase();

        try {
            quantity = Integer.parseInt(args[2]);
        } catch (Exception e) {
            System.err.println("Invalid quantity.(" + quantity+ ").");
            System.out.println("Usage: <MARKET_ID> <INSTRUMENT> <QUANTITY <PRICE> <BUY> or <SELL>");
            System.exit(2);
        }

        try {
            price = Integer.parseInt(args[3]);
        } catch (Exception e) {
            System.err.println("Invalid price (" + price + ").");
            System.out.println("Usage: <MARKET_ID> <INSTRUMENT> <QUANTITY <PRICE> <BUY> or <SELL>");
            System.exit(3);
        }

        try {
            order = args[4].toUpperCase();
            if (!(order.equalsIgnoreCase("BUY") || order.equalsIgnoreCase("SELL"))) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.err.println("Invalid order (" + order + ").");
            System.out.println("Usage: <MARKET_ID> <INSTRUMENT> <QUANTITY <PRICE> <BUY> or <SELL>");
            System.exit(4);
        }

        FIX_MESSAGE = "[ " + market_ID + " | " + instrument + " | "
                + quantity + " | " + price + " | " + order + " ]";
    }

    String getMessage() {
        return FIX_MESSAGE;
    }
}
