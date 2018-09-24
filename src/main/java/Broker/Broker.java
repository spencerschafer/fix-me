package Broker;

public class Broker {
    private int ID =-1;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public static void main(String[] args) {

        String FIX_MESSAGE = new Message(args).getMessage();
        new BrokerServer(FIX_MESSAGE);
    }
}
