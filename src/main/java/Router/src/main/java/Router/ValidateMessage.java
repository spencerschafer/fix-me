package Router;

class ValidateMessage {

	private String checksum;
	private int checksumCopy;
	private int market_ID_Copy;

	ValidateMessage(String FIX_MESSAGE) {

		String[] message = FIX_MESSAGE.split("\\|");
		try {
			market_ID_Copy = Integer.parseInt(message[0]);
		} catch (Exception e) {
			System.err.println("Invalid Market ID (" + message[0] + ").");
			System.exit(1);
		}

		checksum = FIX_MESSAGE.substring((FIX_MESSAGE.length() - 4), FIX_MESSAGE.length() - 1);

		String substring = FIX_MESSAGE.substring(0, FIX_MESSAGE.length() - 4);

		for (char character : substring.toCharArray()) {
			checksumCopy += character;
		}
		checksumCopy %= 256;
	}

	boolean validateChecksum(int market_ID) {
		if (market_ID_Copy != market_ID) {
			return false;
		}

		return (String.format("%03d", checksumCopy)).equalsIgnoreCase(checksum);
	}
}
