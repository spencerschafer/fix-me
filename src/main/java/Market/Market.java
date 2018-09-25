package Market;

class Market {

	public static void main(String[] args) {

		String instrument = null;
		int quantity = 0;

		if (args.length != 2) {
			System.err.println("Usage: <INSTRUMENT> <QUANTITY>");
			System.exit(1);
		}

		try {
			instrument = args[0];
			quantity = Integer.parseInt(args[1]);
			if (quantity < 0) {
				throw new Exception();
			}
		} catch(Exception e) {
			System.err.println("Invalid quantity (" + args[1] + ").");
			System.exit(1);
		}
		new MarketServer(instrument, quantity);
	}
}
