package Broker;

class CreateChecksum {

    private String FIX_MESSAGE;
    private int CHECKSUM;

    CreateChecksum(String MARKET_ID, String INSTRUMENT, int QUANTITY, int PRICE, String ORDER) {

        FIX_MESSAGE = MARKET_ID + "|" + INSTRUMENT + "|" + QUANTITY + "|" + PRICE + "|" + ORDER + "|";

        for (char character: FIX_MESSAGE.toCharArray()) {
            CHECKSUM += character;
        }

        CHECKSUM %= 256;
        FIX_MESSAGE += String.format("%03d", CHECKSUM);
    }

    String getMessage() {
        return FIX_MESSAGE;
    }
}
