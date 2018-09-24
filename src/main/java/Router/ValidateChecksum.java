package Router;

class ValidateChecksum {

	private int checksum;
	private String checksumReceived;

	ValidateChecksum(String FIX_MESSAGE) {

		String message = FIX_MESSAGE.substring(0, FIX_MESSAGE.length() - 3);
		checksumReceived= FIX_MESSAGE.substring((FIX_MESSAGE.length() - 3));

		for (char character : message.toCharArray()) {
			checksum += character;
		}
		checksum %= 256;
	}


	boolean validateChecksum() {
		return (String.format("%03d", checksum)).equalsIgnoreCase(checksumReceived);
	}
}
