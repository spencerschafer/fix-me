package Broker;

class Broker {
    public static void main(String[] args) {

        if (args.length != 5) {
			System.err.println("Usage: <MARKET_ID> <INSTRUMENT> <QUANTITY> <PRICE> <BUY> or <SELL>");
			System.exit(1);
		}
        String FIX_MESSAGE = new CreateMessage(args).getMessage();
        new BrokerServer(FIX_MESSAGE);
    }
}
