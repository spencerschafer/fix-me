package Broker;

class Checksum {

    private String FIX_MESSAGE;
    private int CHECKSUM;

    //Usage: <MARKET_ID> <INSTRUMENT> <QUANTITY <PRICE> <BUY> or <SELL>
    Checksum(String MARKET_ID, String INSTRUMENT, int QUANTITY, int PRICE, String ORDER) {
        //sum all ascii values including pipe character, excluding checksum itself
        //perform modulo 256 on resulting summation
        //checksum is given to three numbers
        //i.e 4168 % 256 is 62, therefore checksum is 062

        FIX_MESSAGE = MARKET_ID + "|" + INSTRUMENT + "|" + QUANTITY + "|" + PRICE + "|" + ORDER + "|";

        for (char character: FIX_MESSAGE.toCharArray()) {
            CHECKSUM += character;
            System.out.println(character + ": " + String.format("%03d", (int)character) + ": " + CHECKSUM);
        }

        CHECKSUM %= 256;
        System.out.println(CHECKSUM);
        FIX_MESSAGE += String.format("%03d", CHECKSUM);
    }

    String getMessage() {
        return FIX_MESSAGE;
    }
}
