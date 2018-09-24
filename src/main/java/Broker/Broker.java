package Broker;

class Broker {
    public static void main(String[] args) {

        String FIX_MESSAGE = new Message(args).getMessage();
        new BrokerServer(FIX_MESSAGE);
    }
}
