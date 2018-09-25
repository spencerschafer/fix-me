package Market;

class ValidateMessage {


	private String checksum;
	private String instrument;
	private String quantity;
	private String order;
	private int checksumCopy;

	//FIX_MESSAGE = market_ID|instrument|quantity|price|order|checksum|;
	ValidateMessage(String FIX_MESSAGE) {

		String[] message = FIX_MESSAGE.split("\\|");

		this.instrument = message[1];
		this.quantity = message[2];
		this.order = message[4];
		checksum = message[5];

		String messageWithoutChecksum = FIX_MESSAGE.substring(0, FIX_MESSAGE.length() - 4);
		for (char character : messageWithoutChecksum.toCharArray()) {
			checksumCopy += character;
		}
		checksumCopy %= 256;
	}

	boolean validateMessage(String instrument, int quantity) {
		// Checking validity of instrument
		if (!this.instrument.equals(instrument))
			return false;

		// Checking whether sufficient quantity is available for BUY / SELL order
		int tempQuantity = Integer.parseInt(this.quantity);
		if (order.equalsIgnoreCase("BUY")) {
			if (quantity - tempQuantity < 0) {
				System.err.println("BUY order quantity (" + this.quantity + ")" +
						" exceeds available Market quantity (" + quantity + ").");
				return false;
			}
		}
		return (String.format("%03d", checksumCopy)).equalsIgnoreCase(checksum);
	}
}
