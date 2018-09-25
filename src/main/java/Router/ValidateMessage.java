package Router;

class ValidateMessage {

	private String checksum;
	private int checksumCopy;

	ValidateMessage(String FIX_MESSAGE) {

		String message = FIX_MESSAGE.substring(0, FIX_MESSAGE.length() - 4);
		checksum = FIX_MESSAGE.substring((FIX_MESSAGE.length() - 4), FIX_MESSAGE.length() - 1);

		for (char character : message.toCharArray()) {
			checksumCopy += character;
		}
		checksumCopy %= 256;
	}


	boolean validateChecksum() {
		return (String.format("%03d", checksumCopy)).equalsIgnoreCase(checksum);
	}
}
